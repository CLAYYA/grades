package controller;

import Dao.Jdbc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;


@SuppressWarnings("serial")
public class AddUser extends JPanel implements ActionListener {
	/*
	 * 教务管理员添加用户，可以添加学生，教师，管理员
	 */
	JPanel contain;
	JLabel id, name, birthday, institute, major;
	JTextField idt, namet, birthdayt, institutet, majort;
	Checkbox check1, check2;
	CheckboxGroup group;
	JButton submit;
	Choice chooice;

	public AddUser(JPanel _contain) {
//		super("添加用户");
//		setSize(300, 350);
//		setLocation(600, 400);
		Box vbox1 = Box.createVerticalBox();
		Box vbox2 = Box.createVerticalBox();
		Box vbox = Box.createVerticalBox();
		Box hbox = Box.createHorizontalBox();
		JPanel contain1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain4 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
		JPanel contain5 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
		JPanel contain6 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
		JPanel contain7 = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
		JPanel contain8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		id = new JLabel("帐号");
		name = new JLabel("姓名");
		group = new CheckboxGroup();
		check1 = new Checkbox("男", group, true);
		check2 = new Checkbox("女", group, false);
		birthday = new JLabel("生日");
		institute = new JLabel("学院");
		major = new JLabel("专业");		
		
		submit = new JButton("提交");
		chooice = new Choice();
		chooice.addItem("学生");
		chooice.addItem("教师");
		chooice.addItem("教务员");
		
		idt = new JTextField();
		namet = new JTextField();
		
		birthdayt = new JTextField();
		institutet = new JTextField();
		majort = new JTextField();
		
		id.setPreferredSize(new Dimension(160,80));
		idt.setPreferredSize(new Dimension(300, 80));
		
		
		name.setPreferredSize(new Dimension(160,80));
		namet.setPreferredSize(new Dimension(300, 80));
		check1.setPreferredSize(new Dimension( 80, 40));
		check2.setPreferredSize(new Dimension( 80, 40));
		birthday.setPreferredSize(new Dimension(160,80));
		birthdayt.setPreferredSize(new Dimension(300, 80));
		institute.setPreferredSize(new Dimension(160,80));
		institutet.setPreferredSize(new Dimension(300, 80));
		major.setPreferredSize(new Dimension(160,80));
		majort.setPreferredSize(new Dimension(300, 80));
		
	
		chooice.setPreferredSize(new Dimension( 300, 80));
		submit.setPreferredSize(new Dimension( 300, 80));
		contain1.add(id);
		contain1.add(idt);
		contain2.add(name);
		contain2.add(namet);
		
		contain3.add(birthday);
		contain3.add(birthdayt);
		contain4.add(institute);
		contain4.add(institutet);
		contain5.add(major);
		contain5.add(majort);
		contain6.add(check1);
		contain6.add(check2);
		
		contain7.add(chooice);
		contain8.add(submit);
		vbox1.add(contain1);
		vbox1.add(contain2);
		vbox1.add(contain6);
		vbox1.add(contain3);
		vbox2.add(contain5);
		vbox2.add(contain4);
		vbox2.add(contain7);
		hbox.add(vbox1);
		hbox.add(vbox2);
		vbox.add(hbox);
		vbox.add(contain8);

		submit.addActionListener(this);
		_contain.add(vbox);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}
	//0学生 1老师 2管理员
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == submit){
			if ((idt.getText().equals("")) || (namet.getText().equals("")) || (birthdayt.getText().equals(""))
					|| (institutet.getText().equals("")) || (majort.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "信息不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
			} else {
				int auth = chooice.getSelectedIndex();
				String sex;
				if (check1.getState()) {
					sex = "male";
				} else {
					sex = "female";
				}
				Connection connection = Jdbc.CONNECTION;
				try {
					PreparedStatement statement = connection.prepareStatement("INSERT INTO User values (?,?,?,?,?,?,?,?,NULL)");
					statement.setString(1, idt.getText());
					statement.setInt(2,auth);
					statement.setString(3, namet.getText());
					//默认密码123456
					statement.setString(4, "123456");
					statement.setString(5, birthdayt.getText());
					statement.setString(6, sex);
					statement.setString(7, institutet.getText());
					statement.setString(8,majort.getText());
					statement.executeUpdate();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "此学生已经存在或数据库错误!\n错误："+ex.getMessage(), "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}


}
