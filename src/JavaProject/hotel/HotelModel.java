package JavaProject.hotel;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;



public class HotelModel extends AbstractTableModel{
   Vector<Hotel>data=new Vector<Hotel>();
   Vector<String> column=new Vector<String>();
   
   public HotelModel() {
      column.add("호텔이름");
      column.add("지역");
      column.add("종류");
      column.add("가격");
      
   }
   public int getRowCount() {
      return data.size();
   }

   public int getColumnCount() {
      return column.size();
   }
   public String getColumnName(int col) {
      return column.get(col);
   }
   public Object getValueAt(int row, int col) {
      Hotel hotel=data.get(row);
      String value=null;
      if(col==0) {
         value=hotel.getHotelName();
      }else if(col==1) {
         value=hotel.getArea();
      }else if(col==2) {
         value=hotel.getHotelType();
      }else if(col==3) {
         value=Integer.toString(hotel.getPrice());
      }
         
      
      return value;
   }
}