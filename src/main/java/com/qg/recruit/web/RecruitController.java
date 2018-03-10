package com.qg.recruit.web;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qg.recruit.annotation.RequestLimit;
import com.qg.recruit.domain.Student;
import com.qg.recruit.dto.Result;
import com.qg.recruit.enums.StateEnum;
import com.qg.recruit.exception.RecruitException;
import com.qg.recruit.service.impl.RecruitServiceImpl;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郑俊铭
 * Date: 2017/12/2
 * Time: 9:24
 * No struggle, talent how to match the willfulness.
 * Description: 招新网站控制器
 */
@RestController
public class RecruitController {
    private final RecruitServiceImpl recruitService;

    @Autowired
    public RecruitController(RecruitServiceImpl recruitService) {
        this.recruitService = recruitService;
    }

    /**
     * 判断学生是否已经注册的控制器
     *
     * @param map 具有一个key，studentId
     * @return Result结果
     */
    @RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json")
    public Result judgeStudentSignUpByStudentId(@RequestBody Map<String, String> map) {
        String studentId = "studentId";
        if (!map.containsKey(studentId)) {
            throw new RecruitException(StateEnum.PARAM_IS_LOST);
        }
        System.out.println(map);
        return recruitService.judgeStudentSignUpByStudentId(map.get("studentId"));
    }

    /**
     * 根据学号获取学生信息的控制器
     *
     * @param map 具有一个key，学号
     * @return Result结果
     */
    @RequestMapping(value = "/details", method = RequestMethod.POST, produces = "application/json")
    public Result getStudentInfoByStudentId(@RequestBody Map<String, String> map) {
        String studentId = "studentId";
        if (!map.containsKey(studentId)) {
            // 参数缺失
            throw new RecruitException(StateEnum.PARAM_IS_LOST);
        }
        return recruitService.getStudentInfoByStudentId(map.get("studentId"));
    }

    /**
     * 学生报名信息提交
     *
     * @param student 学生实体类
     * @return Result结果
     */
    @RequestLimit(count = 10, time = 10000)
    @RequestMapping(value = "/enroll", method = RequestMethod.POST, produces = "application/json")
    public Result insertRegistrationInfo(@RequestBody Student student, HttpServletRequest request) {
        System.out.println(student);
        return recruitService.insertRegistrationInfo(student);
    }

    /**
     * 分页查询数据
     *
     * @param map 包含page页数，pageSize一页的数量
     * @return Result结果
     */
    @RequestMapping(value = "/select", method = RequestMethod.POST, produces = "application/json")
    public Result getPagingData(@RequestBody Map<String, Integer> map) {
        System.out.println(map);
        if (!map.containsKey("page") || !map.containsKey("pageSize")) {
            // 参数缺失
            throw new RecruitException(StateEnum.PARAM_IS_LOST);
        }
        return recruitService.getPagingData(map.get("page") - 1, map.get("pageSize"));
    }

    /**
     * 根据学号批量删除学生数据
     *
     * @param map 含有若干个学号
     * @return Result结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    public Result deleteStudentByStudentId(@RequestBody Map<String, String[]> map) {
        String studentIds = "studentIds";
        if (!map.containsKey(studentIds)) {
            // 参数缺失
            throw new RecruitException(StateEnum.PARAM_IS_LOST);
        }
        return recruitService.deleteStudentByStudentId(map.get("studentIds"));
    }

    /**
     * 根据学号批量导入学生信息到Word文档
     *
     * @return Result结果
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST, produces = "application/json")
    public Result exportWord(HttpServletRequest request) throws IOException, Docx4JException {
        return recruitService.exportWord(request);
    }

    /**
     * 根据学号搜索学生
     *
     * @param map map，含有学号的键值对
     * @return Result结果
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json")
    public Result selectByStudentId(@RequestBody Map<String, String> map) {
        String studentId = "studentId";
        if (!map.containsKey(studentId)) {
            // 参数缺失
            throw new RecruitException(StateEnum.PARAM_IS_LOST);
        }
        return recruitService.selectByStudentId(map.get("studentId"));
    }

    /**
     * 按组别返回报名学生
     *
     * @param group 组别代号
     * @return Result结果
     */
    @RequestMapping(value = "/sms" , method = RequestMethod.GET)
    public List<Student> sendSms(@Param("group") int group){
        Result<List<Student>> result = recruitService.sendSmsToApp(group);
        if (result.getData() == null) {
            return result.getData();
        }
        Collections.sort(result.getData());
        return result.getData();
    }
}
