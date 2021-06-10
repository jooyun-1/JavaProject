package JavaProject.customerchat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Consult extends JFrame implements KeyListener{
   JTextArea area;
   JScrollPane scroll;
   JTextField t_input;
   
   //현재 창에서, B와C를 제어하기 위해 보유한다!!!
   private Client client;
   
   //private JTextArea area2; //ChatClientA 가 보유한 JTextArea의 주소값
   
   //생성자도 메서드이다!! 따라서 매개변수 전달이 가능함!!
   public Consult() {//생성자
      //생성
      area = new JTextArea();
      scroll = new JScrollPane(area);
      t_input = new JTextField(24);
      
      setTitle("상담원");
      //스타일, 레이아웃
      setLayout(new FlowLayout());
      scroll.setPreferredSize(new Dimension(280, 280));
      area.setBackground(Color.ORANGE);
      
      //조립
      add(scroll);
      add(t_input);
      
      //이벤트연결
      t_input.addKeyListener(this);
      
      //보여주기
      setBounds(600, 100, 300, 400);
      setVisible(true);
   }

   @Override
   public void keyTyped(KeyEvent e) {}
   @Override
   public void keyPressed(KeyEvent e) {}
   @Override
   public void keyReleased(KeyEvent e) {
      if(e.getKeyCode() == KeyEvent.VK_ENTER) {
         showText();
      }
   }
   
   //setter
   public void setClient(Client client) {
      this.client = client;
   }
   

   
   
   public void showText() {
      //(1) 나의 텍스트필드 값 구하기
      String msg = t_input.getText();
      //(2) 나의 TextArea 에 누적
      area.append(msg+"\n");
      //(3) ChatA의 TextArea에도 누적
      client.area.append(msg+"\n");
      
      //(4) 입력값 다시 초기화
      t_input.setText("");
   }

}