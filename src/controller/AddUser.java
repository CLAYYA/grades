package controller;

import Dao.Jdbc;

import java.awt.AWTEvent;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
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


@SuppressWarnings("serial")
public class AddUser extends JFrame implements ActionListener {
	/*
	 * �������Ա����û����������ѧ������ʦ������Ա
	 */
	JPanel contain;
	JLabel id, name, birthday, institute, major;
	JTextField idt, namet, birthdayt, institutet, majort;
	Checkbox check1, check2;
	CheckboxGroup group;
	JButton submit;
	Choice chooice;

	public AddUser() {
		super("����û�");
		setSize(300, 350);
		setLocation(600, 400);
		contain = new JPanel();
		contain.setLayout(null);
		id = new JLabel("�ʺ�");
		name = new JLabel("����");
		group = new CheckboxGroup();
		check1 = new Checkbox("��", group, true);
		check2 = new Checkbox("Ů", group, false);
		birthday = new JLabel("����");
		institute = new JLabel("ѧԺ");
		major = new JLabel("רҵ");		
		
		submit = new JButton("�ύ");
		chooice = new Choice();
		chooice.addItem("ѧ��");
		chooice.addItem("��ʦ");
		chooice.addItem("����Ա");
		
		idt = new JTextField();
		namet = new JTextField();
		
		birthdayt = new JTextField();
		institutet = new JTextField();
		majort = new JTextField();
		
		id.setBounds(42, 45, 75, 35);
		idt.setBounds(80, 45, 150, 35);
		
		
		name.setBounds(42, 20, 75, 35);
		namet.setBounds(80, 20, 150, 35);
		check1.setBounds(80, 67, 80, 40);
		check2.setBounds(160, 67, 80, 40);
		birthday.setBounds(42, 100, 75, 35);
		birthdayt.setBounds(80, 100, 150, 35);
		institute.setBounds(40, 145, 75, 35);
		institutet.setBounds(80, 145, 150, 35);
		major.setBounds(40, 220, 75, 35);
		majort.setBounds(80, 220, 150, 35);
		
	
		chooice.setBounds(80, 180, 150, 35);
		submit.setBounds(102, 260, 70, 30);
		contain.add(id);
		contain.add(idt);
		contain.add(name);
		contain.add(namet);
		
		contain.add(birthday);
		contain.add(birthdayt);
		contain.add(institute);
		contain.add(institutet);
		contain.add(major);
		contain.add(majort);
		contain.add(check1);
		contain.add(check2);
		
		contain.add(chooice);
		contain.add(submit);
		submit.addActionListener(this);
		add(contain);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}
	//0ѧ�� 1��ʦ 2����Ա
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == submit){
			if ((idt.getText().equals("")) || (namet.getText().equals("")) || (birthdayt.getText().equals(""))
					|| (institutet.getText().equals("")) || (majort.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "��Ϣ����Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
					//Ĭ������123456
					statement.setString(4, "123456");
					statement.setString(5, birthdayt.getText());
					statement.setString(6, sex);
					statement.setString(7, institutet.getText());
					statement.setString(8,majort.getText());
					statement.executeUpdate();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "��ѧ���Ѿ����ڻ����ݿ����!\n����"+ex.getMessage(), "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
