package com.qg.recruit.config;

import com.qg.recruit.annotation.RequestLimit;
import com.qg.recruit.domain.IpBlackList;
import com.qg.recruit.domain.IpBlackListRepository;
import com.qg.recruit.enums.StateEnum;
import com.qg.recruit.exception.RequestLimitException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 郑俊铭
 * Date: 2018/1/22
 * Time: 19:45
 * No struggle, talent how to match the willfulness.
 * Description:
 */
@Aspect
@Component
public class RequestLimitContract {
    private final IpBlackListRepository ipBlackListRepository;
    private static final Logger logger = LoggerFactory.getLogger("requestLimitLogger");
    private Map<String , Integer> redisTemplate = new HashMap<>();

    @Autowired
    public RequestLimitContract(IpBlackListRepository ipBlackListRepository) {
        this.ipBlackListRepository = ipBlackListRepository;
    }

    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint , RequestLimit limit) throws RequestLimitException {
        try {
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    request = (HttpServletRequest) arg;
                    break;
                }
            }
            if (request == null) {
                throw new RequestLimitException(StateEnum.PARAM_IS_LOST);
            }
            String ip = RequestLimitContract.getIpAddr(request);
            System.out.println(ip);
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat(ip);
            synchronized (this) {
                if (null != ipBlackListRepository.findByIp(ip)) {
                    logger.info(ip + "已列入黑名单");
                    throw new RequestLimitException(StateEnum.IP_IS_ILLEGAL);
                }
            }
            if (redisTemplate.get(key) == null || redisTemplate.get(key) == 0) {
                redisTemplate.put(key, 1);
            } else {
                redisTemplate.put(key, redisTemplate.get(key) + 1);
            }
            int count = redisTemplate.get(key);
            if (count > 0) {
                //创建一个定时器
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        redisTemplate.remove(key);
                    }
                };
                // 这个定时器设定在time规定的时间之后会执行上面的remove方法，也就是说在这个时间后它可以重新访问
                ScheduledExecutorService pool = Executors.newScheduledThreadPool(1000);
                pool.schedule(timerTask, limit.time(), TimeUnit.MILLISECONDS);
            }
            System.out.println(count);
            synchronized (this) {
                if (count > limit.count()) {
                    IpBlackList ipBlackList = new IpBlackList(ip, new Date());
                    logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
                    ipBlackListRepository.save(ipBlackList);
                    throw new RequestLimitException(StateEnum.IP_IS_ILLEGAL);
                }
            }
        } catch (RequestLimitException e) {
            throw e;
        } catch (Exception e) {
            logger.error("发生异常", e);
        }
    }

    private static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip!= null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !"".equals(ip)  && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
