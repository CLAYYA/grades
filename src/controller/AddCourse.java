package controller;


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
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import Dao.Jdbc;
import model.Course;


@SuppressWarnings("serial")
public class AddCourse extends JPanel implements ActionListener {
    /*
     * 教师增加课程
     */


    JPanel contain;
    JButton submit;
    JLabel id, name, gredit, classH, teacherId, teacherName;
    JTextField idt, namet, greditt, classHt, teacherIdt, teacherNamet;

    public AddCourse(JPanel _contain) {
//        super("增加课程");
//        setSize(400, 400);
//        setLocation(600, 400);
        JPanel contain1 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JPanel contain2 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JPanel contain3 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JPanel contain4 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JPanel contain5 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JPanel contain6 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        Box vbox = Box.createVerticalBox();
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

        id.setPreferredSize(new Dimension(160,80));
        idt.setPreferredSize(new Dimension(300, 80));
        name.setPreferredSize(new Dimension(160,80));
        namet.setPreferredSize(new Dimension(300, 80));
        gredit.setPreferredSize(new Dimension(160,80));
        greditt.setPreferredSize(new Dimension(300, 80));
        classH.setPreferredSize(new Dimension(160,80));
        classHt.setPreferredSize(new Dimension(300, 80));

        teacherId.setPreferredSize(new Dimension(160,80));
        teacherIdt.setPreferredSize(new Dimension(300, 80));

        teacherName.setPreferredSize(new Dimension(160,80));
        teacherNamet.setPreferredSize(new Dimension(300, 80));

        submit.setPreferredSize(new Dimension( 250, 80));
        contain1.add(id);
        contain1.add(idt);
        contain2.add(name);
        contain2.add(namet);
        contain3.add(gredit);
        contain3.add(greditt);
        contain4.add(classH);
        contain4.add(classHt);
        contain5.add(teacherId);
        contain5.add(teacherIdt);
        contain6.add(teacherName);
        contain6.add(teacherNamet);
        vbox.add(contain1);
        vbox.add(contain2);
        vbox.add(contain3);
        vbox.add(contain4);
        vbox.add(contain5);
        vbox.add(contain6);
        vbox.add(submit);
        submit.addActionListener(this);
        _contain.add(vbox);
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
                            stm.setString(1,courses);
                            stm.setString(2,teacherIdt.getText());
                            stm.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }

    }


}
