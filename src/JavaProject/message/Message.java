package JavaProject.message;

public class Message {
	   
	   private int message_id;
	   private String title;
	   private String writer;
	   private String content;
	   private String regdate;
	   private int hit;
	   
	   
	   public int getMessage_id() {
	      return message_id;
	   }
	   public void setMessage_id(int message_id) {
	      this.message_id = message_id;
	   }
	   
	   public String getTitle() {
	      return title;
	   }
	   public void setTitle(String title) {
	      this.title = title;
	   }
	   public String getWriter() {
	      return writer;
	   }
	   public void setWriter(String writer) {
	      this.writer = writer;
	   }
	   public String getContent() {
	      return content;
	   }
	   public void setContent(String content) {
	      this.content = content;
	   }
	   public String getRegdate() {
	      return regdate;
	   }
	   public void setRegdate(String regdate) {
	      this.regdate = regdate;
	   }
	   public int getHit() {
	      return hit;
	   }
	   public void setHit(int hit) {
	      this.hit = hit;
	   }
	   
	   
	   
	   
	}