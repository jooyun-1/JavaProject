package JavaProject.hotelreserv;


import java.util.Vector;

import javax.swing.table.AbstractTableModel;



public class ResModel extends AbstractTableModel{
   Vector<h_reserv>data=new Vector<h_reserv>();
   Vector<String> column=new Vector<String>();
   
   public ResModel() {
      column.add("회원이름");
      column.add("호텔이름");
      column.add("지역");
      column.add("종류");
      column.add("체크인");
      column.add("체크아웃");
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
      h_reserv hotel=data.get(row);
      String value=null;
      if(col==0) {
         value=hotel.getM_name();
      }else if(col==1) {
         value=hotel.getH_name();
      }else if(col==2) {
         value=hotel.getH_area();
      }else if(col==3) {
         value=hotel.getH_type();
      }else if(col==4) {
         value=hotel.getCheckin();
      }else if(col==5) {
         value=hotel.getCheckout();
      }else if(col==6) {
         value=Integer.toString(hotel.getH_price());
      }
         
      
      return value;
   }
}