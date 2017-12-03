package com.qg.recruit.web;

import com.qg.recruit.domain.Student;
import com.qg.recruit.domain.StudentRepository;
import com.qg.recruit.dto.Result;
import com.qg.recruit.service.impl.RecruitServiceImpl;
import com.qg.recruit.utils.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 郑俊铭
 * Date: 2017/12/2
 * Time: 9:24
 * No struggle, talent how to match the willfulness.
 * Description: 招新网站控制器
 */
@RestController
@RequestMapping("/recruit")
public class RecruitController {
    private final RecruitServiceImpl recruitService;

    private final StudentRepository studentRepository;

    @Autowired
    public RecruitController(RecruitServiceImpl recruitService, StudentRepository studentRepository) {
        this.recruitService = recruitService;
        this.studentRepository = studentRepository;
    }

    /**
     * 判断学生是否已经注册的控制器
     *
     * @param map 具有一个key，studentId
     * @return Result结果
     */
    @RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json")
    public Result judgeStudentSignUpByStudentId(@RequestBody Map<String, String> map) {
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
        return recruitService.getStudentInfoByStudentId(map.get("studentId"));
    }

    /**
     * 学生报名信息提交
     *
     * @param student 学生实体类
     * @return Result结果
     */
    @RequestMapping(value = "/enroll", method = RequestMethod.POST, produces = "application/json")
    public Result insertRegistrationInfo(@RequestBody Student student) {
        return recruitService.insertRegistrationInfo(student);
    }

    /**
     * 分页查询数据
     *
     * @param page 当前页数
     * @return Result结果
     */
    @RequestMapping(value = "/select/{page}", method = RequestMethod.POST, produces = "application/json")
    public Result getPagingData(@PathVariable("page") Integer page) {
        System.out.println(page);
        return recruitService.getPagingData(page - 1);
    }

    /**
     * 根据学号批量删除学生数据
     *
     * @param map 含有若干个学号
     * @return Result结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    public Result deleteStudentByStudentId(@RequestBody Map<String, List<Map<String, String>>> map) {
        return recruitService.deleteStudentByStudentId(map.get("studentIds"));
    }

    /**
     * 根据学号批量导入学生信息到Word文档
     *
     * @param studentIdMap 含有若干个学号
     * @return Result结果
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST, produces = "application/json")
    public Result exportWordByStudentId(@RequestBody Map<String, List<Map<String, String>>> studentIdMap) {
        return null;
    }

    @RequestMapping(value = "testWord", method = RequestMethod.GET)
    public void getWord() throws IOException {
        Student student = studentRepository.findByStudentId("3116005120");
        Map<String, Object> map = new HashMap<>();
        map.put("name", student.getName());
        map.put("sex", student.getSex());
        map.put("grade", student.getGrade());
        map.put("aClass", student.getaClass());
        map.put("fail", student.getFail());
        map.put("cScore", student.getcScore());
        map.put("cTestScore", student.getcTestScore());
        map.put("enScore", student.getEnScore());
        map.put("gpa", student.getGpa());
        map.put("phone", student.getPhone());
        WordUtils.exportWord(map, "word", "zhengjunming.doc", "templates/QGStudioRecruitsTheRegistrationFormForThe2018TrainingCamp.ftl");
    }
}
