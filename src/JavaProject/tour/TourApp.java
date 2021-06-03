package JavaProject.tour;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import util.ImageManager;
import util.newWindow;

public class TourApp extends JFrame {
	
	JPanel p_north;
	JPanel p_center;
	JScrollPane scroll;
	JLabel name;
	JLabel comm;
	JPanel p_south;
	JMenuBar bar;
	JMenu[] menu;
	String[] menu_title= {"place","room","transport","community"};
	JMenuItem[][] itemArray;
	String[][] item_title={{"intro","food"},{"hotel","others"},{"rentcar","subway"},{"chat","customer"}};
	JButton list;
	JButton logout;
	JButton[] t_place = new JButton[7];
	String[] place_title= {"seoul.png","ki.png","kang.png","chung.png","jeon.png","keong.png","jeju.png"};
	JTable table;
	ImageManager imageManager=new ImageManager();
	
	
	public TourApp() {
		
		
		//생성
		JPanel NewWindowContainer = new JPanel();
	    setContentPane(NewWindowContainer);
	    
		scroll=new JScrollPane();
		p_north=new JPanel();
		p_center=new JPanel();
		p_south=new JPanel();
		bar=new JMenuBar();
		menu=new JMenu[4];
		itemArray=new JMenuItem[2][4];
		list=new JButton("예약 내역");
		logout=new JButton("로그아웃");
		table=new JTable(5,5);
		
		for(int i=0;i<menu_title.length;i++) {
			menu[i]=new JMenu(menu_title[i]);
			bar.add(menu[i]);
		}
		
		for(int a=0;a<item_title.length;a++) {
			for(int j=0;j<2;j++) {
				menu[a].add(item_title[a][j]);
			}
		}
		setJMenuBar(bar); //프레임에 메뉴 부착!!
		
		p_north.add(list);
		p_north.add(logout);
		
		name=new JLabel("인기 여행지");
		name.setFont(new Font("Serif", Font.BOLD, 25));
		comm=new JLabel("고객센터");
		comm.setFont(new Font("Serif", Font.BOLD, 25));
		p_center.add(name);
		for(int x=0;x<place_title.length;x++) {
			t_place[x]=new JButton(imageManager.getScaledIcon(place_title[x],150, 150));
			t_place[x].setPreferredSize(new Dimension(150,150));
			p_center.add(t_place[x]);
		}
		
		//스타일
		
		
		//조립
		setLayout(new BorderLayout());
		add(scroll);
		add(p_north,BorderLayout.NORTH);
		add(p_south,BorderLayout.SOUTH);
		add(p_center,BorderLayout.CENTER);
		
		
		p_south.add(comm);
		p_south.add(table);
		
		//리스너
		list.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});
		
				
	
		
		setBounds(300,100,1300,500);
		setVisible(true);
	}
	
	

}
