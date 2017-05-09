/*
 * ...083p.AttachBoard클래스는 기존의 Board클래스를 상속할 수도 있겠지만, 
           예제에서는 별도의 클래스를 작성해서 처리하도록 함.
 */
package yes.joywins.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "ztbl_attach_boards")
@EqualsAndHashCode(of ="ano")
@ToString
public class AttachBoard {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long ano;
  
  private String title;
  
  private String writer;
  
  private String content;

  @CreationTimestamp
  private Date regdate;
  @UpdateTimestamp
  private Date updatedate;

  private boolean delFlag;
  
/*
 * ...084p.AttachBoard클래스는 여러 개의 첨부 파일을 가질 수 있음.
  @OneToMany
  
 * ...085p.mappedBy속성은 연관관계의 주인을 설정할 때 사용함. 
           이때 연관관계의 주인이라는 의미는 외래키를 가지는 엔티티를 의미함. 
           외래키를 가지는 연관관계의 주인이 아닌 쪽은 mappedBy를 이용해서 
           해당 엔티티가 주인이 아니라는 것을 지정해 줌.           
  @OneToMany(mappedBy="aboard")

 * ...088p.cascade 속성 :
           src/test/java/yes/joywins/AttachBoardTests::testCreate() 를 실행하면,
           실행 결과를 보면 예상과 달리 ztbl_attch_boards 테이블에만 데이터가 
           추가되는 것을 볼 수 있음. 
           데이터베이스 상에서도 동일하게 ztbl_attach_boards 테이블에만 레코드가 
           존재함.
           
           위와 같은 현상이 일어나는 이유는 AttachBoard 객체만을 엔티티 매니저가 
           처리하고, 연관관계가 설정된 AttachFile 객체들은 처리되지 않았기 때문임.
           
           JPA에서는 하나의 객체의 상태에 따라서 종속적인 객체들의 영속성이 같이 
           처리되는 것을 ‘영속성 전이’라고 설명함. 
           영속성 전이라는 개념은 겉으로는 데이터베이스의 트랜잭션과 유사하지만 
           JPA의 경우 메모리상의 객체가 엔티티 매니저의 컨텍스트에 들어가는 ‘영속, 
           준영속, 비영속’등의 개념이 존재하기 때문에 더 복잡한 상태가 됨.
           
           JPA에서는 종속적인 엔티티의 영속성 전이에 대한 설정을 다음과 같이 구분함. 
			ALL
			PERSIST
			MERGE
			REMOVE
			REFRESH
			DETACH
			
		   예를 들어 ALL이나 PERSIST를 이용하게 되면 Board객체가 save( )될 때 replies의 
		   변경이 있는 경우 자동으로 ztbl_replies 에도 변경이 일어나게 됨.
		   따라서 AttachBoard 인스턴스가 save( )를 통해서 영속 상태가될 때 AttachFile의 
		   인스턴스들 역시 같이 저장될 수 있도록 cacade속성을 아래와 같이 지정해 줌. 
		   
		   CascadeType.ALL을 지정하면 AttachBoard의 모든 변화에 대해서 같이 처리되기 때문에 
		   가장 많이 사용됨.
  @OneToMany(mappedBy="aboard" , cascade=CascadeType.ALL)

 * ...092p.fetch 속성 :
           즉시 로딩을 이용하고 싶다면 fetch=FetchType.EAGER 와 같이 지정함.
           실행된 SQL을 보면 left outer join이 걸려서 처리되는 것을 볼 수 있고, 첨부파일 
           이름들을 가져올 때 별도의 SQL실행 없이 동작함.
           즉시 로딩과 지연 로딩 방식중에서 어떤 방식이 더 낫다고 단정짓기는 어렵습니다만, 
           JPA에서 기본으로 사용하는 것이 지연 로딩 방식이고, 성능상에 좋은 점을 가지기 때문에 
           개인적으로는 @Query와 같이 지연로딩을 이용하는 것이 가장 무난한 방법이라고 생각함. 
*/
  @OneToMany(mappedBy="aboard", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
  private List<AttachFile> files;

}
