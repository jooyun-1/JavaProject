package JavaProject.tour;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import JavaProject.hotel.HotelMain;

import util.ImageManager;

public class TourApp extends JFrame {
	
	JPanel p_north;
	JPanel p_center;
	JScrollPane scroll;
	JLabel name;
	JLabel comm;
	JPanel p_south;
	//JPanel p_west;
	//JLabel ing;
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
	LoginForm loginform;
	HotelMain hotelmain;
	ShowAction showaction;
	
	public TourApp() {
		
		
		//생성
		JPanel NewWindowContainer = new JPanel();
	    setContentPane(NewWindowContainer);
	    
		scroll=new JScrollPane();
		p_north=new JPanel();
		p_center=new JPanel();
		p_south=new JPanel();
		//p_west=new JPanel();
		//ing=new JLabel();
		bar=new JMenuBar();
		menu=new JMenu[4];
		itemArray=new JMenuItem[4][2];
		list=new JButton("예약 내역");
		logout=new JButton("로그아웃");
		table=new JTable(5,5);
		
		for(int i=0;i<menu_title.length;i++) {
			menu[i]=new JMenu(menu_title[i]);
			bar.add(menu[i]);
		}
		
		for(int a=0;a<item_title.length;a++) {
			for(int j=0;j<item_title[a].length;j++) {
				
				itemArray[a][j]=new JMenuItem(item_title[a][j]);
				menu[a].add(itemArray[a][j]);
				itemArray[a][j].addActionListener(new ShowAction());	
		}
		}
		setJMenuBar(bar); //프레임에 메뉴 부착!!
		//p_west.add(ing);
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
		logout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		
	
		setBounds(300,100,1300,500);
		setVisible(true);
	}
	public void logout() {
		dispose();
		new LoginForm();
	}
	class ShowAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {

					String cmd=e.getActionCommand();
					switch(cmd) {
					case "intro":
						new TourApp();
						break;
					case "food":
						new TourApp();
						break;
					case "hotel":
						new HotelMain();
						break;
					case "others":
						new TourApp();
						break;
					case "rentcar":
						new TourApp();
						break;
					case "subway":
						new TourApp();
						break;
					case "chat":
						new TourApp();
						break;
					case "customer":
						new TourApp();
						break;
					}
			}
		}

}
