package controller;


import Dao.Jdbc;

import java.awt.AWTEvent;
import java.awt.Choice;
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
import javax.swing.JTextField;

public class DeleteUser extends JFrame implements ActionListener {
	/**
	 * 管理员删除用户
	 */
	private static final long serialVersionUID = 1L;
	JPanel contain;
	JLabel id;
	JTextField idt;
	Choice chooice;
	JButton submit;
	
	String file = System.getProperty("user.dir")+"/data/";
	// String file = "D://test//";

	public DeleteUser() {
		super("删除用户");
		setSize(300, 340);
		setLocation(600, 400);
		contain = new JPanel();
		contain.setLayout(null);
		chooice = new Choice();
		chooice.addItem("学生");
		chooice.addItem("教师");
		chooice.addItem("教务员");
		id = new JLabel("帐号");
		submit = new JButton("提交");
		idt = new JTextField();
		id.setBounds(42, 45, 75, 35);
		idt.setBounds(80, 45, 150, 35);
		chooice.setBounds(80, 100, 150, 35);
		submit.setBounds(102, 150, 70, 30);
		contain.add(id);
		contain.add(idt);
		contain.add(chooice);
		contain.add(submit);
		submit.addActionListener(this);
		add(contain);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submit){
			String deleteId = idt.getText();
			if (deleteId.equals(" ")) return;//为空检验

			Connection connection = Jdbc.CONNECTION;
			try {
				PreparedStatement  preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id = ?");
				preparedStatement.setString(1,deleteId);
				preparedStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "删除学生成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "此学生不存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
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
