package controller;

import Dao.Jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class CourseView extends JFrame {
	/*
	 * 学生查询课程，教师查询所教授课程
	 */
	
	JPanel contain;
	JTextArea list;
	public CourseView(String id, int flag,String a){
		super("课程");
		setSize(330, 400);
		contain = new JPanel();
		setLocation(600, 400);
		list = new JTextArea();
		list.setEditable(false);
		contain.add(list);
		list.append("课程编号\t课程名\t学分\t学时\n");

		String courseid;
		String coursename;
		String credit = null;
		String classhour = null;
	}
	public CourseView(String id, int flag) {
		super("课程");
		setSize(330, 400);
		contain = new JPanel();
		setLocation(600, 400);
		list = new JTextArea();
		list.setEditable(false);
		contain.add(list);
		list.append("课程编号\t课程名\t学分\t学时\n");

		String courseid;
		String coursename;
		String credit = null;
		String classhour = null;

		Connection connection = Jdbc.CONNECTION;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT Courses FROM User WHERE id = ?");
			preparedStatement.setString(1,id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				List<String> courses = Arrays.asList(resultSet.getString(1).split(","));
				for (String Cname:courses) {
					PreparedStatement CourseStatement = connection.prepareStatement("SELECT * FROM Course WHERE courseId = ?");
					CourseStatement.setString(1,Cname);
					ResultSet results = CourseStatement.executeQuery();
					if(results.next()){
						courseid = results.getString(1);
						coursename = results.getString(2);
						credit = results.getString(3);
						classhour = results.getString(4);
						list.append(courseid + "\t");
						list.append(coursename + "\t");
						list.append(credit + "\t");
						list.append(classhour + "\n");
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		add(contain);
		setVisible(true);
	}
}
