package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import javax.swing.UIManager;

import java.util.Enumeration;
import java.util.Hashtable;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.FlatDarculaLaf;

import controller.CheckInfo;
import Dao.Jdbc;


public class MainFrame extends JFrame implements ActionListener {
	/**
	 * ��½������
	 */
	private static final long serialVersionUID = 1L;
	JTextField idTextField;
	JPasswordField passwdTextField;
	JLabel idLabel, passwdLabel, title;
	Choice chooice;
	JButton logon;
	JPanel contain, contain1, contain2, contain3;
	int count = 0;

	public MainFrame() {
		super("�˺ŵ�½");
		setLocationRelativeTo(null);
		setSize(1000, 800);
		contain = new JPanel();
		contain.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 80));
		idLabel = new JLabel("ID��",JLabel.RIGHT);
		passwdLabel = new JLabel("����",JLabel.RIGHT);
		idTextField = new JTextField();
		passwdTextField = new JPasswordField();
		logon = new JButton("��½");
		chooice = new Choice();
		chooice.addItem("ѧ��");
		chooice.addItem("��ʦ");
		// chooice.addItem("����Ա");
		chooice.addItem("ϵͳ����Ա");
		contain.setPreferredSize(new Dimension(900, 600));
		idLabel.setPreferredSize(new Dimension(250, 100));
		idTextField.setPreferredSize(new Dimension(500, 100));
		passwdLabel.setPreferredSize(new Dimension(250, 100));
		passwdTextField.setPreferredSize(new Dimension(500, 100));
		chooice.setPreferredSize(new Dimension(500, 100));
		logon.setPreferredSize(new Dimension(270, 90));
		contain.add(idLabel);
		contain.add(idTextField);
		contain.add(passwdLabel);
		contain.add(passwdTextField);
		contain.add(chooice);
		contain.add(logon);
		logon.addActionListener(this);
		add(contain);
		setVisible(true);
		setResizable(false);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logon) {
			String ch = (String) chooice.getSelectedItem();
			if (ch == "ѧ��") {
				if ((new CheckInfo().isMember("student", idTextField.getText(),
						new String(passwdTextField.getPassword()))) == 1) {
					setVisible(false);
					new StudentsPanel(idTextField.getText());
				} else {
					count += 1;
					if (count <= 5) {
						JOptionPane.showMessageDialog(null, "�޴��û������������������",
								"����", JOptionPane.INFORMATION_MESSAGE);
					}
					if (count > 5) {
						JOptionPane.showMessageDialog(null, "�����������5�Σ�",
								"����", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						setVisible(false);
						System.exit(0);
					}
				}
			} else if (ch == "��ʦ") {
				if ((new CheckInfo().isMember("teacher", idTextField.getText(),
						new String(passwdTextField.getPassword(), 0,
								passwdTextField.getPassword().length))) == 1) {
					setVisible(false);
					new TeachersPanel(idTextField.getText());
				} else {
					count += 1;
					if (count <= 5) {
						JOptionPane.showMessageDialog(null, "�޴��û������������������",
								"����", JOptionPane.INFORMATION_MESSAGE);
					}
					if (count > 5) {
						JOptionPane.showMessageDialog(null, "�����������5�Σ�",
								"����", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						setVisible(false);
						System.exit(0);
					}
				}
			} else if (ch == "ϵͳ����Ա") {
				if ((new CheckInfo().isMember("administrator", idTextField
						.getText(), new String(passwdTextField.getPassword(),
						0, passwdTextField.getPassword().length))) == 1) {
					setVisible(false);
					new AdministratorPanel(idTextField.getText());
				} else {
					count += 1;
					if (count <= 5) {
						JOptionPane.showMessageDialog(null, "�޴��û������������������",
								"����", JOptionPane.INFORMATION_MESSAGE);
					}
					if (count > 5) {
						JOptionPane.showMessageDialog(null, "�����������5�Σ�",
								"����", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						setVisible(false);
						System.exit(0);
					}
				}
			}
		}

	}


	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
			setVisible(false);
			System.exit(0);
		}
	}

	//����ȫ������
	public static void InitGlobalFont(Font fnt) {
		FontUIResource fontRes = new FontUIResource(fnt);
		for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource)
				UIManager.put(key, fontRes);
		}

	}

	public static void main(String[] args) {

		FlatDarculaLaf.install();
		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		InitGlobalFont(new Font("����", Font.PLAIN, 36));
		UIManager.put("Button.arc", 999);
		UIManager.put("Component.arc", 999);
		UIManager.put("ProgressBar.arc", 7);
		UIManager.put("TextField.arc", 999);
		Jdbc.initDB();
		new StudentsPanel("111222333");
		new MainFrame();
	}
}
