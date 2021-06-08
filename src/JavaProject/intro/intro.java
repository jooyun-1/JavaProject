package JavaProject.intro;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class intro extends JFrame{
	public intro() {
	
	JPanel p_center;
	JPanel p_south;
	JButton room;
	JButton rentcar;
	JButton chat;
	JTable review;
	
	p_center=new JPanel();
	p_south=new JPanel();
	room=new JButton();
	rentcar=new JButton();
	chat=new JButton();
	review=new JTable();
	
	p_center.add(room);
	p_center.add(rentcar);
	p_center.add(chat);
	p_south.add(review);
	
	setLayout(new BorderLayout());
	setBounds(600,300,600,600);
	setVisible(true);
	}
}
