package JavaProject.customerchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




//모든 접속 클라이언트 마다 1:1 대응하여, 서버측에 생성되는 대화용 클라이언트 쓰레드
//따라서 클라이언트가 접속을 끊으면, 서버측에 대응되는 이 객체 또한 소멸되어야 한다.
public class ServerMsgThread extends Thread{
   ConsultServer consultserver;
   Socket socket;
   BufferedReader buffr;//듣고
   BufferedWriter buffw;//말하기
   boolean flag=true;
   
   //회원정보를 보관해놓자!!
   MemberConsult member;
   
   
   public ServerMsgThread(Socket socket, ConsultServer consultserver) {
      this.socket=socket;
      this.consultserver=consultserver;
      
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
         
         //예전기능과는 틀리게, 클라이언트가 좀더 복잡한 형태의 데이터 구조를 전송했기 때문에
         //서버는 모든 메시지가 대화를 의도한게 아니라는것을 알고, 데이터를 분석해야 한다!!
         //이때 클라이언트가 보낸 데이터가 JSON이면, JSON파싱을 해야 하고, 클라이언트가 보낸 데이터구조가
         //xml이면 xml 파싱을 통해 해석하면 된다!!
         
         //JSON 파싱 시작!!!
         //msg 는 String  이며, JSoN는 객체가 아니다!! 따라서 JSON 객체로 변환하기위해 파싱해야 한다
         JSONParser jsonParser=new JSONParser();
         try {
            JSONObject packet=(JSONObject)jsonParser.parse(msg);
            String cmd = (String)packet.get("cmd");
            
            if(cmd.equals("login")) { //로그인 정보가 전송되어 오면
               consultserver.area.append("클라이언트가 로그인 정보를 보냈습니다\n");
               member = new MemberConsult(); //empty 상태의 VO 생성  
               
               JSONObject obj=(JSONObject)packet.get("member");
               member.setUser_id((String)obj.get("user_id")); //id 처리 
               member.setName((String)obj.get("name")); //name 처리 
               member.setRegdate((String)obj.get("regdate"));//regdate 처리 
            }else if(cmd.equals("chat")) { //대화의 메시지가 전송되어 오면 
               String message =(String)packet.get("message");
               
               //대화일때 broadcasting !!!
               for(int i=0;i<consultserver.clientList.size();i++) {
                  ServerMsgThread msgThread=consultserver.clientList.get(i);
                  msgThread.send(message);
               }
               consultserver.area.append(member.getUser_id()+"의 말:"+message+"\n");//area에 로그 남기기 
            }else if(cmd.equals("emo")) { //이모티몬을 전송한 거라면
            }else if(cmd.equals("add_friend")) {//친구추가요청이라면
            }else if(cmd.equals("present")) { //선물보내기 라면...
            }
            
         } catch (ParseException e) {
            e.printStackTrace();
         }

         
         
         
         
      } catch (IOException e) {
         //e.printStackTrace();
         flag=false; //쓰레드 dead 상태로 두기 
         consultserver.clientList.remove(this);//명단에서도 제거
         consultserver.area.append("현재까지 참여자 수 : "+consultserver.clientList.size()+"\n");
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
   }
   
}
