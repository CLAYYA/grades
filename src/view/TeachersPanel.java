package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

import controller.AddCourse;
import controller.CourseView;
import controller.EditInfo;
import controller.GradeEnter;
import controller.Info;


@SuppressWarnings("serial")
public class TeachersPanel extends JFrame implements ActionListener {
	/*
	 * ?????????????????
	 */
	
	String idd;
	public JPanel contain1,contain2,_contain,__contain,hugecontain;
	JButton infoButton, gradeButton, courseButton, editButton, courseView, sortGrade;
	Box vbox;
	JFrame self;


	public TeachersPanel(String idd) {
		super("教师");
		this.idd = idd;
		setLocationRelativeTo(null);
		setSize(400, 1700);
		contain1 = new JPanel();
		contain1.setLayout(new FlowLayout(FlowLayout.CENTER));
		contain2 = new JPanel();
		contain2.setLayout(new FlowLayout(FlowLayout.CENTER));
		hugecontain = new JPanel(new FlowLayout(FlowLayout.CENTER));

		vbox = Box.createVerticalBox();

		infoButton = new JButton("信息查询");
		gradeButton = new JButton("成绩登录");
		courseButton = new JButton("全部课程");
		editButton = new JButton("修改信息");
		courseView = new JButton("开课");
		
		sortGrade = new JButton("成绩统计");
		
		infoButton.setPreferredSize(new Dimension(400,100));
		editButton.setPreferredSize(new Dimension(400,100));
		courseView.setPreferredSize(new Dimension(400,100));
		courseButton.setPreferredSize(new Dimension(400,100));
		gradeButton.setPreferredSize(new Dimension(400,100));
		sortGrade.setPreferredSize(new Dimension(400,100));
		
		contain1.add(infoButton);
		infoButton.addActionListener(this);
		contain1.add(gradeButton);
		gradeButton.addActionListener(this);
		contain1.add(courseView);
		courseView.addActionListener(this);
		contain2.add(courseButton);
		courseButton.addActionListener(this);
		contain2.add(editButton);
		editButton.addActionListener(this);
		contain2.add(sortGrade);
		sortGrade.addActionListener(this);
		
		vbox.add(contain1);
		vbox.add(contain2);
		hugecontain.add(vbox);
		add(hugecontain);
		pack();
		_contain = new JPanel();
		__contain = new JPanel();
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == infoButton) {
			_contain.removeAll();
			__contain.removeAll();
			vbox.add(_contain);
			new Info(idd, 1,_contain);
			vbox.updateUI();
			pack();
		}
		if (e.getSource() == gradeButton) {
			_contain.removeAll();
			__contain.removeAll();
			vbox.add(_contain);
			vbox.add(__contain);
			self = this;
			new GradeEnter(idd,hugecontain,__contain,_contain,self);
			hugecontain.updateUI();
			pack();
		}
		if (e.getSource() == courseButton) {
			_contain.removeAll();
			vbox.add(_contain);
			new CourseView(idd, 1,_contain);
			_contain.updateUI();
			pack();
		}
		if (e.getSource() == editButton) {
			_contain.removeAll();
			vbox.add(_contain);
			new EditInfo(idd, 1,_contain);
			_contain.updateUI();
			pack();
		}
		if (e.getSource() == courseView) {
			_contain.removeAll();
			vbox.add(_contain);
			new AddCourse();
			_contain.updateUI();
			pack();
		}
		if(e.getSource() == sortGrade){
			_contain.removeAll();
			vbox.add(_contain);
			new SortGradeFrame();
			_contain.updateUI();
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
