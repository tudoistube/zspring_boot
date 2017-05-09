package yes.joywins.domain;
/*
 * ...104p.객체를 화면에 출력하기 :
           가장 간단한 객체의 출력은 JSP에서 EL을 이용해서 출력하는 것과 거의 동일함. 
           예를 위해서 문자, 숫자, 날짜등의 데이터를 가진 클래스의 인스턴스를 생성해 
           보도록 함.
           예제로 사용할 ‘org.zerock.domain.MemberVO’클래스를 작성함.
 */
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberVO {

  private int mno;
  private String mid;
  private String mpw;
  private String mname;
  private Date regdate;
  
}
