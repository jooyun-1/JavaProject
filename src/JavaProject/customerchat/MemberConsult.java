package JavaProject.customerchat;

//회원 한명의 정보를 담을 수 있는 회원 VO 정의
public class MemberConsult {
 private int member_id;
 private String user_id;
 private String password;
 private String name;
 private String regdate;

 public int getMember_id() {
    return member_id;
 }
 public void setMember_id(int member_id) {
    this.member_id = member_id;
 }
 public String getUser_id() {
    return user_id;
 }
 public void setUser_id(String user_id) {
    this.user_id = user_id;
 }
 public String getPassword() {
    return password;
 }
 public void setPassword(String password) {
    this.password = password;
 }
 public String getName() {
    return name;
 }
 public void setName(String name) {
    this.name = name;
 }
 public String getRegdate() {
    return regdate;
 }
 public void setRegdate(String regdate) {
    this.regdate = regdate;
 }
}
