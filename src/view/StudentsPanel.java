package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

import controller.CourseView;
import controller.EditInfo;
import controller.GradeInfo;
import controller.Info;



@SuppressWarnings("serial")
public class StudentsPanel extends JFrame implements ActionListener {
	/*
	 * ѧ����½�����������
	 */
	JPanel contain;
	String id;
	Box hbox;
	JButton infoButton, gradeButton, courseButton, editButton;

	public StudentsPanel(String id) {
		super("ѧ��");
		this.id = id;
		setLocationRelativeTo(null);
		hbox=Box.createVerticalBox();
		add(hbox);
		setSize(400, 700);
		contain = new JPanel();
		hbox.add(contain);

		contain.setLayout(new FlowLayout(FlowLayout.CENTER));

		infoButton = new JButton("��Ϣ��ѯ");
		gradeButton = new JButton("�ɼ���ѯ");
		courseButton = new JButton("�γ̲�ѯ");
		editButton = new JButton("�޸���Ϣ");
		infoButton.setPreferredSize(new Dimension(400,100));
		gradeButton.setPreferredSize(new Dimension(400,100));
		courseButton.setPreferredSize(new Dimension(400,100));
		editButton.setPreferredSize(new Dimension(400,100));
		contain.add(infoButton);
		infoButton.addActionListener(this);
		contain.add(gradeButton);
		gradeButton.addActionListener(this);
		contain.add(courseButton);
		courseButton.addActionListener(this);
		contain.add(editButton);
		editButton.addActionListener(this);

//		pack();
		setVisible(true);
		setResizable(false);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == infoButton) {
			JPanel _contain = new JPanel();
			hbox.add(_contain);
			new Info(id, 1,_contain);
			contain.updateUI();
			pack();
		}
		if (e.getSource() == gradeButton) {
			JPanel _contain = new JPanel();
			hbox.add(_contain);
			new GradeInfo(id,_contain);
			contain.updateUI();
			pack();
		}
		if (e.getSource() == courseButton) {
			new CourseView(id, 0);
		}
		if (e.getSource() == editButton) {
			new EditInfo(id, 0);
		}
	}

	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
			System.exit(0);
		}
	}
}
