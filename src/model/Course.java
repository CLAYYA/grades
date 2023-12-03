package model;

import Dao.Jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Course {

    private String courseId;
    private String courseName;
    private String teacherId;
    private String teacherName;
    private String credit;
    private String hour;
    private float fail, pass, good, excellent;

    public Course(String courseId, float pass, float good, float excellent) {
        super();
        this.courseId = courseId;
        this.pass = pass;
        this.good = good;
        this.excellent = excellent;
    }

    public Course(String courseId, String courseName, String teacherId,
                  String teacherName, String credit, String hour) {

        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.credit = credit;
        this.hour = hour;
    }

    public Course(String courseId, String courseName, String teacherId,
                  String teacherName, float fial, float pass, float good,
                  float excellent) {

        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.fail = fial;
        this.pass = pass;
        this.good = good;
        this.excellent = excellent;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public float getFial() {
        return fail;
    }

    public void setFial(float fial) {
        this.fail = fial;
    }

    public float getPass() {
        return pass;
    }

    public void setPass(float pass) {
        this.pass = pass;
    }

    public float getGood() {
        return good;
    }

    public void setGood(float good) {
        this.good = good;
    }

    public float getExcellent() {
        return excellent;
    }

    public void setExcellent(float excellent) {
        this.excellent = excellent;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public float getFail() {
        return fail;
    }

    public void setFail(float fail) {
        this.fail = fail;
    }


    public int hasCourse() {
        ResultSet rs = Jdbc.SelectById("Course", "courseId", this.courseId);
        try {
            if (rs.next()) return 1;
            else return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int[] sortGrade() {
        int failCount = 0, passCount = 0, goodCount = 0, excellentCount = 0;
        ResultSet rs = Jdbc.SelectById("Grades", "CourseId", this.courseId);
        try {
            while (rs.next()) {
                int grade = rs.getInt("grade");
                if (grade < this.pass) failCount++;
                else if(grade < this.good) passCount++;
                else if(grade < this.excellent) goodCount++;
                else excellentCount++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new int[]{failCount, passCount, goodCount, excellentCount};
    }

    public int isValidate() {      // 输入的成绩标准是否是在正常内[0, 100],以及pass<good<excellent
        if (this.pass < 0 || this.pass > 100 || this.good < 0 || this.good > 100 || this.excellent < 0 || this.excellent > 100
                || this.pass >= good || this.pass >= excellent || this.good >= excellent)
            return 1;
        return 0;
    }


}
