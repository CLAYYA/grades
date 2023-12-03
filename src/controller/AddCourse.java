package controller;


import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.Jdbc;
import model.Course;


@SuppressWarnings("serial")
public class AddCourse extends JFrame implements ActionListener {
    /*
     * 教师增加课程
     */


    JPanel contain;
    JButton submit;
    JLabel id, name, gredit, classH, teacherId, teacherName;
    JTextField idt, namet, greditt, classHt, teacherIdt, teacherNamet;

    public AddCourse() {
        super("增加课程");
        setSize(400, 400);
        setLocation(600, 400);
        contain = new JPanel();
        contain.setLayout(null);
        id = new JLabel("课程号");
        name = new JLabel("课程名");
        gredit = new JLabel("学分");
        classH = new JLabel("学时");

        teacherId = new JLabel("教师编号");
        teacherName = new JLabel("教师姓名");

        submit = new JButton("提交");
        idt = new JTextField();
        namet = new JTextField();
        greditt = new JTextField();
        classHt = new JTextField();
        teacherIdt = new JTextField();
        teacherNamet = new JTextField();

        id.setBounds(42, 35, 75, 35);
        idt.setBounds(80, 35, 150, 35);
        name.setBounds(40, 90, 75, 35);
        namet.setBounds(80, 90, 150, 35);
        gredit.setBounds(45, 145, 75, 35);
        greditt.setBounds(80, 145, 150, 35);
        classH.setBounds(45, 200, 75, 35);
        classHt.setBounds(80, 200, 150, 35);

        teacherId.setBounds(45, 245, 75, 35);
        teacherIdt.setBounds(85, 245, 150, 35);

        teacherName.setBounds(45, 280, 75, 35);
        teacherNamet.setBounds(80, 280, 75, 35);

        submit.setBounds(102, 320, 70, 30);
        contain.add(id);
        contain.add(idt);
        contain.add(name);
        contain.add(namet);
        contain.add(gredit);
        contain.add(greditt);
        contain.add(classH);
        contain.add(classHt);
        contain.add(teacherId);
        contain.add(teacherIdt);
        contain.add(teacherName);
        contain.add(teacherNamet);
        contain.add(submit);
        submit.addActionListener(this);
        add(contain);
        setVisible(true);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    //使用数据库方法版本
    public int hasCourse(String id) {//  教师开课前检查课程是否已经存在
        Connection connection = Jdbc.CONNECTION;
        String sql = "SELECT courseId FROM Course WHERE courseId = " + id + ';';
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            if (resultSet.next()) return 1;
            else return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //改为数据库存储
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if ((idt.getText().equals("")) || (namet.getText().equals("")) || (greditt.getText().equals(""))
                    || (classHt.getText().equals("")) || teacherIdt.getText().equals("") || teacherNamet.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "信息不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if ((hasCourse(idt.getText())) == 1) {
                    JOptionPane.showMessageDialog(null, "此课程已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //数据库部分
                    Connection connection = Jdbc.CONNECTION;
                    try {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO Course values (?,?,?,?,?,?)");
                        statement.setString(1, idt.getText());
                        statement.setString(2, namet.getText());
                        statement.setString(3, greditt.getText());
                        statement.setString(4, classHt.getText());
                        statement.setString(5, teacherIdt.getText());
                        statement.setString(6, teacherNamet.getText());
                        statement.executeUpdate();
                        //同步到User表
                        ResultSet rs = Jdbc.SelectById("User","id",teacherIdt.getText());
                        PreparedStatement stm =connection.prepareStatement("UPDATE User SET Courses = ? WHERE id = ?");
                        rs.next();
                        if(rs.getString("Courses") == null){
                            stm.setString(1,idt.getText());
                            stm.setString(2,teacherIdt.getText());
                            stm.executeUpdate();
                        }else {
                            String courses = rs.getString("Courses")+","+idt.getText();

                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
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
