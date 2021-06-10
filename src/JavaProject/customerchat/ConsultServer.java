package JavaProject.customerchat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConsultServer extends JFrame{
   JPanel p_north;
   JTextField t_port;
   JButton bt;
   JTextArea area;
   JScrollPane scroll;
   
   ServerSocket server;
   Thread serverThread; //서버 가동에 사용되는 쓰레드, 특히 접속자 감지에서 메인쓰레드로 하여금 대기상태에 빠지지
                           //않게 하기 위해 필요함
   Vector<ServerMsgThread> clientList = new Vector<ServerMsgThread>();
   boolean serverFlag = true;
   
   public ConsultServer() {
      //생성
      p_north = new JPanel();
      t_port = new JTextField(10);
      bt = new JButton("서버가동");
      area = new JTextArea();
      scroll = new JScrollPane(area);
      
      //스타일 & 레이아웃
      //setLayout(new FlowLayout());//패널줬으므로 필요없음
      //t_port.setPreferredSize(new Dimension(100, 25));//패널줬으므로 간단하게 위에서 처리가능
      
      //조립
      p_north.add(t_port);
      p_north.add(bt);
      add(p_north, BorderLayout.NORTH);
      add(scroll);
      
      //리스너 연결
      
      //이벤트
      bt.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            serverThread = new Thread() {
               public void run() {
                  runServer();
               }
            };
            serverThread.start();
         }
      });
      
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            serverFlag = false;//쓰레드 소멸
            System.exit(0);//프로세스 킬
         }
      });
      //보여주기
      setVisible(true);
      setBounds(700, 100, 300, 400);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
   }
   
   private void runServer() {
      int port = Integer.parseInt(t_port.getText());
      try {
         server = new ServerSocket(port);
         area.append("서버가동\n");
         while(serverFlag) {
            Socket socket = server.accept();//대기상태에 빠지는 코드는 절대로 메인쓰레드로 처리하지 말자!!
            String ip = socket.getInetAddress().getHostAddress();
            area.append(ip+"접속자 발견\n");
            
            //접속자 발견하면, 메시지 담당 쓰레드 객체 생성 후, 벡터 명단에 추가!!!
            ServerMsgThread smt = new ServerMsgThread(socket, this);
            smt.start();//열심히 듣고, 말하기 시작~~~~~~
            clientList.add(smt);
            area.append("현재 접속자 수는 "+clientList.size()+"\n");//로그 남기기
            
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public static void main(String[] args) {
      new ConsultServer();
   }

}
