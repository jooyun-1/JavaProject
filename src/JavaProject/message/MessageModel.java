package JavaProject.message;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

//notice 게시판 전용 TableModel 밖으로 뻄~
public class MessageModel extends AbstractTableModel {
   
   //Vector의 제너릭 형태는 notice : 존재 시키기 
   Vector<Message> data =new Vector<Message>(); // 기존의 데이터를 처리했던 이차원배열을 대신한 컬렉션 객체(형태)(일차원임)
   Vector<String> column = new Vector<String>(); //기존의 컬럼명을 처리했던 일차원배열을 대신할 컬렉션 객체
   public MessageModel() {
      column.add("제목"); 
      column.add("작성자"); 
      column.add("내용"); 
      column.add("날짜"); 
      column.add("조회수"); 
   }
   public int getRowCount() {
      
      return data.size(); //VO가 담겨진 벡터의 크기
   }
   public int getColumnCount() {
      
      return column.size(); //위 백터 이용, 컬럼 사이즈
   }
   
   //이 메서드는 getColumnCount()의 반환값만큼 호출하면서 매개변수 col의 값을 0부터 증가시키며 호출
   public String getColumnName(int col) { // 0,1,2,3...순서대로 col을 증가시키며 호출
      return column.get(col);
   }
   public Object getValueAt(int row, int col) {
      System.out.println(row+","+col);
      
      Message message =data.get(row);
      String value = null; // JTable은 스트링 취급
      if(col==0) {
         value = message.getTitle();
      }else if(col==1) {
         value = message.getWriter();
      }else if(col==2) {
         value = message.getContent();
      }else if(col==3) {
         value = message.getRegdate();
      }else if(col==4) {
         value=Integer.toString(message.getHit());
         
      }
      return value;
   }

}