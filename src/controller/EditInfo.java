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
public class EditInfo extends JPanel implements ActionListener {
	/*
	 * �û��޸���Ϣ
	 */
	String id;
	JButton submit;
	JLabel name, inst, birth, pass1, pass2, major;
	JTextField namet, instt, birtht, pass1t, pass2t, majort;
	Checkbox check1, check2;
	CheckboxGroup group;
	int flag;

	public EditInfo(String id, int flag,JPanel _contain) {

		this.id = id;
		this.flag = flag; // flag=0�޸�ѧ����Ϣ��flag=1�޸Ľ�ʦ��Ϣ
		Box vbox1 = Box.createVerticalBox();
		Box vbox2 = Box.createVerticalBox();
		Box vbox = Box.createVerticalBox();
		Box hbox1 = Box.createHorizontalBox();
		Box hbox2 = Box.createHorizontalBox();
		JPanel contain1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain2 = new JPanel(new FlowLayout(FlowLayout.CENTER,100,10));
		JPanel contain3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain6 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,70));
		JPanel contain7 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,70));
		JPanel contain8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
		name.setPreferredSize(new Dimension(160,80));
		namet.setPreferredSize(new Dimension(300, 80));
		check1.setPreferredSize(new Dimension(80, 40));
		check2.setPreferredSize(new Dimension( 80, 40));
		birth.setPreferredSize(new Dimension(160, 80));
		birtht.setPreferredSize(new Dimension(300, 80));
		inst.setPreferredSize(new Dimension( 160, 80));
		instt.setPreferredSize(new Dimension( 300, 80));
		major.setPreferredSize(new Dimension( 160, 80));
		majort.setPreferredSize(new Dimension( 300, 80));
		pass1.setPreferredSize(new Dimension(160, 80));
		pass1t.setPreferredSize(new Dimension( 300, 80));
		pass2.setPreferredSize(new Dimension(160, 80));
		pass2t.setPreferredSize(new Dimension( 300, 80));
		submit.setPreferredSize(new Dimension( 250, 80));

		contain1.add(name);
		contain1.add(namet);
		contain2.add(check1);
		contain2.add(check2);
		contain3.add(birth);
		contain3.add(birtht);
		contain4.add(inst);
		contain4.add(instt);
		contain5.add(major);
		contain5.add(majort);
		contain6.add(pass1);
		contain6.add(pass1t);
		contain7.add(pass2);
		contain7.add(pass2t);
		contain8.add(submit);
		vbox1.add(contain1);
		vbox1.add(contain2);
		vbox1.add(contain3);
		vbox2.add(contain4);
		vbox2.add(contain5);
		hbox2.add(contain6);
		hbox2.add(contain7);
		hbox1.add(vbox1);
		hbox1.add(vbox2);
		vbox.add(hbox1);
		vbox.add(hbox2);
		vbox.add(contain8);
		submit.addActionListener(this);
		_contain.add(vbox);
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

}
