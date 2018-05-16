package com.qg.recruit.domain;


import lombok.Data;

import javax.persistence.*;
import java.text.Collator;
import java.util.Locale;

/**
 * @author 郑俊铭
 * Date: 2017/12/1
 * Time: 18:10
 * No struggle, talent how to match the willfulness.
 * Description: 学生报名信息实体类
 */
@Entity
@Table(name = "student")
@Data
public class Student implements Comparable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    /**
     * 学生信息
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 学号
     */
    @Column(name = "student_id", nullable = false)
    private String studentId;

    /**
     * 性别
     */
    @Column(name = "sex", nullable = false)
    private int sex;

    /**
     * 年级
     */
    @Column(name = "grade", nullable = false)
    private String grade;

    /**
     * 专业班级
     */
    @Column(name = "a_class", nullable = false)
    private String aClass;

    /**
     * 手机
     */
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * 绩点
     */
    @Column(name = "gpa", nullable = false)
    private double gpa;

    /**
     * 是否挂科，1为挂科，0为没挂科
     */
    @Column(name = "fail", nullable = false)
    private int fail;

    /**
     * C语言理论成绩
     */
    @Column(name = "c_score", nullable = false)
    private int cScore;

    /**
     * C语言实验成绩
     */
    @Column(name = "c_test_score", nullable = false)
    private String cTestScore;

    /**
     * 英语成绩
     */
    @Column(name = "en_score", nullable = false)
    private int enScore;

    /**
     * 应征方向： 1前端,2后台,3嵌入式,4手游,5移动,6数据挖掘,7设计
     */
    @Column(name = "wish", nullable = false)
    private int wish;

    /**
     * 是否愿意服从应征方向调剂: 1为服从 0为不服从
     */
    @Column(name = "swap", nullable = false)
    private int swap;

    public Student() {
    }


    public Student( String studentId, String name, int sex, String aClass) {
        this.studentId = studentId;
        this.name = name;
        this.sex = sex;
        this.aClass = aClass;
    }

    public Student(String name, String phone, int sex) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
    }

    @Override
    public int compareTo(Object o) {
        if ( o instanceof Student) {
            return Collator.getInstance(Locale.CHINA).compare(this.name , ((Student) o).name);
        } else {
            return -1;
        }
    }
}
