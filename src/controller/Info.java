package controller;

import java.awt.AWTEvent;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Dao.Jdbc;
import model.*;

public class Info extends JPanel {
    /**
     * 用户查询个人信息
     */
    private static final long serialVersionUID = 1L;
    JLabel idLabel, nameLabel, genderLabel, birLabel, insLabel, majorLabel;
    String id, name, pwd, gender, birthday, institute, major;
    JPanel stuInfoJPanel;

    Student stu;
    Teacher t;

    public Info(String id, int flag, JPanel contain) {
//        super("信息");
        this.id = id;
//        setSize(300, 340);
//        setLocation(600, 400);
        stuInfoJPanel = new JPanel();
        stuInfoJPanel.setLayout(new GridLayout(10, 1));
        contain.add(stuInfoJPanel);
        ResultSet rs = Jdbc.SelectById("User", "id", id);
        try {
            rs.next();
            name = rs.getString("UserName");
            pwd = rs.getString("Password");
            gender = rs.getString("Sex");
            birthday = rs.getString("Birthday");
            institute = rs.getString("Institute");
            major = rs.getString("Major");
            if (flag == 1) {
                stu = new Student(id, pwd, name, gender, birthday, institute, major);
                idLabel = new JLabel("账号:" + stu.getId());
                nameLabel = new JLabel("姓名:" + stu.getName());
                genderLabel = new JLabel("性别:" + stu.getSex());
                birLabel = new JLabel("生日:" + stu.getBirthday());
                insLabel = new JLabel("学院:" + stu.getInstitute());
                majorLabel = new JLabel("系别:" + stu.getMajor());

            } else {
                t = new Teacher(id, pwd, name, gender, birthday, institute, major);
                idLabel = new JLabel("账号:" + t.getId());
                nameLabel = new JLabel("姓名:" + t.getName());
                genderLabel = new JLabel("性别:" + t.getSex());
                birLabel = new JLabel("生日:" + t.getBirthday());
                insLabel = new JLabel("学院:" + t.getInstitute());
                majorLabel = new JLabel("系别:" + t.getMajor());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        stuInfoJPanel.add(idLabel);
        stuInfoJPanel.add(nameLabel);
        stuInfoJPanel.add(genderLabel);
        stuInfoJPanel.add(birLabel);
        stuInfoJPanel.add(insLabel);
        stuInfoJPanel.add(majorLabel);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        setVisible(true);
    }
}

//    public void processWindowEvent(WindowEvent e) {
//        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
//            this.dispose();
//            setVisible(false);
//        }
//    }
//}
