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

//		super("����γ̺źͳɼ���׼");
//		setSize(300, 300);
//		setLocation(600, 400);
		this.huge = huge;
		this.self = self;
		this.__contain =__contain;
		this._contain = _contain;
		contain = new JPanel();
		contain.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
		_contain.add(contain);
		id = new JLabel("�γ̺�");
		idt = new JTextField();
		
		pass = new JLabel("����");
		passt = new JTextField();
		good = new JLabel("����");
		goodt = new JTextField();
		excellent = new JLabel("����");
		excellentt = new JTextField();
		
		submit = new JButton("�ύ");
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
				JOptionPane.showMessageDialog(null, "��Ϣ����Ϊ�գ�", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
				
			}else if(new GradeSort(idt.getText(), Float.parseFloat(passt.getText()),
					Float.parseFloat(goodt.getText()), Float.parseFloat(excellentt.getText())).isValidate() == 1){
				
				JOptionPane.showMessageDialog(null, "�ɼ���׼���ô���", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
				
			}
			else {
				GradeSort gradeSort = new GradeSort(idt.getText(), Float.parseFloat(passt.getText()),
						Float.parseFloat(goodt.getText()), Float.parseFloat(excellentt.getText()));
				
				if (gradeSort.hasCourse()==0) {
					JOptionPane.showMessageDialog(null, "�˿γ̲����ڣ�", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE);
				}else{
				
				this.result = gradeSort.sortGrade();
				showResult();
				}
			}

		}
	}
	
	
	
	void showResult(){
		
		JLabel fm = new JLabel("�ɼ�ͳ�ƽ��");
		fm.setSize(300, 340);

		
		JLabel fail = new JLabel("������");
		JLabel pass = new JLabel("����");
		JLabel good = new JLabel("����");
		JLabel excellent = new JLabel("����");
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
		
		failt.setText(Integer.toString(this.result[0])+"��");
		passt.setText(Integer.toString(this.result[1])+"��");
		goodt.setText(Integer.toString(this.result[2])+"��");
		excellentt.setText(Integer.toString(this.result[3])+"��");

		huge.updateUI();
		self.pack();

		__contain.setVisible(true);
		
	}
	
	
	
	
}
