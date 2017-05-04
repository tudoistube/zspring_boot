package yes.joywins;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import yes.joywins.dao.BoardRepository;
import yes.joywins.domain.Board;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests {
	
	@Autowired
	private BoardRepository boardRepo;
	
	  //@Test
	  public void inspect(){
	      
	      Class clz = boardRepo.getClass();
	      
	      System.out.println("ClassName : " + clz.getName());
	      
	      Class[] interfaces = clz.getInterfaces();
	      
	      Stream.of(interfaces).forEach(inter -> System.out.println("InterfaceName : " + inter.getName()));
	      
	      Class superClasses = clz.getSuperclass();
	      
	      System.out.println("SuperClassName : " + superClasses.getName());
	  }	
/*
 * ...42p.console output : 
	ClassName : com.sun.proxy.$Proxy82
	InterfaceName : yes.joywins.dao.BoardRepository
	InterfaceName : org.springframework.data.repository.Repository
	InterfaceName : org.springframework.transaction.interceptor.TransactionalProxy
	InterfaceName : org.springframework.aop.framework.Advised
	InterfaceName : org.springframework.core.DecoratingProxy
	SuperClassName : java.lang.reflect.Proxy
 */
	  
	  //@Test
	  public void testInsert() {
		  Board board = new Board();
		  board.setTitle("Title 제목");
		  board.setContent("Content 내용");
		  board.setWriter("user00");
		  
		  boardRepo.save(board);
	  }
/*
 * ...043p.console output :
		   drop table은 현재 JPA의 설정이 spring.jpa.hibernate.ddl-auto=create로 되어 있기 때문에
		   프로젝트가 실행되면 테이블 생성을 시도하면서 drop table로 기존의 테이블을 삭제함. 
           이후에 테이블이 생성되고, 마지막에 insert 구문이 실행됨. 
	Hibernate: drop table if exists hibernate_sequence
	Hibernate: drop table if exists ztbl_boards
	Hibernate: create table hibernate_sequence (next_val bigint) engine=MyISAM
	Hibernate: insert into hibernate_sequence values ( 1 )
	Hibernate: create table ztbl_boards (bno bigint not null, content varchar(255), regdate datetime, title varchar(255), updatedate datetime, writer varchar(255), primary key (bno)) engine=MyISAM
	......
	Hibernate: select next_val as id_val from hibernate_sequence for update
	Hibernate: update hibernate_sequence set next_val= ? where next_val=?
	Hibernate: insert into ztbl_boards (content, regdate, title, updatedate, writer, bno) values (?, ?, ?, ?, ?, ?)

 */
	  
	  //@Test
	  public void testSelect() {
//...044p.ref : Java 8 Optional Class.
//   https://dzone.com/articles/java-8-optional-whats-point
		  Optional<Board> board = boardRepo.findOne(1L);		  
		  
		  System.out.println("board : " + board);
	  }
/*
 * ...043p.console output :
		Hibernate: select next_val as id_val from hibernate_sequence for update
		Hibernate: update hibernate_sequence set next_val= ? where next_val=?
		Hibernate: insert into ztbl_boards (content, regdate, title, updatedate, writer, bno) values (?, ?, ?, ?, ?, ?)
		Hibernate: select board0_.bno as bno1_0_0_, board0_.content as content2_0_0_, board0_.regdate as regdate3_0_0_, board0_.title as title4_0_0_, board0_.updatedate as updateda5_0_0_, board0_.writer as writer6_0_0_ from ztbl_boards board0_ where board0_.bno=?
		board : Optional[Board(bno=1, title=Title 제목, writer=user00, content=Content 내용, regdate=2017-04-28 11:54:07.0, updatedate=2017-04-28 11:54:07.0)]
		
 * ...045p.Spring Data JPA는 기본적으로 Hibernate라는 JPA구현체를 이용함. 
           Hibernate는 내부적으로 지정되는 DB에 맞게 SQL문을 생성하는 Dialect 가 존재함. 
           Dialect는 Hibernate가 다양한 데이터베이스를 처리하기 위해 각 데이터베이스마다 
           다른 SQL문법을 처리하기 위해 존재함. 
           JPA를 통해서 호출하면 설정된 데이터베이스에 맞게 SQL문이 생성되는데 이 역할을 하는 존재를 Dialect라고 함. 
           내부적으로는 OracleDialect, MySQLDialect같은 클래스등이 있음. 		
 */
	  
	  //@Test
	  public void testUpdate(){

		  System.out.println("Read First.........................");
/*
 * ...045p.repo.findOne( ).get( )으로 하면 문제는 해결됩니다. 
 */
		  //Optional<Board> board = boardRepo.findOne(1L);
		  Board board = boardRepo.findOne(1L).get();
	
		  System.out.println("Update Title.......................");
		  board.setTitle("수정된 제목입니다");
	
		  System.out.println("Call Save( ).......................");
		  boardRepo.save(board);

	  }
	  
	  @Test
	  public void testDelete(){
	    
	    System.out.println("DELETE Entity ");
	    
	    boardRepo.delete(5L);
	  }	  
	  
}

