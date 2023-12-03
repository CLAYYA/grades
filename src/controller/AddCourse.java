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
     * ��ʦ���ӿγ�
     */


    JPanel contain;
    JButton submit;
    JLabel id, name, gredit, classH, teacherId, teacherName;
    JTextField idt, namet, greditt, classHt, teacherIdt, teacherNamet;

    public AddCourse() {
        super("���ӿγ�");
        setSize(400, 400);
        setLocation(600, 400);
        contain = new JPanel();
        contain.setLayout(null);
        id = new JLabel("�γ̺�");
        name = new JLabel("�γ���");
        gredit = new JLabel("ѧ��");
        classH = new JLabel("ѧʱ");

        teacherId = new JLabel("��ʦ���");
        teacherName = new JLabel("��ʦ����");

        submit = new JButton("�ύ");
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

    //ʹ�����ݿⷽ���汾
    public int hasCourse(String id) {//  ��ʦ����ǰ���γ��Ƿ��Ѿ�����
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
    //��Ϊ���ݿ�洢
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if ((idt.getText().equals("")) || (namet.getText().equals("")) || (greditt.getText().equals(""))
                    || (classHt.getText().equals("")) || teacherIdt.getText().equals("") || teacherNamet.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "��Ϣ����Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if ((hasCourse(idt.getText())) == 1) {
                    JOptionPane.showMessageDialog(null, "�˿γ��Ѿ����ڣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //���ݿⲿ��
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
                        //ͬ����User��
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
