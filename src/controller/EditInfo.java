package controller;

import Dao.Jdbc;

import java.awt.AWTEvent;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EditInfo extends JFrame implements ActionListener {
	/*
	 * 用户修改信息
	 */
	String id;
	JPanel contain;
	JButton submit;
	JLabel name, inst, birth, pass1, pass2, major;
	JTextField namet, instt, birtht, pass1t, pass2t, majort;
	Checkbox check1, check2;
	CheckboxGroup group;
	int flag;

	public EditInfo(String id, int flag) {
		super("修改信息");
		setSize(300, 420);
		setLocation(600, 400);
		this.id = id;
		this.flag = flag; // flag=0修改学生信息，flag=1修改教师信息
		contain = new JPanel();
		contain.setLayout(null);
		name = new JLabel("姓名");
		birth = new JLabel("生日");
		inst = new JLabel("学院");
		major = new JLabel("专业");
		pass1 = new JLabel("新密码");
		pass2 = new JLabel("确认密码");
		submit = new JButton("提交");
		group = new CheckboxGroup();
		check1 = new Checkbox("男", group, true);
		check2 = new Checkbox("女", group, false);
		instt = new JTextField();
		namet = new JTextField();
		birtht = new JTextField();
		majort = new JTextField();
		pass1t = new JPasswordField();
		pass2t = new JPasswordField();
		name.setBounds(42, 20, 75, 35);
		namet.setBounds(80, 20, 150, 35);
		check1.setBounds(80, 60, 80, 40);
		check2.setBounds(160, 60, 80, 40);
		birth.setBounds(42, 100, 75, 35);
		birtht.setBounds(80, 100, 150, 35);
		inst.setBounds(40, 145, 75, 35);
		instt.setBounds(80, 145, 150, 35);
		major.setBounds(40, 190, 75, 35);
		majort.setBounds(80, 190, 150, 35);
		pass1.setBounds(36, 235, 75, 35);
		pass1t.setBounds(80, 235, 150, 35);
		pass2.setBounds(28, 280, 75, 35);
		pass2t.setBounds(80, 280, 150, 35);
		submit.setBounds(102, 325, 70, 30);
		contain.add(name);
		contain.add(namet);
		contain.add(check1);
		contain.add(check2);
		contain.add(birth);
		contain.add(birtht);
		contain.add(inst);
		contain.add(instt);
		contain.add(major);
		contain.add(majort);
		contain.add(pass1);
		contain.add(pass1t);
		contain.add(pass2);
		contain.add(pass2t);
		contain.add(submit);
		submit.addActionListener(this);
		add(contain);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			if ((inst.getText().equals("")) || (birtht.getText().equals(""))
					|| (namet.getText().equals(""))
					|| (pass1t.getText().equals(""))
					|| (pass2t.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "信息不能为空！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (!(pass1t.getText().equals(pass2t.getText()))) {
					JOptionPane.showMessageDialog(null, "新密码与确认密码不同！", "提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (pass1t.getText().length() < 6) {
					JOptionPane.showMessageDialog(null, "密码长度至少为6位！", "提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String m;
					if (check1.getState()) {
						m = "male";
					} else {
						m = "female";
					}
					Connection connection = Jdbc.CONNECTION;
					try {
						PreparedStatement preparedStatement = connection.prepareStatement("UPDATE User SET UserName=? ,Password=?,Birthday=?,Institute=?,Major=?,Sex = ? WHERE id = ?");
						preparedStatement.setString(1,namet.getText());
						preparedStatement.setString(2,pass1t.getText());
						preparedStatement.setString(3,birtht.getText());
						preparedStatement.setString(4,instt.getText());
						preparedStatement.setString(5,majort.getText());
						preparedStatement.setString(6,m);
						preparedStatement.setString(7,id);
						preparedStatement.executeUpdate();
						JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, "修改失败，可能是用户不存在等。\n错误："+ex.getMessage(), "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
		}
	}
}
