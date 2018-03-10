package com.qg.recruit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 按小组查找学生
     *
     * @param group 组名代号
     * @return 学生集合
     */
    @Query(value = "select new Student (t.name , t.phone ,t.sex) FROM Student t WHERE t.wish = :wish ORDER BY t.name")
    List<Student> findByWish(@Param("wish") int group);
}
