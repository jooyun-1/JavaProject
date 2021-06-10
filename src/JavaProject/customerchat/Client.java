package JavaProject.customerchat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import JavaProject.tour.TourApp;

public class Client extends JFrame implements ActionListener, KeyListener{
   JButton bt_open; //대화 상대방을 띄우기 위한 버튼
   JTextArea area;
   JScrollPane scroll;
   JTextField t_input;
   JButton bt;
   Consult consult; //내가 제어하고싶은 상대방 창에 대한 레퍼런스 변수
   TourApp tourApp;
   
   public Client(TourApp tourApp) {//생성자
	   this.tourApp=tourApp;
      //생성
      bt_open = new JButton("1:1 상담 연결");
      area = new JTextArea( );
      scroll = new JScrollPane(area);
      t_input = new JTextField(18);
      bt = new JButton("전송");
      
      setTitle("회원");
      //레이아웃, 스타일
      this.setLayout(new FlowLayout());
      scroll.setPreferredSize(new Dimension(280, 280));
      area.setBackground(Color.WHITE);
      
      //조립
      add(bt_open);
      add(scroll);
      add(t_input);
      add(bt);
      
      //이벤트리스너와의 연결
      bt.addActionListener(this); //전송 버튼과 리스너 연결 //괄호안에 액션리스너 와야함
      bt_open.addActionListener(this); // 오픈 버튼과 리스너 연결 //괄호안에 액션리스너 와야함
      //텍스트필드와 리스너 연결
      t_input.addKeyListener(this); //괄호안에 키리스너 와야함
   
      
      //보여주기
      setBounds(300, 100, 300, 400);// x, y, width, height
      setVisible(true);
      
   }
   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void keyReleased(KeyEvent e) {
      int key = e.getKeyCode();
      if(key == KeyEvent.VK_ENTER) {
         showText();
      }
      
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      //열기 버튼 누르면
      if(e.getSource() == bt_open) {
         //System.out.println("열기 눌렀네");
         //ChatClientB가 태어나도록 하자!!!
         consult = new Consult();
         
         
         //B창을 위한 설정 ( A,C정보를 넘경야 한다 )
         consult.setClient(this);//A
         

      }else if(e.getSource() == bt) {//전송 버튼 누르면
         //System.out.println("전송 눌렀네");
         showText();
      }
   }
   
   //전송 처리
   public void showText() {
      //(1) TextField 값 가져오기
      String msg = this.t_input.getText();      
      //(2) TextArea 에 누적
      this.area.append(msg+"\n");      
      //(3) 기존 입력값 초기화
      this.t_input.setText("");
      
      //친구인 상대방 채팅창의 area.append(msg+"\n");
      consult.area.append(msg+"\n");
      
      
   }
   
 
}