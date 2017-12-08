package com.example.zyd.libraryseat.entity;

/**
 * Created by zyd on 2017/11/11.
 */

public class Student {
    private Integer Id;
    private String StuentName;
    private String StudentId;
    private String institute;
    private String password;

    public Student() {

    }

    public Student(String StudentName, String StudentId, String institute, String password) {
        this.StuentName = StudentName;
        this.StudentId = StudentId;
        this.institute = institute;
        this.password = password;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getStuentName() {
        return StuentName;
    }

    public void setStuentName(String stuentName) {
        StuentName = stuentName;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
