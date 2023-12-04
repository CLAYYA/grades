package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

import controller.AddUser;
import controller.DeleteUser;
import controller.EditInfo;



@SuppressWarnings("serial")
public class AdministratorPanel extends JFrame implements ActionListener {
	/*
	 * ����Ա��½�����������
	 */
	JButton deleteUser, addUser, selfInfo;
	JPanel contain,_contain;
	String idd;
	Box vbox;

	public AdministratorPanel(String idd) {
		super("ϵͳ����Ա");
		this.idd = idd;
		setLocationRelativeTo(null);
		setSize(400, 700);
		contain = new JPanel(new FlowLayout(FlowLayout.CENTER));
		vbox = Box.createVerticalBox();

		add(vbox);
		selfInfo = new JButton("�޸���Ϣ");
		addUser = new JButton("�����û�");
		deleteUser = new JButton("ɾ���û�");
		selfInfo.setPreferredSize(new Dimension(400,100));
		addUser.setPreferredSize(new Dimension(400,100));
		deleteUser.setPreferredSize(new Dimension(400,100));
		contain.add(selfInfo);
		contain.add(addUser);
		contain.add(deleteUser);
		vbox.add(contain);
		selfInfo.addActionListener(this);
		addUser.addActionListener(this);
		deleteUser.addActionListener(this);
		setVisible(true);
		pack();
		_contain = new JPanel();
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addUser) {
			_contain.removeAll();
			vbox.add(_contain);
			new AddUser(_contain);
			vbox.updateUI();
			pack();// �û������ʼ��ͳһΪ123456
		}
		else if (e.getSource() == deleteUser) {
			_contain.removeAll();
			vbox.add(_contain);
			new DeleteUser(_contain);
			vbox.updateUI();
			pack();
		}
		else if (e.getSource() == selfInfo) {
			_contain.removeAll();
			vbox.add(_contain);
			new EditInfo(idd, 3,_contain);
			vbox.updateUI();
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