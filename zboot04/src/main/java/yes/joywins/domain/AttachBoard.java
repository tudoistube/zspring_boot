/*
 * ...083p.AttachBoard클래스는 기존의 Board클래스를 상속할 수도 있겠지만, 
           예제에서는 별도의 클래스를 작성해서 처리하도록 함.
 */
package yes.joywins.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
