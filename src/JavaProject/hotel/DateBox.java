package JavaProject.hotel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;



//날짜 한개를 표현할 박스
public class DateBox extends JPanel{
   int width;
   int height;
   String day;
   public String getDay() {
	return day;
}

public void setDay(String day) {
	this.day = day;
}

Color color;
   Image img;
   
   public DateBox(String day,Color color,int width,int height) {
      this.day=day;
      this.color=color;
      this.width=width;
      this.height=height;
      setPreferredSize(new Dimension(width,height));
   
   }
   
   public void paint(Graphics g) {
      //배경색도 여기서 처리
      g.setColor(color);//그래픽 객체의 물감이 회색이다!!
      g.fillRect(0, 0,width,height);
      
      g.setColor(Color.WHITE);
      g.drawString(day, 10, 30);

   }
   
}