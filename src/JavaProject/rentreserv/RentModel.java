package JavaProject.rentreserv;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class RentModel extends AbstractTableModel{
   public Vector<r_reserv>data=new Vector<r_reserv>();
   Vector<String> column=new Vector<String>();
   
   public RentModel() {
      column.add("회원이름");
      column.add("차모델");
      column.add("픽업픽업");
      column.add("반납장소");
      column.add("이용시간");
      column.add("가격");
      column.add("요청사항");
      
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
      r_reserv hotel=data.get(row);
      String value=null;
      if(col==0) {
         value=hotel.getM_name();
      }else if(col==1) {
         value=hotel.getCar_name();
      }else if(col==2) {
         value=hotel.getPick();
      }else if(col==3) {
         value=hotel.getR_return();
      }else if(col==4) {
         value=Integer.toString(hotel.getTime());
      }else if(col==5) {
         value=Integer.toString(hotel.getPrice());
      }else if(col==6) {
         value=hotel.getDetail();
      }
         
      
      return value;
   }
   
   
}