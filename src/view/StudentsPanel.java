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
	 * 学生登陆后操作主界面
	 */
	JPanel contain,_contain;
	String id;
	Box vbox;
	JButton infoButton, gradeButton, courseButton, editButton;

	public StudentsPanel(String id) {
		super("学生");
		this.id = id;
		setLocationRelativeTo(null);
		vbox=Box.createVerticalBox();
		add(vbox);
		setSize(400, 700);
		contain = new JPanel();
		vbox.add(contain);

		contain.setLayout(new FlowLayout(FlowLayout.CENTER));

		infoButton = new JButton("信息查询");
		gradeButton = new JButton("成绩查询");
		courseButton = new JButton("课程查询");
		editButton = new JButton("修改信息");
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
		 _contain = new JPanel();
		setVisible(true);
		setResizable(false);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == infoButton) {
			_contain.removeAll();
			vbox.add(_contain);
			new Info(id, 1,_contain);
			contain.updateUI();
			pack();
		}
		if (e.getSource() == gradeButton) {
			_contain.removeAll();
			vbox.add(_contain);
			new GradeInfo(id,_contain);
			contain.updateUI();
			pack();
		}
		if (e.getSource() == courseButton) {
			_contain.removeAll();
			vbox.add(_contain);
			new CourseView(id, 0,_contain);
			contain.updateUI();
			pack();
		}
		if (e.getSource() == editButton) {
			_contain.removeAll();
			vbox.add(_contain);
			new EditInfo(id, 0,_contain);
			contain.updateUI();
			pack();
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
