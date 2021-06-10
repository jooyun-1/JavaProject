package JavaProject.rentcar;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import JavaProject.tour.DBManager;
import JavaProject.tour.TourApp;




public class CarMain extends JFrame{
   
   JPanel p_east,p_westm,p_center;

   JPanel p_search; //검색 패널
   JTextField t_keyword;//검색어 입력
   JButton bt_search; 
   
   JTable table; // 테이블 나올 곳
   JScrollPane t_scroll;

   //동쪽
   Choice car; //차량선택 
   Choice option; // 차량 종류
   JPanel empty; // 띄어쓰기
   JLabel c_option; //차량옵션 라벨 
   
   JPanel empty3;
   JLabel c_name;
   JTextField car_name;
   JLabel la_pick; //픽업장소
   JTextField pick;
   JLabel la_return; //반납장소
   JTextField r_return;
   JLabel la_time; //이용시간
   JTextField time;
   JLabel la_price; //가격
   JTextField price;
   
   JPanel empty4;
   JLabel driver;
   
   JTextArea detail; //문의내용
   JScrollPane scroll;
   JPanel bt_empty;
   JButton bt_regist;
   
   
   
   String[] columnName = { "차 종류", "차 이름",  "이용시간",  "이용금액"};
   String[][] rows = {};
   int car_id; 
   DBManager dbmanager=new DBManager();
   Connection con=dbmanager.getConnection();
   //ArrayList<CarData> carList=new ArrayList<CarData>(); //size 0 즉 아무것도 채워진게 없다
   //ArrayList<CarDetail> subList=new ArrayList<CarDetail>();
   TourApp tourApp;
   
