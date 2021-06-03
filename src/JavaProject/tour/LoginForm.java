package JavaProject.tour;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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

public class LoginForm extends JFrame {
	
	JLabel label;
	JPanel p_north;
	JPanel p_center;
	JPanel p_south;
	JLabel la_id;
	JTextField id;
	JLabel la_pass;
	JPasswordField pass;
	JButton login;
	JButton join;
	
	Member member;
	JoinForm joinform;
	TourApp tourapp;
	//데이터베이스 관련 
	DBManager dbmanager=new DBManager();
	private boolean session;
	
	
	public LoginForm()  {
		
	
	//JPanel NewWindowContainer = new JPanel();
    //setContentPane(NewWindowContainer);
    
	p_north=new JPanel();
	label=new JLabel("로그인");
	p_center=new JPanel();
	la_id=new JLabel("e-mail");
	id=new JTextField(25);
	la_pass=new JLabel("Password");
	pass=new JPasswordField(25);
	p_south=new JPanel();
	login=new JButton("로그인");
	join=new JButton("회원가입");
	
	//스타일
	label.setPreferredSize(new Dimension(80,80));
	la_id.setPreferredSize(new Dimension(70,70));
	la_pass.setPreferredSize(new Dimension(70,70));
	
	//
	setLayout(new BorderLayout());
	add(p_north,BorderLayout.NORTH);
	add(p_center,BorderLayout.CENTER);
    add(p_south,BorderLayout.SOUTH);
    
    login.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			loginCheck();
		}
	});
    
    join.addActionListener(new ActionListener() {
		
  		public void actionPerformed(ActionEvent e) {
  			new JoinForm();
  			
  		}
  	});
	p_north.add(label);
	p_center.add(la_id);
	p_center.add(id);
	p_center.add(la_pass);
	p_center.add(pass);
	
	p_south.add(login);
	p_south.add(join);
	
	setBounds(300,100,400,500);
	setVisible(true);
	
	}
	//목록
	public void loginCheck() {
	      String sql="select * from member where m_mail=? and m_pass=?";
	      
	      PreparedStatement pstmt=null;
	      ResultSet rs = null;
	      Connection con=dbmanager.getConnection();
	      
	      try {
	         pstmt=con.prepareStatement(sql);
	         pstmt.setString(1, id.getText());
	         pstmt.setString(2, new String(pass.getPassword()));
	         rs=pstmt.executeQuery();
	         
	         //회원인지 아닌지?
	         if(rs.next()) {
	            JOptionPane.showMessageDialog(this, "인증되었습니다");
	            this.setSession(true);
	            
	            new TourApp();
	         }else {
	            JOptionPane.showMessageDialog(this, "로그인 정보가 올바르지 않습니다");
	            
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         dbmanager.release(con,pstmt, rs);
	      }
	   }
	
	public boolean isSession() {
		return session;
	}

	public void setSession(boolean session) {
		this.session = session;
	}
	
	public static void main(String[] args) {
		new LoginForm();
	}		
			
}

