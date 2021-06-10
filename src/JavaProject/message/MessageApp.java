package JavaProject.message;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import JavaProject.tour.DBManager;
import JavaProject.tour.Member;
import JavaProject.tour.TourApp;


public class MessageApp extends JFrame {
   JPanel p_east, p_west, p_center;

   JPanel la_panel;
   JLabel la_text;

   JTable table; // 테이블 나올 곳
   JScrollPane t_scroll;

   // 동쪽

   JPanel empty, emptyw; // 띄어쓰기
   JLabel t_text, t_textw;

   JPanel empty3, empty3w;
   JLabel la_title ,la_titlew; // 제목
   JTextField titlee, titlew;
   JLabel la_writer, la_writerw; // 작성자
   JTextField writere, writerw;
   JPanel empty4, empty4w;

   JLabel la_content, la_contentw;
   JTextArea contente, contentw; // 문의내용
   JScrollPane scroll, scrollw;
   JPanel bt_empty, bt_emptyw;
   JButton bt_del, bt_registw;
   JButton bt_registc, bt_registcw;
  

   //String[] columnName = { "ID", "title", "writer", "content", "regdate", "hit" };
   //String[][] rows = {};

   DBManager dbmanager=new DBManager();
   Connection con; // import sql꺼 갖고 오기 , mysql 말구
   MessageModel messagemodel;
   TourApp tourapp;
   int message_id;
   
