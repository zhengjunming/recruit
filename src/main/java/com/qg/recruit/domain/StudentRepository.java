package com.qg.recruit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 郑俊铭
 * Date: 2017/12/1
 * Time: 20:50
 * No struggle, talent how to match the willfulness.
 * Description:
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    /**
     * 根据学号查找学生
     *
     * @param studentId 学号
     * @return 学生实体类
     */
    Student findByStudentId(@Param("studentId") String studentId);


}
