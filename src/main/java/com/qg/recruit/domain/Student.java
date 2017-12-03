package com.qg.recruit.domain;


import javax.persistence.*;

/**
 * @author 郑俊铭
 * Date: 2017/12/1
 * Time: 18:10
 * No struggle, talent how to match the willfulness.
 * Description: 学生报名信息实体类
 */
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getEntity",
                query = "SELECT student_id, name, sex, a_class FROM student",
                resultSetMapping = "ReturnColumnEntityList"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ReturnColumnEntityList",
                entities = {},
                columns = {
                        @ColumnResult(name = "student_id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "sex"),
                        @ColumnResult(name = "a_class")
                }
        )
})

@Entity
@Table(name = "student")
public class Student {

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getaClass() {
        return aClass;
    }

    public void setaClass(String aClass) {
        this.aClass = aClass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public int getcScore() {
        return cScore;
    }

    public void setcScore(int cScore) {
        this.cScore = cScore;
    }

    public String getcTestScore() {
        return cTestScore;
    }

    public void setcTestScore(String cTestScore) {
        this.cTestScore = cTestScore;
    }

    public int getEnScore() {
        return enScore;
    }

    public void setEnScore(int enScore) {
        this.enScore = enScore;
    }

    public int getWish() {
        return wish;
    }

    public void setWish(int wish) {
        this.wish = wish;
    }

    public int getSwap() {
        return swap;
    }

    public void setSwap(int swap) {
        this.swap = swap;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", sex=" + sex +
                ", grade='" + grade + '\'' +
                ", aClass='" + aClass + '\'' +
                ", phone='" + phone + '\'' +
                ", gpa=" + gpa +
                ", fail=" + fail +
                ", cScore=" + cScore +
                ", cTestScore='" + cTestScore + '\'' +
                ", enScore=" + enScore +
                ", wish=" + wish +
                ", swap=" + swap +
                '}';
    }
}
