// ...071p.
package yes.joywins.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
//@Table(name = "ztbl_replies")
// ...081p.Reply 클래스에 인덱스를 추가하고 싶다면 다음과 같이 처리해 줄 수 있음. 
@Table(name = "ztbl_replies" , 
       indexes = {@Index(unique=false, columnList="board_bno" )  })
@EqualsAndHashCode(of={"rno"})
public class Reply implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long rno;
  private String replyText;
  private String replyer;

  @CreationTimestamp
  private Date regdate;
  @UpdateTimestamp
  private Date updatedate;
  
  // ...072p.
  @ManyToOne
  private Board board;

}
