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
	 * �û��޸���Ϣ
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
		super("�޸���Ϣ");
		setSize(300, 420);
		setLocation(600, 400);
		this.id = id;
		this.flag = flag; // flag=0�޸�ѧ����Ϣ��flag=1�޸Ľ�ʦ��Ϣ
		contain = new JPanel();
		contain.setLayout(null);
		name = new JLabel("����");
		birth = new JLabel("����");
		inst = new JLabel("ѧԺ");
		major = new JLabel("רҵ");
		pass1 = new JLabel("������");
		pass2 = new JLabel("ȷ������");
		submit = new JButton("�ύ");
		group = new CheckboxGroup();
		check1 = new Checkbox("��", group, true);
		check2 = new Checkbox("Ů", group, false);
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
				JOptionPane.showMessageDialog(null, "��Ϣ����Ϊ�գ�", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (!(pass1t.getText().equals(pass2t.getText()))) {
					JOptionPane.showMessageDialog(null, "��������ȷ�����벻ͬ��", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (pass1t.getText().length() < 6) {
					JOptionPane.showMessageDialog(null, "���볤������Ϊ6λ��", "��ʾ",
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
						JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ��������û������ڵȡ�\n����"+ex.getMessage(), "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
