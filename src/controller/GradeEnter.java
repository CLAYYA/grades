package controller;

import Dao.Jdbc;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GradeEnter extends JFrame implements ActionListener {
	/*
	 * 教师登陆课程信息
	 */
	String idd;  // 教师号
	JPanel contain;
	JLabel id;
	JTextField idt, stuIdt, stuGradet, stuNamet;
	
	String targetFile;
	
	JButton submit, bn;
	ArrayList<String> modifiedContent = new ArrayList<String>();

	public GradeEnter(String idd) {
		super("查看");
		this.idd = idd;
		setSize(300, 340);
		setLocation(600, 400);
		contain = new JPanel();
		contain.setLayout(null);
		add(contain);
		id = new JLabel("课程号");
		idt = new JTextField();
		submit = new JButton("提交");
		id.setBounds(38, 50, 75, 35);
		idt.setBounds(80, 50, 150, 35);
		submit.setBounds(102, 125, 70, 30);
		contain.add(id);
		contain.add(idt); 
		contain.add(submit);
		submit.addActionListener(this);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			if (hasThisCourse(idt.getText()) == 1) {
				enter();   // 进入成绩输入界面
				
			} else {
				JOptionPane.showMessageDialog(null, "您未开设此课程！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (e.getSource() == bn) {
			
			if (hasThisStu() == 1) {   // 登陆成绩
				Connection connection = Jdbc.CONNECTION;
				try {
					PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Grades VALUES (?,?,?)");
					preparedStatement.setString(1,stuIdt.getText());
					preparedStatement.setString(2,idt.getText());
					preparedStatement.setInt(3,Integer.parseInt( stuGradet.getText()));
					preparedStatement.executeUpdate();
				} catch (SQLException | NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "异常：请输入数字\n"+ex.getMessage(), "提示", JOptionPane.INFORMATION_MESSAGE);
				}
				JOptionPane.showMessageDialog(null, "成绩登录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
			
			} else {
				JOptionPane.showMessageDialog(null, "课程号为" + idt.getText() + "  无此学生", "提示",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	int hasThisStu(){
		Connection connection = Jdbc.CONNECTION;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE id = ? AND UserName = ?");
			preparedStatement.setString(1,stuIdt.getText());
			preparedStatement.setString(2,stuNamet.getText());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) return 1;
			else return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	int hasThisCourse(String courseId){
		Connection connection = Jdbc.CONNECTION;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Course WHERE teacherId = ? AND courseId = ?");
			preparedStatement.setString(1,idd);
			preparedStatement.setString(2,courseId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) return 1;
			else return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	void enter() {
		JFrame fm = new JFrame("登录成绩");
		fm.setSize(300, 340);
		JPanel contain = new JPanel();
		fm.setLocation(600, 400);
		contain.setLayout(null);
		bn = new JButton("提交");
		JLabel stuId = new JLabel("学号");
		JLabel stuGrade = new JLabel("成绩");
		JLabel stuName = new JLabel("姓名");
		
		stuIdt = new JTextField();
		stuGradet = new JTextField();
		stuNamet = new JTextField();
		
		stuId.setBounds(38, 50, 75, 35);
		stuIdt.setBounds(80, 50, 150, 35);
		
		stuGrade.setBounds(38, 110, 75, 35);
		stuGradet.setBounds(80, 110, 150, 35);
		
		stuName.setBounds(38, 170, 75, 35);
		stuNamet.setBounds(80, 170, 150, 35);
		
		bn.setBounds(170, 220, 70, 30);
		contain.add(stuId);
		contain.add(stuIdt);
		contain.add(stuGrade);
		contain.add(stuGradet);
		contain.add(stuName);
		contain.add(stuNamet);
		contain.add(bn);
		fm.add(contain);
		bn.addActionListener(this);
		
	
		fm.setVisible(true);
		
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
		}
	}
}
