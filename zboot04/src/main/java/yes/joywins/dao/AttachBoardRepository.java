// ...086p.
package yes.joywins.dao;

import org.springframework.data.repository.CrudRepository;

import yes.joywins.domain.AttachBoard;

/*
 * ...086p.Aggregate(집합체) : 
           이전 예제의 댓글 처리에서는 별도의 ReplyRepository인터페이스를 설계해서 
           Reply가 단독으로 생성되거나 삭제가 가능하도록 처리되었음.
            
           반면에 게시물과 첨부파일의 관계는 이 관계가 조금 더 강하게 묶여있음. 
           예를 들어 첨부파일 자체는 원글이 생성될 때 같이 생성되는 경향이 강하고, 
           첨부파일을 다른 것으로 변경한다는 행위 자체가 의미하는 바가 결국은 게시물의 
           수정이라는 행위와 동일하게 해석됨.
            
           이러한 상황들을 고민해 보면 결국 게시물이라는 것이 단순 게시물이 아닌 
           ‘게시물 + 첨부파일들’이라는 것을 의미하게 됨. 
           이러한 성격의 엔티티를 도메인 주도 설계에서는 ‘집합체(Aggregate)’라는 
           용어로 설명함.
            
           집합체의 경우에는 모든 집합체의 대표가 되는 객체를 이용해서 진행하는 것이 좋음. 
           따라서 별도의 Repository를 생성하는 대신에 대표가 되는 객체의 Repository를 
           이용해서 작업하는 것이 복잡도를 줄여줄 수 있음. 
 */
public interface AttachBoardRepository extends CrudRepository<AttachBoard, Long> {

}
