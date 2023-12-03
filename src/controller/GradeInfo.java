package controller;

import Dao.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GradeInfo extends JFrame { 
	/**
	 * 学生根据学号查询所有成绩
	 */
	private static final long serialVersionUID = 1L;
	JPanel contain;
	JTextArea list;
	String id;

	String courseid;
	String coursename;
	String teacherid;
	String teachername;
	String studentid;
	String studentname;
	String grade;

	public GradeInfo(String id,JPanel _contain) {
//		super("课程");
		this.id = id;
//		setSize(600, 400);
		contain = new JPanel();
		_contain.add(contain);
//		setLocation(600, 400);
		list = new JTextArea();
		list.setEditable(false);
		contain.add(list);
		
		list.append("课程号" + "\t");
		list.append("课程名" + "\t");
		list.append("教师工号" + "\t");
		list.append("教师姓名" + "\t");
		list.append("学号" + "\t");
		list.append("学生姓名" + "\t");
		list.append("成绩" + "\n");
		try {

			ResultSet rs = Jdbc.SelectById("Grades","StuId",id);
			while (rs.next()){
				int grade = rs.getInt("grade");
				String CourseId = rs.getString("CourseId");
				String StuId = id;
				ResultSet CourseRS = Jdbc.SelectById("Course","courseId",CourseId);
				ResultSet StuRS = Jdbc.SelectById("User","id",StuId);
				CourseRS.next();StuRS.next();
				String CourseName = CourseRS.getString("courseName");
				String TeacherId = CourseRS.getString("teacherId");
				String TeacherName = CourseRS.getString("teacherName");
				String StuName = StuRS.getString("UserName");
				list.append(CourseId + "\t");
				list.append(CourseName + "\t");
				list.append(TeacherId + "\t");
				list.append(TeacherName + "\t");
				list.append(StuId + "\t");
				list.append(StuName + "\t");
				list.append(grade + "\n");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		setVisible(true);
	}
}
