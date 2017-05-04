package yes.joywins.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * ...36p.
Java코드로 엔티티 클래스가 생성되었다면 다음 단계는 클래스에 적절한 어노테이션을 적용해서 
JPA에서 필요한 정보들을 설정함.

설정에 사용하는 어노테이션들에 대해서 가장 최소한의 정보들을 정리하면 아래와 같음.

@Id : 각 엔티티를 구별이 가능한 식별 ID를 가지도록 설계해야 합니다. 
      @Id는 가장 중요한 어노테이션임. 
      @Id는 해당 컬럼이 식별키(Primary key)라는 것을 의미함. 
	  모든 엔티티에는 반드시 @Id를 지정해 주도록 함. 
	  @Id는 주로 @GeneratedValue라는 어노테이션과 같이 이용해서 식별키를 어떤 전략으로 
	  생성하는지를 명시함. 
      데이터베이스마다 차이가 있기는 하지만, 일반적으로 식별키를 지정하는 방식은 :
      1)사용자가 직접 지정하거나, 
      2)자동으로 생성되는 번호등을 이용하거나, 
      3)별도의 방식을 이용해서 필요한 데이터를 생성해 내는 방식을 이용합니다. 
	  

@Column : 데이터베이스의 테이블을 구성할 때 인스턴스 변수가 컬럼이 되기 때문에 
          원한다면 컬럼명을 별도로 지정하거나, 컬럼의 사이즈, 제약 조건들을 추가하기 
		  위해서 사용함. 
		  
@Table : 클래스가 테이블이 되기 때문에 클래스의 선언부에 작성해서 데이터베이스상에서 
         어떤 테이블명을 가지게 할 것인지를 결정하게 됨. 
		 @Table을 설정하는 경우는 기본적으로 데이터베이스에 클래스명과 동일하게 테이블이
		 생성되는 것을 다르게 지정하고 싶을 때 사용함. 		 
		 
@Entity : 해당 클래스의 인스턴스들이 엔티티임을 명시함. 
          우선 클래스 선언부에는 반드시 @Entity가 설정되어야 함. 

 */


@Getter
@Setter
@ToString
@Entity
@Table(name="ztbl_boards")
public class Board implements Serializable {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long bno;
	  private String title;
	  private String writer;
	  private String content;
	  
/*
 * ...037p.@CreationTimestamp, @UpdateTimestamp : 
      이 두개의 어노테이션은 javax.persistence가 아닌 org.hibernate로 시작하는 패키지의 것임. 
      이것은 Hibernate의 고유한 기능으로, 엔티티가 생성되거나 업데이트 되는 시점의 날짜 데이터를 
      기록하는 설정임.
	  
 */

	  @CreationTimestamp
	  private Date regdate;
	  @UpdateTimestamp
	  private Date updatedate;
	  
}