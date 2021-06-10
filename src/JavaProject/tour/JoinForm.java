package JavaProject.tour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

public class JoinForm extends JFrame {
   
   JLabel la_title;
   JPanel p_north;
   JPanel p_center;
   JPanel p_south;
   JLabel la_email;
   JTextField  m_mail;
   JLabel la_pass;
   JPasswordField m_pass;
   JLabel la_name;
   JTextField m_name;
   JLabel la_number;
   JTextField m_number;
   JButton bt_regist;
   
   
    boolean flag=false;
    Member member;
    DBManager dbmanager=new DBManager();
   
   
   
   public JoinForm()  {
      
   
   //JPanel NewWindowContainer = new JPanel();
    //setContentPane(NewWindowContainer);
    
   p_north=new JPanel();
   la_title=new JLabel("Join");
   p_center=new JPanel();
   la_email=new JLabel("e-mail");
   m_mail=new JTextField(25);
   la_pass=new JLabel("Password");
   m_pass=new JPasswordField(25);
   la_name=new JLabel("name");
   m_name=new JTextField(25);
   la_number=new JLabel("전화번호");
   m_number=new JTextField(25);
   p_south=new JPanel();
   bt_regist=new JButton("회원가입");
   
   
   //스타일
   la_title.setPreferredSize(new Dimension(120,50));
   m_mail.setPreferredSize(new Dimension(150,30));
   m_pass.setPreferredSize(new Dimension(150,30));
   m_name.setPreferredSize(new Dimension(150,30));
   m_number.setPreferredSize(new Dimension(150,30));
   //id.setBackground(Color.ORANGE);
   //pass.setBackground(Color.orange);
   la_email.setPreferredSize(new Dimension(150,50));
   la_pass.setPreferredSize(new Dimension(150,50));
   la_name.setPreferredSize(new Dimension(150,50));
   la_number.setPreferredSize(new Dimension(150,50));
   
   
   bt_regist.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           
           if(flag==false) {
              checkValue();
           }else {
           regist();               
        }
     }
     });
   
   
    la_title.setFont(new Font("ITALIC", Font.BOLD, 24));
   bt_regist.setForeground(Color.WHITE);
   
   
   p_north.setBackground(Color.WHITE);
   p_center.setBackground(Color.WHITE);
   p_south.setBackground(Color.WHITE);
   bt_regist.setBackground(Color.darkGray);
   
   
   //
   setLayout(new BorderLayout());
   add(p_north,BorderLayout.NORTH);
   add(p_center,BorderLayout.CENTER);
    add(p_south,BorderLayout.SOUTH);
    

   p_north.add(la_title);
   p_center.add(la_email);
   p_center.add( m_mail);
   p_center.add(la_pass);
   p_center.add(m_pass);
   p_center.add(la_name);
   p_center.add(m_name);
   p_center.add(la_number);
   p_center.add(m_number);
   
   p_south.add(bt_regist);
   
   
   setLocationRelativeTo(null);
    setBounds(400,400,450,400);
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
                  JOptionPane.showMessageDialog(this, "회원가입이 완료되셨습니다");
                  dispose();
               }else {
                  JOptionPane.showMessageDialog(this, "다시 입력하여주세요");
               }
            } catch (SQLException e) {
               e.printStackTrace();
            }finally {
               dbmanager.release(con,pstmt);
            }
         }
      
      

      
   }