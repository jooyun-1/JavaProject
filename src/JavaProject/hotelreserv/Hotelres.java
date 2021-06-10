package JavaProject.hotelreserv;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import JavaProject.rentreserv.RentModel;
import JavaProject.rentreserv.r_reserv;
import JavaProject.tour.DBManager;
import JavaProject.tour.TourApp;


public class Hotelres extends JFrame{
   
   JLabel la_hoRes,la_renRes;
   JTable table_h,table_r;
   JScrollPane scroll_h,scroll_r;
   JButton bt_back;//뒤로가기
   JButton cancel_h,cancel_r;
   
   TourApp app;
   ResModel model;
   RentModel rentModel;
   DBManager dbManager=new DBManager();
   
   public Hotelres(TourApp app) {
      this.app=app;
      setLayout(null);
      
      
      //숙박예약내역
      la_hoRes=new JLabel("숙박예약내역");
      table_h=new JTable();
      scroll_h=new JScrollPane(table_h);
      //렌트카예약내역
      la_renRes=new JLabel("렌트카예약내역");
      table_r=new JTable();
      scroll_r=new JScrollPane(table_r);
      
      
      cancel_h=new JButton("숙박예약삭제");
      cancel_r=new JButton("렌트카예약삭제");
      //스타일
      Font titleFnt = new Font("굴림체", Font.BOLD, 32);
      Font fnt = new Font("굴림체", Font.BOLD, 15);

      
      //숙박
      la_hoRes.setBounds(400, 70, 230, 50);
      la_hoRes.setFont(titleFnt);
      scroll_h.setBounds(100,150,800,100);

      //렌트카
      la_renRes.setBounds(390,300,280,50);
      la_renRes.setFont(titleFnt);
      scroll_r.setBounds(100, 370,800, 100);
   
      
      
      cancel_h.setBounds(350, 500, 130, 30);
      cancel_h.setBackground(Color.DARK_GRAY);
      cancel_h.setForeground(Color.WHITE);
      cancel_h.setFont(fnt);

      cancel_r.setBounds(520, 500, 150,30);
      cancel_r.setBackground(Color.DARK_GRAY);
      cancel_r.setForeground(Color.white);
      cancel_r.setFont(fnt);
      
      //조립
      
      add(la_hoRes);
      add(scroll_h);
      add(la_renRes);
      add(scroll_r);
      
      add(cancel_h);
      add(cancel_r);
   

      cancel_h.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         
            h_delete();
         }
      });
      
      cancel_r.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            r_delete();
         }
      });
      
      setVisible(true);
      setBounds(700,100,1000,600);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      HgetList();
      RgetList();
   }
   
   public void HgetList() {
      String sql="select * from h_reserv order by h_reserv_id desc";
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      ResultSetMetaData meta;//컬럼 정보 등을 가져오기 위한 객체
      model = new ResModel();
      Connection con=dbManager.getConnection();
      try {
         pstmt=con.prepareStatement(sql);
         rs=pstmt.executeQuery();//쿼리 수행후 결과집합 가져오기
         meta=rs.getMetaData(); //rs가 존재해야 메타정보를 얻을 수 있다!!
//         컬럼의 수, 컬럼의 이름 구해서 모델에 적용해보기!!
//         int col_count = meta.getColumnCount();//컬럼수
//         for(int i=1;i<=col_count;i++) {
//            String name=meta.getColumnName(i);
//            model.column.add(name);//모델객체가 보유한 벡터에 컬럼명 추가!!
//         }
         while(rs.next()){
            //db->java
            h_reserv reserv=new h_reserv();//게시물 한건을 담게될 VO생성 empty 상태
            reserv.setM_name(rs.getString("m_name"));
            reserv.setH_name(rs.getString("h_name"));
            reserv.setH_area(rs.getString("h_area"));
            reserv.setH_type(rs.getString("h_type"));
            reserv.setCheckin(rs.getString("checkin"));
            reserv.setCheckout(rs.getString("checkout"));
            reserv.setH_price(rs.getInt("h_price"));
         
            //한건의 레코드를 담은 VO를 벡터에 추가하자!!
            model.data.add(reserv);
            System.out.println(reserv);
         }
         //Model에 들어있는 메서드들은, Table에 해당 모델 적용 시점에 호출되는 것을 알 수 있다..
         //이때 JTable이 원하는 정보를 모델로 부터 얻어간다!!
         table_h.setModel(model);//JTable의 생성자에서 모델을 결정하는게 아니라, 생성된 모델중
                                 //원하는 모델을 테이블에 적용시키고 싶을때
         table_h.updateUI();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         dbManager.release(con, pstmt, rs);
      }         
   }
   
   public void h_delete() {    
            PreparedStatement pstmt=null;
            Connection con=dbManager.getConnection();
            String member= app.getMember().getM_name();

            String sql="delete from h_reserv where m_name=?";
            
            try {
               pstmt=con.prepareStatement(sql);
               pstmt.setString(1, member);       
               int result=pstmt.executeUpdate(); // DML 중 delete 수행
               if(result >0) {
                  //파일 삭제!!
                  JOptionPane.showMessageDialog(this, "삭제 완료");
                  HgetList(); // 리스트 다시 조회
               }
               
               
            } catch (SQLException e) {
                  e.printStackTrace();
            }finally {
               dbManager.release(con, pstmt);
            }
         }
         
   public void RgetList() {
      String sql="select * from r_reserv order by m_name desc";
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      ResultSetMetaData meta;//컬럼 정보 등을 가져오기 위한 객체
      rentModel = new RentModel();
      Connection con=dbManager.getConnection();
      try {
         pstmt=con.prepareStatement(sql);
         rs=pstmt.executeQuery();//쿼리 수행후 결과집합 가져오기
         meta=rs.getMetaData(); //rs가 존재해야 메타정보를 얻을 수 있다!!

         while(rs.next()){
            //db->java
            r_reserv reserv=new r_reserv();//게시물 한건을 담게될 VO생성 empty 상태
            reserv.setM_name(rs.getString("m_name"));
            reserv.setCar_name(rs.getString("car_name"));
            reserv.setPick(rs.getString("pick"));
            reserv.setR_return(rs.getString("r_return"));
            reserv.setTime(rs.getInt("time"));
            reserv.setPrice(rs.getInt("price"));
            reserv.setDetail(rs.getString("detail"));
         
            //한건의 레코드를 담은 VO를 벡터에 추가하자!!
            rentModel.data.add(reserv);
            System.out.println(reserv);
         }
         //Model에 들어있는 메서드들은, Table에 해당 모델 적용 시점에 호출되는 것을 알 수 있다..
         //이때 JTable이 원하는 정보를 모델로 부터 얻어간다!!
         table_r.setModel(rentModel);//JTable의 생성자에서 모델을 결정하는게 아니라, 생성된 모델중
                                 //원하는 모델을 테이블에 적용시키고 싶을때
         table_r.updateUI();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         dbManager.release(con, pstmt, rs);
      }         
   }
         
   public void r_delete() {    
         PreparedStatement pstmt=null;
         Connection con=dbManager.getConnection();
         String member= app.getMember().getM_name();

         String sql="delete from r_reserv where m_name=?";
         
         try {
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, member);       
          int result=pstmt.executeUpdate(); // DML 중 delete 수행
          if(result >0) {
             //파일 삭제!!
             JOptionPane.showMessageDialog(this, "삭제 완료");
             RgetList(); // 리스트 다시 조회
          }else {
             JOptionPane.showMessageDialog(this, "예약내역이 없습니다");
          }
            
            
         } catch (SQLException e) {
               e.printStackTrace();
         }finally {
            dbManager.release(con, pstmt);
         }
      }
   
   
   
   
}
