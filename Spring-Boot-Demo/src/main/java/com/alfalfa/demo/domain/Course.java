package com.alfalfa.demo.domain;

public class Course {
    private Long id;
    private String course_name;
    private String course_term;
    private String teacher_name;
    private Long comment_count;
    private Long popcount;
    private String deatil;
    private String addtime;
    private String nullField;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_term() {
        return course_term;
    }

    public void setCourse_term(String course_term) {
        this.course_term = course_term;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public Long getComment_count() {
        return comment_count;
    }

    public void setComment_count(Long comment_count) {
        this.comment_count = comment_count;
    }

    public Long getPopcount() {
        return popcount;
    }

    public void setPopcount(Long popcount) {
        this.popcount = popcount;
    }

    public String getDeatil() {
        return deatil;
    }

    public void setDeatil(String deatil) {
        this.deatil = deatil;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getNullField() {
        return nullField;
    }

    public void setNullField(String nullField) {
        this.nullField = nullField;
    }
}
