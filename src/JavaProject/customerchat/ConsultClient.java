package JavaProject.customerchat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ConsultClient extends JFrame{
   JPanel p_north;
   JTextField t_ip;
   JTextField t_port;
   JButton bt_connect;
   JTextArea area;
   JScrollPane scroll;
   JPanel p_south;
   JTextField t_input;
   JButton bt_send;
   Socket socket;
   ClientMsgThread msgThread;
   MemberConsult memberconsult;
   
   public ConsultClient(MemberConsult memberconsult) {
      this.memberconsult=memberconsult; //넘겨받은 회원정보 
      
      p_north = new JPanel();
      t_ip = new JTextField("183.96.90.103",10);
      t_port = new JTextField(5);
      bt_connect = new JButton("접속");
      area = new JTextArea();
      scroll = new JScrollPane(area);
      p_south = new JPanel();
      t_input = new JTextField(20);
      bt_send = new JButton("send");
      
      //조립 
      p_north.add(t_ip);
      p_north.add(t_port);
      p_north.add(bt_connect);
      add(p_north, BorderLayout.NORTH);
      add(scroll);
      p_south.add(t_input);
      //p_south.add(bt_send);
      add(p_south, BorderLayout.SOUTH);
      
      //이벤트 
      bt_connect.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //접속시도 
            connect();
         }
      });
      
      //윈도우창 닫을때 해야할 작업(쓰레드 종료: 즉 청취 종료) 
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            msgThread.flag=false; //쓰레드 종료
            System.exit(0); //현재 프로세스 종료
         }
      });
      
      //메시지 입력 엔터처리 
      t_input.addKeyListener(new KeyAdapter() {
         public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) { //엔터치면..
               sendMsg();   
            }
         }
      });
      
      //전송 버튼 누르면..
      bt_send.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            sendMsg();
         }
      });
      
      //보이기 
      setVisible(true);
      setBounds(0, 100, 300, 400);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      connect(); //서버와 연결!!!
      //직후에는 로그인 정보를 넘겨주자!! 방법1로.
      sendAllData();
   }
   
   public void sendMsg() {
      String msg =t_input.getText();
      //클라이언트가 서버에 보낼 데이터가 언제나 대화메시지 만 있는 것은 아니다!!!
      //대화 이외에 서버에 데이터를 전송할때는 어떻게 해야 하나? 
      //서버에 보내려고 하는 데이터에 각종 의미를 부여하되, 정형적,구조화된,많이 알려진 형태의 데이터를
      //구성하는게 좋다..
      /*
      {
         "cmd":"chat", 
         "message": "안녕"
      }
      */
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      sb.append("\"cmd\":\"chat\", ");
      sb.append("\"message\": \""+msg+"\"");
      sb.append("}");
      
      msgThread.send(sb.toString()); //서버에 메시지 전송~(출력)
      t_input.setText(""); //초기화
   }
   
   //방법1)채팅서버에 회원의 모든 정보를 실어서 보내는 방법  
   public void sendAllData() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      sb.append("\"cmd\" : \"login\",");  //cmd 요청명령을 구분하는 값!!
      sb.append("\"member\":{");
      sb.append("\"user_id\" :  \""+memberconsult.getUser_id()+"\",");
      sb.append("\"name\" :\""+memberconsult.getName()+"\",");
      sb.append("\"regdate\" :\""+memberconsult.getRegdate()+"\"");
      sb.append("}");
      sb.append("}");
      
      //서버에 메시지 전송 
      msgThread.send(sb.toString());
   }
   
   //방법2) 채팅서버에 회원의 id만 보내서, 이 회원의 정보는 서버측에서 참조할수 있게 하는 방법
   public void sendId() {
      
   }
   
   public void connect() {
      //String ip=t_ip.getText();
      //int port=Integer.parseInt(t_port.getText());
      String ip="localhost";
      int port=7777;
      
      try {
         socket = new Socket(ip, port); //접속!!!!!!!
         //클라이언트 측의 대화용 쓰레드 생성 
         msgThread = new ClientMsgThread(socket, this);
         msgThread.start(); //서버의 메시지 실시간 청취 시작
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }


}
