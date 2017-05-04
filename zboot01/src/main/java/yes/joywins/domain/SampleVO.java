package yes.joywins.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * ...15p.클래스 선언부의 몇 개의 어노테이션만으로도 
 *        getter/setter, toString( )을 컴파일시에 자동으로 작성함. 
@Getter
@Setter
@ToString
public class SampleVO implements Serializable {
 *
 * ...16p.@Data :
 * 		  @Data를 이용하면 getter/setter를 생성하고, equals( ), hashCode( ),
 *        toString( ), 파라미터가 없는 기본 생성자까지 자동으로 만들어 줌.
 *        @Data는 @ToString, @EqualsAndHashCode,@Getter, @Setter 그리고 
 *        @RequireArgsConstructor의 묶음이어서 ORM 에서 상호호출의 문제가
 *        발생할 수 있어서 차라리 @Getter, @Setter등을 이용하는 편이 코드를
 *        작성할 때 더 안전함.
 *        
 * ...16p.@ToString(exclude={"val3"}) :
 *        @ToString에는 exlude라는 속성을 이용해서 원하지 않는 속성을 
 *        출력하지 않도록 함.
 *                
 */
@Data
@ToString(exclude={"val3"})
public class SampleVO implements Serializable {
	
	private String val1;
	private String val2;
	private String val3;

}