   public  CarMain(TourApp tourApp) {
   this.tourApp=tourApp;  
   
     //상단바 & 테이블
      p_center= new JPanel();
      p_search = new JPanel(); //검색패널

      table = new JTable(new AbstractTableModel() {

      // 총 레코드 수를 반환하는 메서드 그리고 이 메서드는 JTable이 호출하는 것이다!!
      public int getRowCount() {
         return rows.length;
      }

      // 총 컬럼수를 반환하는 메서드, 그리고 이 메서드 또한 JTable이 호출하는 거다!!
      public int getColumnCount() {
         return columnName.length;
      }

      public String getColumnName(int col) { // col 변수에 호출시마다 1씩 증가시키며 호출 0,1,2,3,4
         return columnName[col];
      }

      
      public Object getValueAt(int row, int col) {
         //System.out.println("getValueAt(" + row + "," + col + ")");
         return rows[row][col];
      }
   });
   
      
      t_scroll=new JScrollPane(table);
      t_keyword = new JTextField(); //검색창
      bt_search = new JButton("검색"); //검색버튼
   
      //동쪽 영역 (차량정보 등)
      p_east= new JPanel();
      

      empty = new JPanel();
      c_option = new JLabel("차량 예약 정보");

      

      
      empty3 = new JPanel();
      c_name=new JLabel("차 종류");
      car_name=new JTextField(25);
      la_pick = new JLabel("픽업장소");
      pick = new JTextField(25);
      la_return = new JLabel("반납장소");
      r_return = new JTextField(25);
      la_time = new JLabel("이용시간");
      time = new JTextField(25);
      la_price = new JLabel("이용가격");
      price = new JTextField(25);
      
      //요청사항
      empty4 = new JPanel();
      driver =new JLabel("고객 요청 사항");
      detail = new JTextArea("--요청사항--");
      scroll = new JScrollPane(detail);
      
      
      bt_empty = new JPanel();
      bt_regist = new JButton("예약하기");
     
      
      
      
      
      //스타일 및 레이아웃 
      setLayout(new BorderLayout());
      t_scroll.setPreferredSize(new Dimension(790,400));
      

      //상단
      p_center.setPreferredSize(new Dimension(30, 30));
      detail.setPreferredSize(new Dimension(380, 80));
      
      //정보
      p_east.setLayout(new FlowLayout());
      p_east.setPreferredSize(new Dimension(400, 500));
     
     // c_option.setPreferredSize(new Dimension(100,60));
      
      //예약정보 글씨
      c_option.setFont(new Font("Serif", Font.BOLD, 25));
      scroll.setPreferredSize(new Dimension(180, 180));
      empty.setPreferredSize(new Dimension(70,30));
      driver.setPreferredSize(new Dimension(100,10));

      bt_empty.setPreferredSize(new Dimension(380, 4));
      t_keyword.setPreferredSize(new Dimension(300, 30));
      p_center.setLayout(new FlowLayout());
      
      Dimension d = new Dimension(380,30); //공통 크기
      

      
      Dimension a = new Dimension(380,7);
      empty3.setPreferredSize(a);
      empty4.setPreferredSize(a);
      
      
      Dimension z= new Dimension(120,30);
      c_name.setPreferredSize(z);
      la_pick.setPreferredSize(z);
      la_return.setPreferredSize(z);
      la_time.setPreferredSize(z);
      la_price.setPreferredSize(z);
      
      Dimension e= new Dimension(200,30);
      car_name.setPreferredSize(e);
      pick.setPreferredSize(e);
      r_return.setPreferredSize(e);
      time.setPreferredSize(e);
      price.setPreferredSize(e);
 
      
   
      p_center.add(p_search);
      p_search.add(t_keyword);
      p_search.add(bt_search);  
      p_center.add(t_scroll);


      p_east.add(empty);
      
      p_east.add(empty3);
      p_east.add(la_pick);
      p_east.add(pick);
      p_east.add(la_return);
      p_east.add(r_return);
      p_east.add(la_time);
      p_east.add(time);
      p_east.add(la_price);
      p_east.add(price);
      p_east.add(empty4);
      p_east.add(driver);
      p_east.add(detail);
      p_east.add(bt_empty);
      p_east.add(bt_regist);

      
      
      add(p_center, BorderLayout.NORTH);
      add(p_east, BorderLayout.EAST);
      add(p_center);
   
      //보여주기
      setBounds(300,100,1300,500);
      setVisible(true);
   
      

      
      
      //검색하기
      bt_search.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
         search();
      }
   });
      
      //등록하기
      bt_regist.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
              
              table.updateUI();
           }
        });
      
     
      table.addMouseListener(new MouseAdapter() {
    	  public void mouseReleased(MouseEvent e) {
    		  //String car_type=(String) table.getValueAt(table.getSelectedRow(), 0);
    		  String name=(String) table.getValueAt(table.getSelectedRow(), 1);
    		  String usetime=(String) table.getValueAt(table.getSelectedRow(), 2);
    		  int c_price=Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 3));
    		  
    		  car_name.setText(name);
    		  time.setText(usetime);
    		  price.setText(Integer.toString(c_price));
    	  }
      });

      
     
      getDetailtList();
   }    
   

   

      public void getDetailtList() {
         
         PreparedStatement pstmt=null;
         ResultSet rs=null;
         
         String sql="select  car_name, car_type, usetime, price";
         sql+=" from rentcar";
         
         
         
         try {
            pstmt=con.prepareStatement(sql
                  , ResultSet.TYPE_SCROLL_INSENSITIVE
                  ,  ResultSet.CONCUR_READ_ONLY);
               
            rs=pstmt.executeQuery();
            rs.last();// 커서를 마지막 레코드로 보냄
            int total = rs.getRow(); //레코드 번호 구하기
            
            
            rows=new String[total][columnName.length];
            
            rs.beforeFirst(); // 커서 위치 제자리로
            int index=0;
            while(rs.next()) {

               rows[index][0]=rs.getString("car_type");
               rows[index][1]=rs.getString("car_name");
               rows[index][2]=Integer.toString(rs.getInt("usetime"));
               rows[index][3]=Integer.toString(rs.getInt("price"));
              
         
               
               index++;
            }
            table.updateUI(); // JTable 갱신
         } catch (SQLException e) {
            e.printStackTrace();
         }finally {
            dbmanager.release(con,pstmt,rs);
         }
      }
      public void search() {
    	  
          PreparedStatement pstmt=null;
          ResultSet rs=null;
          Connection con=dbmanager.getConnection();
          String sql="select  * from rentcar where car_type=?";
          
          try {
        	  pstmt=con.prepareStatement(sql
                      , ResultSet.TYPE_SCROLL_INSENSITIVE
                      ,  ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1,t_keyword.getText());
			 rs=pstmt.executeQuery();
	            rs.last();// 커서를 마지막 레코드로 보냄
	            int total = rs.getRow(); //레코드 번호 구하기
	            
	            
	            rows=new String[total][columnName.length];
	            
	            rs.beforeFirst(); // 커서 위치 제자리로
	            int index=0;
	            while(rs.next()) {

	               rows[index][0]=rs.getString("car_type");
	               rows[index][1]=rs.getString("car_name");
	               rows[index][2]=Integer.toString(rs.getInt("usetime"));
	               rows[index][3]=Integer.toString(rs.getInt("price"));
	              
	         
	               
	               index++;
	            }
	            table.updateUI(); // JTable 갱신
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
            dbmanager.release(con,pstmt,rs);
         }
      }

}