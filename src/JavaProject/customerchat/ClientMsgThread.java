package JavaProject.customerchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//다른 클라이언트의 메시지를 실시간 청취하기 위해서는, listen() 즉 듣기를 무한루프로 처리해야 하므로, 
//별도의 쓰레드가 필요하다, 즉 서버측과 마찬가지로 클라이언트 또한 메시지를 전담하는 객체를 결국 쓰레드로
//지원하자!!
public class ClientMsgThread extends Thread{
   ConsultClient consultclient;
   Socket socket;
   BufferedReader buffr;//듣고
   BufferedWriter buffw;//말하기
   boolean flag=true;
   
   public ClientMsgThread(Socket socket, ConsultClient consultclient) {
      this.socket=socket;
      this.consultclient=consultclient;
      
      try {
         buffr= new BufferedReader(new InputStreamReader(socket.getInputStream()));
         buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   //듣고
   public void listen() {
      String msg=null;
      try {
         msg= buffr.readLine();
         consultclient.area.append(msg+"\n");//area에 로그 남기기 
      } catch (IOException e) {
         //e.printStackTrace();
         flag=false; //쓰레드 dead 상태로 두기 
      }
   }
   
   //말하기
   public void send(String msg) {
      try {
         buffw.write(msg+"\n");
         buffw.flush();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   //듣고,말하는 행위는 서버이기에 끝없이!!! 즉 쓰레드가 절대 죽으면 안됨 
   public void run() {
      while(flag) {
         listen();
      }
      //쓰레드 종료에 따른, 스트림 닫기
      if(buffw != null) {
         try {
            buffr.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      if(buffr != null) {
         try {
            buffr.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
   
}