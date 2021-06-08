package JavaProject.hotel;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import JavaProject.tour.DBManager;
import JavaProject.tour.Member;
import JavaProject.tour.TourApp;

public class HotelMain extends JFrame{
   JPanel p_north,p_east;
   Choice ch_area;
   JTextField t_hotelName;
   JButton bt_search;
   JButton loadXml;
   
   JTable table;
   JScrollPane scroll;
   
   JLabel la_hotel;//이름
   JTextField t_hotel;
   JLabel la_area;//지역
   JTextField t_area;
   JLabel la_type;//종류
   JTextField t_type;
   JLabel la_price;//가격
   JTextField t_price;
   JLabel la_reserveSt;//예약 시작일
   JTextField t_reserveSt;
   JLabel la_reserveEnd;//예약 종료일
   JTextField t_reserveEnd;
   JButton bt_reservation;//예약하기
   
   
   String[] areaArray= {"서울","경기","충청도","전라도","강원도","경상도","제주도"};
   
   DBManager dbmanager=new DBManager();
   HotelHandler handler;
   Diary diary;
   TourApp tourApp;
   HotelModel hotelmodel;
   
   public HotelMain(TourApp tourApp) {
	   this.tourApp=tourApp;
	   
      //생성
      p_north=new JPanel();
      ch_area=new Choice();
      t_hotelName=new JTextField(25);
      loadXml=new JButton("Xml로드");
      bt_search=new JButton("검색");
      
      //센터
      table=new JTable();
      scroll=new JScrollPane(table);
      
      p_east=new JPanel();
      la_hotel=new JLabel("숙소이름");
      t_hotel=new JTextField();
      la_area=new JLabel("지역");
      t_area=new JTextField();
      la_type=new JLabel("숙박종류");
      t_type=new JTextField();
      la_price=new JLabel("가격");
      t_price=new JTextField();
      la_reserveSt=new JLabel("예약시작일");
      t_reserveSt=new JTextField();
      la_reserveEnd=new JLabel("예약종료일");
      t_reserveEnd=new JTextField();
      bt_reservation=new JButton("예약하기");
   
      
      //스타일 
      scroll.setPreferredSize(new Dimension(800,400));
      p_east.setLayout(new FlowLayout());
      p_east.setPreferredSize(new Dimension(500,400));
      
      Dimension d=new Dimension(400,30);
      t_hotel.setPreferredSize(d);
      t_area.setPreferredSize(d);
      t_type.setPreferredSize(d);
      t_price.setPreferredSize(d);
      t_reserveSt.setPreferredSize(d);
      t_reserveEnd.setPreferredSize(d);
      
      Dimension a=new Dimension(80,30);
      la_hotel.setPreferredSize(a);
      la_area.setPreferredSize(a);
      la_type.setPreferredSize(a);
      la_price.setPreferredSize(a);
      la_reserveSt.setPreferredSize(a);
      la_reserveEnd.setPreferredSize(a);
      
      
      //조립
      p_north.add(ch_area);
      p_north.add(t_hotelName);
      p_north.add(loadXml);
      p_north.add(bt_search);
      add(p_north,BorderLayout.NORTH);
      for(int i=0;i<areaArray.length;i++) {
         ch_area.add(areaArray[i]);
      }
   
      add(scroll);
      p_east.add(la_hotel);
      p_east.add(t_hotel);
      p_east.add(la_area);
      p_east.add(t_area);
      p_east.add(la_type);
      p_east.add(t_type);
      p_east.add(la_price);
      p_east.add(t_price);
      p_east.add(la_reserveSt);
      p_east.add(t_reserveSt);
      p_east.add(la_reserveEnd);
      p_east.add(t_reserveEnd);
      p_east.add(bt_reservation);
      add(p_east,BorderLayout.EAST);
      
      //리스너
      
      //검색
      loadXml.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			loadXML();
			
		}
	});
      bt_search.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            search();
         }
      });
      
      //예약하기
      bt_reservation.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            reserv();
         }
      });
      t_reserveSt.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            diary=new Diary();
            
         }
      });
      t_reserveEnd.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            diary=new Diary();
         }
      });
      
      
      
      //상세보기
      table.addMouseListener(new MouseAdapter() {
         public void mouseReleased(MouseEvent e) {
            String hotelName=(String) table.getValueAt(table.getSelectedRow(), 0);
            String area=(String) table.getValueAt(table.getSelectedRow(), 1);
            String hotelType=(String) table.getValueAt(table.getSelectedRow(), 2);
            int price=Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 3));
            
            t_hotel.setText(hotelName);
            t_area.setText(area);
            t_type.setText(hotelType);
            t_price.setText(Integer.toString(price));
         }
      });      
   
      setBounds(300,100,1300,500);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
   }
   
   public void loadXML() {
      SAXParserFactory factory=SAXParserFactory.newInstance();//팩토리의 인스턴스 얻기
      
      try {
         URL url=this.getClass().getClassLoader().getResource("hotel.xml");
         URI uri=url.toURI();
         SAXParser saxParser=factory.newSAXParser();//파서생성
         saxParser.parse(new File(uri),handler=new HotelHandler());
         //JTable의 모델 데이터와 파싱한 결과와의 매칭은 파싱 전? 파싱한 후?
         HotelModel model=new HotelModel();
         model.data=handler.hotelList;
         
         table.setModel(model);//바로 이 순간부터 JTable은  TableModel의
                                         //메서드들을 호출하게된다~~? 왜? 그래야 표를 구성하니깐..
         String sql="insert into hotel(h_name,h_area,h_type,h_price) values(?,?,?,?)";
         
         PreparedStatement pstmt=null;
         Connection con=dbmanager.getConnection();
         try {
            for(int i=0;i<handler.hotelList.size();i++) {
               pstmt=con.prepareStatement(sql);
               pstmt.setString(1,handler.hotelList.get(i).getHotelName());
               pstmt.setString(2, handler.hotelList.get(i).getArea());
               pstmt.setString(3,  handler.hotelList.get(i).getHotelType());
               pstmt.setInt(4,  handler.hotelList.get(i).getPrice());
               int result=pstmt.executeUpdate();
               if(result==1) {
                 System.out.println("등록성공");
               }else {
                System.out.println("등록실패");
               }
            }
         
         } catch (SQLException e) {
            e.printStackTrace();
         }finally {
            dbmanager.release(con,pstmt);
         }
      } catch (URISyntaxException e) {
         e.printStackTrace();
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
   }
   
   
      
   public void reserv() {
	   Member member=tourApp.getMember();
	   
	   String sql="insert into h_reserv(member_sid,h_name,h_area,h_type,h_price) values(?,?,?,?,?)";
	   
	   
		PreparedStatement pstmt=null;
		Connection con=dbmanager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,member.getMember_id());
			pstmt.setString(2, t_hotel.getText());
			pstmt.setString(3, t_area.getText());
			pstmt.setString(4, t_type.getText());
			pstmt.setString(5, t_price.getText());
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
   
   //vector의 data 를 대상으로 조사 
 public void search() {
       // Vector <Hotel>result=new Vector<Hotel>();
        String word=t_hotelName.getText();
        
        
        /*for(int i=0;i<model.data.size();i++) {
          /String d=(String)model.getValueAt(i, 1);
             
           if(d.startsWith(word)) {
              //벡터에 VO 만들어 넣어주기 
//              hotel.setArea(d);
//              result.add(hotel);
//              
//              model.data.add(hotel);
           }
           
        }
       //교체 
      // table.setModel(model);
       //업ㄷ티 
       table.updateUI();;
      }*/
   }
   }

   
  
 
   
