// ...083p.
package yes.joywins.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "ztbl_attach_files")
@EqualsAndHashCode(of ="fileno")
/*
 * ...084p.양방향 설계시 반드시 주의할 점 : 
           1. 양쪽 모두 toStirng( )을 오버라이드 하게 되면 무한 루프처리 되므로 주의.
              toString( )의 경우는 반드시 toString( )생성시에 양쪽 중에 한쪽은 참조하는 
              객체의 toString( )을 호출하지 않도록 작성해야 함.            
           2. 별도의 테이블이 생성되지 않도록 므로mappedBy 속성을 처리할 것.
 */
@ToString(exclude="aboard")
public class AttachFile {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long fileno;
  
  private String filename;
  
/*
 * ...084p.AttachFile 클래스는 ‘다대일’의 관계임.
 */
  @ManyToOne
  private AttachBoard aboard;
  
}