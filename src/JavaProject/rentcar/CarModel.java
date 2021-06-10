package JavaProject.rentcar;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class CarModel extends AbstractTableModel {

   Vector<Car>data=new Vector<Car>();
   Vector<String> column=new Vector<String>();
   
   public CarModel() {
      column.add("차종");
      column.add("차 이름");
      column.add("사용시간");
      column.add("가격");
      
   }
   public int getRowCount() {
      return data.size();
   }

   public int getColumnCount() {
      return column.size();
   }
   /*public String getColumnName(int col) {
      return column.get(col);
   }*/
   
   public Object getValueAt(int row, int col) {
      Car car =data.get(row);
      String value=null;
      if(col==0) {
         value=car.getCar_name();
      }else if(col==1) {
         value=car.getCar_type();
      }else if(col==2) {
         value=Integer.toString(car.getUsetime());
      }else if(col==3) {
         value=Integer.toString(car.getPrice());
      }
         
      
      return value;
   }
}
