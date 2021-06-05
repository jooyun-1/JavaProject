package util;

//각종 유용한 문자열 처리를 쉽게 처리하는 전담 객체를 정의한다!!
public class StringManager {

 //넘겨받은 인수가 1자리수 인 경우 앞에 '0' 문자를 붙이고
 //두자리 수 라면 그냥 쓴다
// public String getZeroString(int n) {
//    String value=null;
//    if(n<10) {//1자리수
//       value="0"+n;
//    }else {//2자리수
//       value=Integer.toString(n);
//    }
//    return value;
// }

 public static String getZeroString(int n) {
    return (n<10)? "0"+n : Integer.toString(n);
 }
}
