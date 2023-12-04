package controller;

import Dao.Jdbc;

import java.awt.*;
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

import javax.swing.*;

@SuppressWarnings("serial")
public class GradeEnter extends JPanel implements ActionListener {
	/*
	 * ��ʦ��½�γ���Ϣ
	 */
	String idd;  // ��ʦ��
	JPanel contain,__contain,huge;
	JLabel id;
	JTextField idt, stuIdt, stuGradet, stuNamet;
	JFrame self;

	String targetFile;

	JButton submit, bn;
	ArrayList<String> modifiedContent = new ArrayList<String>();

	public GradeEnter(String idd, JPanel huge,JPanel __contain,JPanel _contain,JFrame self) {
//		super("�鿴");
		this.idd = idd;
		this.huge = huge;
		this.self = self;
		this.__contain =__contain;
//		setSize(300, 340);
//		setLocation(600, 400);
		contain = new JPanel();
		contain.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
		_contain.add(contain);
		id = new JLabel("�γ̺�");
		idt = new JTextField();
		submit = new JButton("�ύ");
		id.setPreferredSize(new Dimension(160, 80));
		idt.setPreferredSize(new Dimension(300, 80));
		submit.setPreferredSize(new Dimension(250, 80));
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
				enter();   // ����ɼ��������

			} else {
				JOptionPane.showMessageDialog(null, "��δ����˿γ̣�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (e.getSource() == bn) {

			if (hasThisStu() == 1) {   // ��½�ɼ�
				Connection connection = Jdbc.CONNECTION;
				try {
					PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Grades VALUES (?,?,?)");
					preparedStatement.setString(1, stuIdt.getText());
					preparedStatement.setString(2, idt.getText());
					preparedStatement.setInt(3, Integer.parseInt(stuGradet.getText()));
					preparedStatement.executeUpdate();
				} catch (SQLException | NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "�쳣������������\n" + ex.getMessage(), "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				}
				JOptionPane.showMessageDialog(null, "�ɼ���¼�ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null, "�γ̺�Ϊ" + idt.getText() + "  �޴�ѧ��", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	int hasThisStu() {
		String aa = stuIdt.getText();
		Connection connection = Jdbc.CONNECTION;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE id = ? AND UserName = ?");
			preparedStatement.setString(1, stuIdt.getText());
			preparedStatement.setString(2, stuNamet.getText());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) return 1;
			else return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	int hasThisCourse(String courseId) {
		Connection connection = Jdbc.CONNECTION;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Course WHERE teacherId = ? AND courseId = ?");
			preparedStatement.setString(1, idd);
			preparedStatement.setString(2, courseId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) return 1;
			else return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	void enter() {
		huge.updateUI();
		JLabel lb = new JLabel("��¼�ɼ�");
		lb.setSize(300, 340);
		JPanel contain1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bn = new JButton("�ύ");
		JLabel stuId = new JLabel("ѧ��");
		JLabel stuGrade = new JLabel("�ɼ�");
		JLabel stuName = new JLabel("����");
		Box vbox = Box.createVerticalBox();

		stuIdt = new JTextField();
		stuGradet = new JTextField();
		stuNamet = new JTextField();

		stuId.setPreferredSize(new Dimension(160,80));
		stuIdt.setPreferredSize(new Dimension(300,80));

		stuGrade.setPreferredSize(new Dimension(160,80));
		stuGradet.setPreferredSize(new Dimension(300,80));

		stuName.setPreferredSize(new Dimension(160,80));
		stuNamet.setPreferredSize(new Dimension(300,80));

		bn.setPreferredSize(new Dimension( 250, 80));
		contain1.add(stuId);
		contain1.add(stuIdt);
		contain2.add(stuGrade);
		contain2.add(stuGradet);
		contain3.add(stuName);
		contain3.add(stuNamet);
		vbox.add(lb);
		vbox.add(contain1);
		vbox.add(contain2);
		vbox.add(contain3);
		vbox.add(bn);
		__contain.add(vbox);
		bn.addActionListener(this);
		__contain.setVisible(true);
		huge.updateUI();
		self.pack();

//		fm.setVisible(true);

	}
}

