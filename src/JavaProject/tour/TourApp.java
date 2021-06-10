package JavaProject.tour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import JavaProject.chat.MainChat;
import JavaProject.chat.Server;
import JavaProject.hotel.HotelMain;
import JavaProject.hotelreserv.Hotelres;
import JavaProject.intro.Intro;
import JavaProject.message.MessageApp;
import JavaProject.rentcar.CarMain;


public class TourApp extends JFrame{
   
   JMenuBar bar;
   JMenu[] menu;
   String[] menu_title= {"place","reservation","community"};
   JMenuItem[][] itemArray;
   String[][] item_title={{"intro"},{"hotel","rentcar"},{"chat","customer"}};
   ShowAction showaction;
   JScrollPane scrollPane;
   ImageIcon i = new ImageIcon("D:\\workspace\\korea202102_javaworkspace\\JavaProject\\res\\Main.jpg");
    Image im=i.getImage();
    //JPanel p_center;
    JButton list;
   JButton logout;
   JButton re;
   JPanel p_north;
   MessageApp messageapp;
   
   
   
   private boolean session;    
   private Member member;
   LoginForm loginform;
   HotelMain hotelmain;
    
    
    
    
    public TourApp(Member member) {
       this.member=member; 
       
       //JFrame frm = new JFrame("프로의 BLOG"); 
        this.setTitle("Tour");
        
        bar=new JMenuBar();
      menu=new JMenu[3];
      itemArray=new JMenuItem[3][2];
        
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
      setJMenuBar(bar);
      
       JPanel p_north = new JPanel();
         list = new JButton("Reservation");
         re = new JButton("REVIEW");
        logout = new JButton("LOGOUT");
        
        list.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent e) {
				new Hotelres(TourApp.this);
				
			}
		});
        re.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new MessageApp(TourApp.this);
			}
		});
        logout.addActionListener(new ActionListener() {
			
    			
    			public void actionPerformed(ActionEvent e) {
    				
    				logout();
    			}
    		});
         
        
        MyPanel panel = new MyPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(1300,500));
        p_north.setPreferredSize(new Dimension(35,35));
       
        
        
        p_north.add(panel);
        p_north.add(list);
        p_north.add(logout);
        p_north.add(re);
   
          
        add(panel,BorderLayout.CENTER);
        add(p_north, BorderLayout.SOUTH);
        p_north.setBackground(Color.white);
        bar.setBackground(Color.white);

        list.setBackground(Color.white);
        logout.setBackground(Color.white);
        re.setBackground(Color.white);

       
        
        //scrollPane = new JScrollPane(panel);
       
       JLabel label = new JLabel("<HTML><body style='text-align:center;'><br><br><br><br><br><br><br>더 넓은 세상을 경험하고, 잊지 못할 순간을 예약하세요<br>YOURS TO EXPLORE</body></HTML>");
        //JButton bt1 = new JButton("<HTML><body style='text-align:center;'></body></HTML>");
       label.setFont(new Font("Serif", Font.BOLD, 25));
       label.setForeground(Color.WHITE);
       label.setHorizontalAlignment(JLabel.CENTER); //JLabel 가운데 정렬
       //JLabel 가운데 정렬
       //label.setHorizontalAlignment(JLabel.RIGHT);
           
       panel.add(label);
       
        
 
       
       
        this.add(panel);
        setBounds(300,100,1300,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
       //setContentPane(scrollPane);
       
    }
 
   class MyPanel extends JPanel{
            
        public void paintComponent(Graphics g){
            //super.paintComponent(g);
            g.drawImage(im,0,0,getWidth(),getHeight(),this);
       
        }
    }
   public void logout() {
      dispose();
      this.setSession(false);
      loginform=null;
      member=null;
   }
   
   public void list() {
      new Hotelres(TourApp.this);
   }
   
   
   public Member getMember() {
      return member;
   }
   public void setMember(Member member) {
      this.member = member;
   }
   public boolean isSession() {
      return session;
   }

   public void setSession(boolean session) {
      this.session = session;
   }

   class ShowAction implements ActionListener{
         public void actionPerformed(ActionEvent e) {

                  String cmd=e.getActionCommand();
                  switch(cmd) {
                  case "intro":
                     new Intro(TourApp.this);
                     break;
                
                  case "hotel":
                     new HotelMain(TourApp.this);
                     break;
                  
                  case "rentcar":
                     new CarMain(TourApp.this);
                     break;
            
                  case "chat":
                     new Server(TourApp.this);
                     new MainChat(TourApp.this);
                     break;
                  case "customer":
                     new MessageApp(TourApp.this);
                     break;
                  }
            }
   }
}