package JavaProject.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import JavaProject.tour.DBManager;

public class HotelManager {
	DBManager dbmanager=new DBManager();
	HotelHandler handler=new HotelHandler();
	public HotelManager() {
		  String sql="insert into hotel(h_name,h_area,h_type,h_price) values(?,?,?,?)";
	         
			PreparedStatement pstmt=null;
			Connection con=dbmanager.getConnection();
			try {
				for(int i=0;i<handler.hotelList.size();i++) {
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, handler.hotelList.get(i).getHotelName());
					pstmt.setString(2, handler.hotelList.get(i).getArea());
					pstmt.setString(3,  handler.hotelList.get(i).getHotelType());
					pstmt.setInt(4,  handler.hotelList.get(i).getPrice());
					int result=pstmt.executeUpdate();
					if(result==1) {
						System.out.println("성공");
					}else {
						System.out.println("실패");
					}
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbmanager.release(con,pstmt);
			}
	}
}
