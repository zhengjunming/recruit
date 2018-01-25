package com.qg.recruit.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author 郑俊铭
 * Date: 2017/12/11
 * Time: 21:29
 * No struggle, talent how to match the willfulness.
 * Description: 获取端口值的配置类
 */
@Configuration
public class PortConfig implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
    private static EmbeddedServletContainerInitializedEvent embeddedServletContainerInitializedEvent;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent embeddedServletContainerInitializedEvent) {
        PortConfig.embeddedServletContainerInitializedEvent = embeddedServletContainerInitializedEvent;
    }

    /**
     * 获取端口值
     *
     * @return 返回端口
     */
    public static int getPort() {
        return embeddedServletContainerInitializedEvent.getEmbeddedServletContainer().getPort();
    }
}
