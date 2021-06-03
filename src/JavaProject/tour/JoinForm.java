package JavaProject.tour;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class JoinForm extends JFrame {
		
	   //제목
	   JPanel p_title;
	   JLabel la_title;
	   
	   JPanel p_center;
	   //이메일
	   JLabel la_email;
	   JTextField m_mail;
	   //JLabel la_at;
	   //Choice ch_server;
	   //비밀번호
	   JLabel la_pass;
	   JPasswordField m_pass;
	   //이름
	   JLabel la_name;
	   JTextField m_name;
	   
	   JPanel p_south;
	   JButton bt_regist;
	   
	   JLabel la_number;
	   JTextField m_number;
	   boolean flag=false;
	   
	   Member member;
	   DBManager dbmanager=new DBManager();
	   
	
	   public JoinForm() {
		   
		   
		   	
			JPanel NewWindowContainer = new JPanel();
		    setContentPane(NewWindowContainer);
		    
		    //생성
		      p_title=new JPanel();
		      la_title=new JLabel("회원가입");
		      
		      la_email=new JLabel("email");
		      m_mail=new JTextField(25);
		      //la_at=new JLabel("@");
		     //ch_server=new Choice();
		      
		      la_pass=new JLabel("password");
		      m_pass=new JPasswordField(25);
		      
		      la_name=new JLabel("이름");
		      m_name=new JTextField(25);
		      
		      la_number=new JLabel("전화번호");
		      m_number=new JTextField(25);
		      bt_regist=new JButton("등록");
		   

		   	setLayout(new FlowLayout());
		      la_title.setFont(new Font("돋음",Font.BOLD,28));
		      p_title.setPreferredSize(new Dimension(450,50));
		      
		      Dimension d =new Dimension(150,30);
		      la_email.setPreferredSize(d);
		      //la_at.setPreferredSize(new Dimension(20,30));
		      m_mail.setPreferredSize(new Dimension(120,30));
		      la_pass.setPreferredSize(d);
		      m_pass.setPreferredSize(new Dimension(350,30));
		      la_name.setPreferredSize(d);
		      m_name.setPreferredSize(new Dimension(350,30));
		      la_number.setPreferredSize(d);
		      m_number.setPreferredSize(new Dimension(350,30));
		      
		      //ch_server.setPreferredSize(new Dimension(130,30));
			
	        
			/*addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					disConnect(); //DB 접속해제
					System.exit(0); //kill process
				}
			});*/
			
			bt_regist.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) {
					
					if(flag==false) {
						checkValue();
					}else {
					regist();					
				}
			}
			});
			
			 //조립
		      p_title.add(la_title);
		      add(p_title);
		      
		      add(la_email);
		      add(m_mail);
		      //add(la_at);
		      //add(ch_server);
		      add(la_pass);
		      add(m_pass);
		      add(la_name);
		      add(m_name);
		      add(la_number);
		      add(m_number);
		      add(bt_regist);
		      /*ch_server.add("gmail.com");
		      ch_server.add("naver.com");
		      ch_server.add("daum.net");
		      ch_server.add("direct insert");*/


	      
	      //보여주기
		setLocationRelativeTo(null);
	      setBounds(400,400,500,400);
	      setVisible(true);
	      
	   }
	 public void checkValue() {
		 String pass=new String(m_pass.getPassword());
		 
		 if(m_mail.getText().length()==0) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
				m_mail.requestFocus();//커서 올려놓기
				flag=false;
			}else if(pass.length()<8) {
				JOptionPane.showMessageDialog(this, "비밀번호를 8자이상 입력하세요");
				m_pass.requestFocus();
				flag=false;
			}else if(m_name.getText().length()==0) {
				JOptionPane.showMessageDialog(this, "이름을 입력하세요");
				m_name.requestFocus();
				flag=false;
			}else if(m_number.getText().length()<11) {
				JOptionPane.showMessageDialog(this, "정확한 핸드폰 번호를 입력하세요");
				flag=false;
			}else {
				flag=true;
			}
	 }
		//Create(=insert) Read(=select) Update Delete
		//등록
		public void regist() {
			String sql="insert into member(m_mail,m_pass,m_name,m_number) values(?,?,?,?)";
			PreparedStatement pstmt=null;
			Connection con=dbmanager.getConnection();
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, m_mail.getText());
				pstmt.setString(2, new String(m_pass.getPassword()));
				pstmt.setString(3, m_name.getText());
				pstmt.setString(4, m_number.getText());
				int result=pstmt.executeUpdate();
				if(result==1) {
					JOptionPane.showMessageDialog(this, "등록성공");
				}else {
					JOptionPane.showMessageDialog(this, "등록실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbmanager.release(con,pstmt);
			}
		}
	
	

	
}