   public MessageApp(TourApp tourapp) {
     this.tourapp= tourapp;
      
      // 상단바 & 테이블
      p_center = new JPanel();
      p_center.setBackground(Color.WHITE);
      
      la_panel = new JPanel(); // 검색패널
      la_panel.setBackground(Color.WHITE);

      table = new JTable();

      t_scroll = new JScrollPane(table);
      la_text = new JLabel("Review contents"); // 검색창
      la_text.setForeground(Color.DARK_GRAY);
     
      p_east = new JPanel();
      p_east.setBackground(Color.WHITE);
      
      empty = new JPanel();
      empty.setBackground(Color.WHITE);
      
      t_text = new JLabel("[ 나의 리뷰 내역 ]");
      t_text.setForeground(Color.DARK_GRAY);
      
      empty3 = new JPanel();
      empty3.setBackground(Color.WHITE);
      
      la_title = new JLabel("제목");
      la_title.setForeground(Color.DARK_GRAY);
      titlee = new JTextField(25);
      
      la_writer = new JLabel("작성자");
      la_writer.setForeground(Color.DARK_GRAY);
      writere = new JTextField(25);

      // 요청사항
      // empty4 = new JPanel();
      la_content = new JLabel("내용");
      la_content.setForeground(Color.DARK_GRAY);
      
      Border border = BorderFactory.createLineBorder(Color.DARK_GRAY);
      contente = new JTextArea("--내용을 입력하세요--");
      contente.setBorder(border);
     
      
      scroll = new JScrollPane(contente);

      bt_empty = new JPanel();
      bt_empty.setBackground(Color.WHITE);
      
      bt_del = new JButton("삭제하기");
      bt_del.setBackground(Color.DARK_GRAY);
      bt_del.setForeground(Color.WHITE);
      
      bt_registc = new JButton("수정하기");
      bt_registc.setBackground(Color.DARK_GRAY);
      bt_registc.setForeground(Color.WHITE);
      
      // 서쪽영역
      p_west = new JPanel();
      p_west.setBackground(Color.WHITE);
      
      emptyw = new JPanel();
      emptyw.setBackground(Color.WHITE);
      
      t_textw = new JLabel("[ 리뷰 등록 ]");
      t_textw.setForeground(Color.DARK_GRAY);
      

      empty3w = new JPanel();
      empty3w.setBackground(Color.WHITE);
      
      la_titlew = new JLabel("제목");
      la_titlew.setForeground(Color.DARK_GRAY);
      
      titlew = new JTextField(25);
      
      la_writerw = new JLabel("작성자");
      la_writerw.setForeground(Color.DARK_GRAY);
      
      writerw = new JTextField(25);

      // 요청사항
      la_contentw = new JLabel("내용");
      la_contentw.setForeground(Color.DARK_GRAY);
      
      Border border2 = BorderFactory.createLineBorder(Color.DARK_GRAY);
      contentw = new JTextArea("--내용을 입력하세요--");
      contentw.setBorder(border2);
     
      
      scrollw = new JScrollPane(contente);

      bt_emptyw = new JPanel();
      bt_emptyw.setBackground(Color.WHITE);
      
      bt_registw = new JButton("등록하기");
      bt_registw.setBackground(Color.DARK_GRAY);
      bt_registw.setForeground(Color.WHITE);
      
      bt_registcw = new JButton("게시판 목록");
      bt_registcw.setBackground(Color.DARK_GRAY);
      bt_registcw.setForeground(Color.WHITE);
      

      

      // 스타일 및 레이아웃

      la_text.setFont(new Font("Vredana", Font.BOLD, 30));
      la_title.setFont(new Font("Vredana", Font.BOLD, 15));
      la_writer.setFont(new Font("Vredana", Font.BOLD, 15));
      la_content.setFont(new Font("Vredana", Font.BOLD, 15));
      la_titlew.setFont(new Font("Vredana", Font.BOLD, 15));
      la_writerw.setFont(new Font("Vredana", Font.BOLD, 15));
      la_contentw.setFont(new Font("Vredana", Font.BOLD, 15));
      setLayout(new BorderLayout());
      t_scroll.setPreferredSize(new Dimension(500, 400));

      // 상단
      p_center.setPreferredSize(new Dimension(30, 30));
      contente.setPreferredSize(new Dimension(300, 80));
      contentw.setPreferredSize(new Dimension(300, 80));

      // 정보
      p_east.setLayout(new FlowLayout());
      p_east.setPreferredSize(new Dimension(380, 500));
      p_west.setLayout(new FlowLayout());
      p_west.setPreferredSize(new Dimension(380, 500));

      // 예약정보 글씨
      t_text.setFont(new Font("ITALIC", Font.BOLD, 20));
      scroll.setPreferredSize(new Dimension(180, 180));
      empty.setPreferredSize(new Dimension(70, 100));
      t_textw.setFont(new Font("ITALIC", Font.BOLD, 20));
      scrollw.setPreferredSize(new Dimension(180, 180));
      emptyw.setPreferredSize(new Dimension(70, 100));

      bt_empty.setPreferredSize(new Dimension(380, 4));
      la_title.setPreferredSize(new Dimension(200, 50));
      bt_emptyw.setPreferredSize(new Dimension(380, 4));
      la_titlew.setPreferredSize(new Dimension(200, 50));
      p_center.setLayout(new FlowLayout());
      

      Dimension a = new Dimension(380, 40);
      empty3.setPreferredSize(a);
      empty3w.setPreferredSize(a);
      // empty4.setPreferredSize(a);

      Dimension z = new Dimension(120, 30);
      la_title.setPreferredSize(z);
      la_writer.setPreferredSize(z);
      la_content.setPreferredSize(z);
      la_titlew.setPreferredSize(z);
      la_writerw.setPreferredSize(z);
      la_contentw.setPreferredSize(z);
      

      Dimension e = new Dimension(200, 30);
      titlee.setPreferredSize(e);
      writere.setPreferredSize(e);
      titlew.setPreferredSize(e);
      writerw.setPreferredSize(e);

      p_center.add(la_panel);
      la_panel.add(la_text);
      p_center.add(t_scroll);
      // p_east.add(empty);
      p_east.add(t_text);
      p_east.add(empty3);
      p_east.add(la_title);
      p_east.add(titlee);
      p_east.add(la_writer);
      p_east.add(writere);
      p_east.add(la_content);
      // p_east.add(empty4);
      p_east.add(contente);
      p_east.add(bt_empty);
      p_east.add(bt_del);
      p_east.add(bt_registc);
      // p_west.add(emptyw);
      p_west.add(t_textw);
      p_west.add(empty3w);
      p_west.add(la_titlew);
      p_west.add(titlew);
      p_west.add(la_writerw);
      p_west.add(writerw);
      p_west.add(la_contentw);
      // p_west.add(empty4w);
      p_west.add(contentw);
      p_west.add(bt_emptyw);
      p_west.add(bt_registw);
      p_west.add(bt_registcw);
    

      add(p_center, BorderLayout.NORTH);
      add(p_east, BorderLayout.EAST);
      add(p_west, BorderLayout.WEST);
      add(p_center);

      
      
     //이벤트
      
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            
            dbmanager.release(con);;
         }
         
      
      });
      
      
      //등록버튼 구현
      bt_registw.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            regist();
            getList(); // 목록 가져오기
            
            table.updateUI();
         }
      });
      
      //게시판목록버튼 구현
      bt_registcw.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            getList(); // 목록 가져오기
            table.updateUI();
         }
      });
      

      //테이블
      table.addMouseListener(new MouseAdapter() {
             public void mouseReleased(MouseEvent e) {
                //String car_type=(String) table.getValueAt(table.getSelectedRow(), 0);
                String title=(String) table.getValueAt(table.getSelectedRow(), 0);
                String writer=(String) table.getValueAt(table.getSelectedRow(), 1);
                String content=(String)table.getValueAt(table.getSelectedRow(), 2);
               
                titlee.setText(title);
                writere.setText(writer);
                contente.setText(content);
             }
          });   
         
      //삭제하기
   
      bt_del.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(JOptionPane.showConfirmDialog(MessageApp.this, "삭제하시겠어요?")==JOptionPane.OK_OPTION) {
               
               deleteList();
               table.updateUI();
            }
         
         }
      });
      
      //수정하기
      bt_registc.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         
               
            if(JOptionPane.showConfirmDialog(MessageApp.this, "수정하시겠어요?")==JOptionPane.OK_OPTION) {
               updateList();
               getList();
               table.updateUI();
               
            }
         }
      });
      
      
      setBounds(300, 100, 1300, 600);
       setVisible(true);
        
         
        
       
   }
   
   //등록하기
   public void regist() {
      Member member= tourapp.getMember();
      String sql="insert into message(title,m_name,content) values(?,?,?)";
      PreparedStatement pstmt=null;
      con=dbmanager.getConnection();
       
      try {
         pstmt=con.prepareStatement(sql);
         pstmt.setString(1, titlew.getText());
         pstmt.setString(2, member.getM_name());
         pstmt.setString(3, contentw.getText());
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
   
   
   //목록
   public void getList() {
      Vector<Message> vec=new Vector<Message>(); 
      String sql="select * from message order by message_id desc";
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      ResultSetMetaData meta; //컬럼 정보 등을 가져오기 위한 객체
      messagemodel = new MessageModel();
      con=dbmanager.getConnection();
      
      try {
         pstmt=con.prepareStatement(sql);
         rs=pstmt.executeQuery(); 
         meta = rs.getMetaData();
         
         /*
         int col_count = meta.getColumnCount(); 
         
         for(int i=1;i<=col_count;i++) {
            String name=meta.getColumnName(i);
            System.out.println(name);
            messagemodel.column.add(name); 
         }
         */
         
         while(rs.next()) {
            Message message = new Message();
            
            message.setMessage_id(rs.getInt("message_id"));
            message.setTitle(rs.getString("title"));
            message.setWriter(rs.getString("m_name"));
            message.setContent(rs.getString("content"));
            message.setRegdate(rs.getString("regdate"));
            message.setHit(rs.getInt("hit"));
            
            vec.add(message);//한건의 레코드를 담은 VO를 백터에 추가하자
         }
         //Model에 들어있는 메서드들은, Table에 해당 모데 적용시점에 호출되는 것을 알 수 있다.
         //이때 JTable 원하는 정보를 모델로부터 얻어간다!
         messagemodel.data=vec; //신규 데이터로 교체 
         table.setModel(messagemodel); // JTable의 생성자에서 모델을 결정하는게 아니라, 생성된 모델중 원하는 모델을
                                  // 테이블에 적용시키고 싶을때
         table.updateUI();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         dbmanager.release(con,pstmt,rs);
      }
      
   }
   
   
   
   //삭제
   public void deleteList() {
      
      
      //String member= tourapp.getMember().getM_name();
      
      String title= (String)table.getValueAt(table.getSelectedRow(), 0);
      String m_name= (String)table.getValueAt(table.getSelectedRow(), 1);
      String content= (String)table.getValueAt(table.getSelectedRow(), 2);
      String sql="delete from message where title=?";
      
      PreparedStatement pstmt=null;
      con=dbmanager.getConnection();
      
      //int price= Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 3));
      
      try {
         pstmt=con.prepareStatement(sql);
         pstmt.setString(1, title);
         int result=pstmt.executeUpdate(); // DML 중 delete 수행
         if(result >0) {
            //파일 삭제!!
            JOptionPane.showMessageDialog(this, "삭제 완료");
            getList(); // 리스트 다시 조회
         }
      } catch (SQLException e) {
            e.printStackTrace();
      }finally {
         dbmanager.release(con,pstmt);
      }
   }
   
   
   //수정
   public void updateList() {
      String sql="update message set title=?, m_name=?, content=? where message_id=?";
      PreparedStatement pstmt=null;
      con=dbmanager.getConnection();
      try {
         pstmt=con.prepareStatement(sql);
         
         //int message_id=Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 0));
         String title= (String)table.getValueAt(table.getSelectedRow(),0);
         //String writer= (String)table.getValueAt(table.getSelectedRow(),1);
         //String content= (String)table.getValueAt(table.getSelectedRow(),2);
         
         //현재 내가 선택한인덱스와 일ㅊ치하는 데이터 추출 
         int row = table.getSelectedRow(); // 0,1,2,
         
         MessageModel model = (MessageModel)table.getModel();
         Message message=model.data.get(row);
         sql="update message set title='"+titlee.getText()+"', m_name='"+writere.getText()+"', content='"+contente.getText()+"' where message_id="+message.getMessage_id();
         
         System.out.println(sql);
         
         //pstmt.setInt(1, message_id);//price
         pstmt.setString(1, titlee.getText());
         pstmt.setString(2, writere.getText());//detail
         pstmt.setString(3, contente.getText());//filename
         pstmt.setInt(4, message.getMessage_id());
      
         int result = pstmt.executeUpdate(); //DML 실행
         if(result>0) {
            JOptionPane.showMessageDialog(this,"수정 완료");
         }
      } catch (SQLException e) {
            e.printStackTrace();
      }finally {
         dbmanager.release(con,pstmt);
   
   }
      
   }   
}