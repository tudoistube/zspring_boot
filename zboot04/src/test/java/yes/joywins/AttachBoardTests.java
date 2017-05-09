/*
 * ...087p.테스트 코드를 통한 연관관계의 확인 :
           집합체의 경우에는 모든 작업의 대표가 되는 객체를 통해서 
           하는 것이 일반적이므로, 기존의 단일 엔티티 클래스만으로 
           가지고 처리하는 것에 비해서 다음과 같이 추가적으로 알아야 
           하는 내용들이 있음 :  
			cascade 속성. 
			Lazy, Eager 로딩. 
			orhan.
 */
package yes.joywins;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import yes.joywins.dao.AttachBoardRepository;
import yes.joywins.domain.AttachBoard;
import yes.joywins.domain.AttachFile;

@RunWith(SpringRunner.class)
@SpringBootTest
/*
 * ...093p.만일 실제로 데이터베이스에 테스트 결과를 반영하고 싶다면 테스트 클래스의 
           선언부에 @Commit 어노테이션을 추가해 주어야 함. 
 */
@Commit //...093p.
public class AttachBoardTests {
	
	@Autowired
	private AttachBoardRepository repo;

/*
 * ...087p.연관관계.
           cascade 속성.
           Lazy, Eager 로딩.
           orphan 속성.

 * ...089p./yes/joywins/domain/AttachBoard 의
           private List<AttachFile> files 에 아래와 같이 cascade 속성을
           @OneToMany(mappedBy="aboard" , cascade=CascadeType.ALL)
           추가하지 않으면, ztbl_attch_boards 테이블에만 데이터가 추가되므로 
           cascade 속성을 추가함.
 */
	//@Test
	public void testCreate(){

		AttachBoard board = new AttachBoard();
	
		board.setTitle("Title03 with Attach");
		board.setContent("Test Content03");
		board.setWriter("admin00");
	
		AttachFile afile = new AttachFile();
		afile.setFilename("aaa03.jpg");
		afile.setAboard(board);
	
	
		AttachFile bfile = new AttachFile();
		bfile.setFilename("bbb03.jpg");
		bfile.setAboard(board);
	
		board.setFiles(Arrays.asList(afile, bfile));
	
		repo.save(board);
	}
	
/*
 * ...090p.삭제 작업과 orphanRemoval 속성 : 
           영속성이 전이되는 관계에서는 삭제 역시 주의가 필요함. 
           AttachBoard인스턴스의 영속상태가 변경되면 포함된 AttachFile인스턴스들의 
           상태역시 변경되어야 하는데, 삭제시에도 같이 삭제가 처리되어야 함. 
           이를 처리하는 방법은 다음과 같은 방식들이 존재함. 
            1. CascadeType.REMOVE 혹은 CascadeType.ALL을 지정하는 방법. 
			2. orphanRemoval 속성을 true로 지정하는 방법. 
		   두 설정 모두 삭제가 된다는 점은 동일하지만, CascadeType을 지정하는 경우에는 
		   JPA에서 처리되는 것이고, orphanRemoval의 경우에는 데이터베이스 영역에서 처리
		   된다는 점에서 차이가 있음.
		   (orphanRemoval은 JPA2버전부터 추가된 속성임.	
 */
	//@Test
	public void testDelete(){
		
		repo.delete(2011L); //2번 첨부 게시물 삭제
		
	}
	
/*
 * ...091p.@Transactional :
           @Transactional 어노테이션을 붙이지 않고 코드를 실행하면 정상적으로 실행되지 않고
           에러가 발생함. 
           	org.hibernate.LazyInitializationException: failed to lazily initialize a collection 
           	of role: org.zerock.domain.AttachBoard.files, could not initialize proxy - 
           	no Session…이하생략
           	
           이런 문제가 발생하는 원인은 SQL문이 2번 실행되기 때문임. 
           JPA에서 ztbl_attach_board 테이블을 접근하는 SQL을 한번 실행해 버렸기 때문에, 
           ztbl_attach_file 데이터를 가져오는 SQL을 실행할 수 없게 된 것임.
            
		   이 문제를 해결하려면 즉시 로딩 방식을 이용하거나 @Transactional을 지정해 주어야 함.
		    
		   @Transactional은 해당 테스트 코드를 실행할 때 하나의 트랜잭션으로 처리하는 것을 의미함. 
		   따라서 메소드 내에서 여러 SQL이 실행되어야 할 때 유용하게 사용할 수 있음.	
 */
	@Transactional
	//@Test
	public void testRead(){

		AttachBoard board = repo.findOne(2014L).get();
	
		System.out.println(board.getAno());
	
		System.out.println("====================");
	
		System.out.println(board.getFiles());

	}
	

/*
 * ...093p.@Commit 어노테이션 :
		SQL을 보면 정상적으로 실행된 듯이 보이지만, 이후에 다음과 같이 자동으로 Roll back 
		되었다는 메시지가 출력됨.
		이는 JPA를 이용해서 테스트를 진행후 자동으로 Roll back 처리하여서 벌어지는 현상임. 
		o.s.t.c.transaction.TransactionContext   : Rolled back transaction for test context 이하 생략…		
		 
		만일 실제로 데이터베이스에 테스트 결과를 반영하고 싶다면 테스트 클래스의 선언부에 
		@Commit 어노테이션을 추가해 주어야 함. 
		
		
		@RunWith(SpringRunner.class)
		@SpringBootTest
		@Commit
		public class AttachBoardTests {
		…이하생략…
		@Commit은 테스트 시에 사용되는 어노테이션이며 org.springframework.test.annotation.Commit 타입임. 
		@Commit을 지정하고 테스트 코드를 다시 실행해 보면 정상적으로 데이터베이스에 저장됨. 
		
		Hibernate: select attachboar0_.ano as ano1_0_0_, attachboar0_.content as content2_0_0_, attachboar0_.del_flag as del_flag3_0_0_, attachboar0_.regdate as regdate4_0_0_, attachboar0_.title as title5_0_0_, attachboar0_.updatedate as updateda6_0_0_, attachboar0_.writer as writer7_0_0_ from tbl_attach_boards attachboar0_ where attachboar0_.ano=?
		3
		Hibernate: insert into tbl_attach_files (aboard_ano, filename) values (?, ?)
		..
		 o.s.t.c.transaction.TransactionContext   : Committed transaction for test context… 이하 생략…
 */
	@Transactional
	@Test
	public void testModify(){

		AttachBoard board = repo.findOne(2017L).get();
	
		System.out.println(board.getAno());
	
		AttachFile newFile = new AttachFile();
		newFile.setFilename("ccc.jpg");
		newFile.setAboard(board);
	
		board.getFiles().add(newFile);
	
		repo.save(board);

	}

}
