package com.qg.recruit.service;

import com.qg.recruit.domain.Student;
import com.qg.recruit.dto.Result;
import org.docx4j.openpackaging.exceptions.Docx4JException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author 郑俊铭
 * Date: 2017/12/2
 * Time: 9:32
 * No struggle, talent how to match the willfulness.
 * Description: QG招新网站逻辑层接口
 */
public interface RecruitService {

    /**
     * 判断学生是否已经报名的逻辑层接口
     *
     * @param studentId 学号
     * @return Result结果
     */
    Result judgeStudentSignUpByStudentId(String studentId);

    /**
     * 根据学号获取学生信息
     *
     * @param studentId 学号
     * @return Result结果
     */
    Result getStudentInfoByStudentId(String studentId);

    /**
     * 插入学生报名信息逻辑层接口
     *
     * @param student 学生信息实体类
     * @return Result结果
     */
    Result insertRegistrationInfo(Student student);

    /**
     * 获取分页数据
     *
     * @param page 页数
     * @param pageSize 一页的数量
     * @return Result结果
     */
    Result getPagingData(Integer page, Integer pageSize);

    /**
     * 根据学号删除学生数据逻辑层接口
     *
     * @param studentIds 含有若干个学号
     * @return Result结果
     */
    Result deleteStudentByStudentId(String[] studentIds);

    /**
     * 根据学号搜索学生
     *
     * @param studentId 学号
     * @return Result结果
     */
    Result selectByStudentId(String studentId);

    /**
     * 根据学号批量导出Word
     *
     * @param request 请求信息
     * @param wish 方向
     * @throws IOException ioException
     * @throws Docx4JException docx4JException
     * @return Result结果
     */
    Result exportWord(HttpServletRequest request, int wish) throws IOException, Docx4JException;

    /**
     * 返回学生信息，按组别查询
     * @param group 组别
     * @return Result结果
     */
    Result<List<Student>> sendSmsToApp(int group);
}
