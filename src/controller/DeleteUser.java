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

public class DeleteUser extends JPanel implements ActionListener {
	/**
	 * ����Աɾ���û�
	 */
	private static final long serialVersionUID = 1L;
	JPanel contain;
	JLabel id;
	JTextField idt;
	Choice chooice;
	JButton submit;
	
	String file = System.getProperty("user.dir")+"/data/";
	// String file = "D://test//";

	public DeleteUser(JPanel _contain) {
//		super("ɾ���û�");
//		setSize(300, 340);
//		setLocation(600, 400);
		JPanel contain1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel contain2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box vbox = Box.createVerticalBox();
		chooice = new Choice();
		chooice.addItem("ѧ��");
		chooice.addItem("��ʦ");
		chooice.addItem("����Ա");
		id = new JLabel("�ʺ�");
		submit = new JButton("�ύ");
		idt = new JTextField();
		id.setPreferredSize(new Dimension(160,80));
		idt.setPreferredSize(new Dimension(300, 80));
		chooice.setPreferredSize(new Dimension(300, 80));
		submit.setPreferredSize(new Dimension( 250, 80));
		contain1.add(id);
		contain1.add(idt);
		contain2.add(chooice);
		contain3.add(submit);
		vbox.add(contain1);
		vbox.add(contain2);
		vbox.add(contain3);
		submit.addActionListener(this);
		_contain.add(vbox);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submit){
			String deleteId = idt.getText();
			if (deleteId.equals(" ")) return;//Ϊ�ռ���

			Connection connection = Jdbc.CONNECTION;
			try {
				PreparedStatement  preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id = ?");
				preparedStatement.setString(1,deleteId);
				preparedStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "ɾ��ѧ���ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "��ѧ�������ڣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
