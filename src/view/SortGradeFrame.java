package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.GradeSort;


@SuppressWarnings("serial")
public class SortGradeFrame extends JPanel implements ActionListener{
	
	JPanel contain;
	JLabel id, pass, good, excellent;
	JTextField idt, passt, goodt, excellentt;
	
	JButton submit, bn;
	JPanel _contain,__contain,huge;
	JFrame self;
	int[] result = null;
	
	public SortGradeFrame(JPanel huge,JPanel _contain,JPanel __contain,JFrame self){

//		super("输入课程号和成绩标准");
//		setSize(300, 300);
//		setLocation(600, 400);
		this.huge = huge;
		this.self = self;
		this.__contain =__contain;
		this._contain = _contain;
		contain = new JPanel();
		contain.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
		_contain.add(contain);
		id = new JLabel("课程号");
		idt = new JTextField();
		
		pass = new JLabel("及格");
		passt = new JTextField();
		good = new JLabel("良好");
		goodt = new JTextField();
		excellent = new JLabel("优秀");
		excellentt = new JTextField();
		
		submit = new JButton("提交");
		id.setPreferredSize(new Dimension(160,80));
		idt.setPreferredSize(new Dimension(300,80));
		
		pass.setPreferredSize(new Dimension(160,80));
		passt.setPreferredSize(new Dimension(300,80));
		good.setPreferredSize(new Dimension(160,80));
		goodt.setPreferredSize(new Dimension(300,80));
		excellent.setPreferredSize(new Dimension(160,80));
		excellentt.setPreferredSize(new Dimension(300,80));
		
		submit.setPreferredSize(new Dimension( 250, 80));
		contain.add(id);
		contain.add(idt); 
		
		contain.add(pass);
		contain.add(passt);
		contain.add(good);
		contain.add(goodt);
		contain.add(excellent);
		contain.add(excellentt);
		
		contain.add(submit);
		submit.addActionListener(this);
		setVisible(true);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		
		idt.setText("");
		passt.setText("");
		goodt.setText("");
		excellentt.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submit) {
			if ((idt.getText().equals("")) || (passt.getText().equals(""))|| (goodt.getText().equals(""))|| (excellentt.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "信息不能为空！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				
			}else if(new GradeSort(idt.getText(), Float.parseFloat(passt.getText()),
					Float.parseFloat(goodt.getText()), Float.parseFloat(excellentt.getText())).isValidate() == 1){
				
				JOptionPane.showMessageDialog(null, "成绩标准设置错误！", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				
			}
			else {
				GradeSort gradeSort = new GradeSort(idt.getText(), Float.parseFloat(passt.getText()),
						Float.parseFloat(goodt.getText()), Float.parseFloat(excellentt.getText()));
				
				if (gradeSort.hasCourse()==0) {
					JOptionPane.showMessageDialog(null, "此课程不存在！", "提示",
							JOptionPane.INFORMATION_MESSAGE);
				}else{
				
				this.result = gradeSort.sortGrade();
				showResult();
				}
			}

		}
	}
	
	
	
	void showResult(){
		
		JLabel fm = new JLabel("成绩统计结果");
		fm.setSize(300, 340);

		
		JLabel fail = new JLabel("不及格");
		JLabel pass = new JLabel("及格");
		JLabel good = new JLabel("良好");
		JLabel excellent = new JLabel("优秀");
		Box vbox = Box.createVerticalBox();
		JPanel contain1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel contain4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JTextField failt = new JTextField();
		JTextField passt = new JTextField();
		JTextField goodt = new JTextField();
		JTextField excellentt = new JTextField();
		
		fail.setPreferredSize(new Dimension(160,80));
		failt.setPreferredSize(new Dimension(300,80));
		pass.setPreferredSize(new Dimension(160,80));
		passt.setPreferredSize(new Dimension(300,80));
		good.setPreferredSize(new Dimension(160,80));
		goodt.setPreferredSize(new Dimension(300,80));
		excellent.setPreferredSize(new Dimension(160,80));
		excellentt.setPreferredSize(new Dimension(300,80));
		
		contain1.add(fail);
		contain1.add(failt);
		contain2.add(pass);
		contain2.add(passt);
		contain3.add(good);
		contain3.add(goodt);
		contain4.add(excellent);
		contain4.add(excellentt);
		vbox.add(fm);
		vbox.add(contain1);
		vbox.add(contain2);
		vbox.add(contain3);
		vbox.add(contain4);
		__contain.add(vbox);
		
		failt.setText(Integer.toString(this.result[0])+"人");
		passt.setText(Integer.toString(this.result[1])+"人");
		goodt.setText(Integer.toString(this.result[2])+"人");
		excellentt.setText(Integer.toString(this.result[3])+"人");

		huge.updateUI();
		self.pack();

		__contain.setVisible(true);
		
	}
	
	
	
	
}
