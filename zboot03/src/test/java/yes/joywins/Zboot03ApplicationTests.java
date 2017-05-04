package yes.joywins;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Table;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;

import yes.joywins.dao.BoardRepository;
import yes.joywins.domain.Board;

//...050p.
@RunWith(SpringRunner.class)
@SpringBootTest
public class Zboot03ApplicationTests {
	
	@Autowired
	private BoardRepository repo;


	//@Test
	public void contextLoads() {
	}
	
	//@Test
	public void testInsert200() {
	
		for (int i = 1; i <= 200; i++) {
	
		  Board board = new Board();
		  board.setTitle("제목.." + i);
		  board.setContent("내용 ...." + i + " 채우기 ");
		  board.setWriter("user0" + (i % 10));
		  repo.save(board);
	
		}

	}

/*
 * ...055p./zboot03/src/main/java/yes/joywins/dao/BoardRepository.java
	@Query("select board from Board board"
			+ " where board.title like %?1% "
			+ " and board.bno > 0 order by board.bno desc")
	List<Board> findBoardByTitle(String title); 의 @Query 에 기술한
	@Table(name="ztbl_boards") 설정이 된 Board 클래스의 변수 board 가
	아래 findBoardByTitle 의 board 에 사용됨.
 */
	//@Test
	public void testByTitle(){

		repo.findBoardByTitle("제목..200")
		.forEach(board -> System.out.println("board1 : " + board));

	}
	
	//@Test
	public void testByTitle2(){

		repo.findBoardByTitle("제목..2")
		.forEach(board -> System.out.println("board2 : " + board));

	}
	
	
/*
 * ...058p.@Query를 이용하면 ‘select ‘로 시작하는 JPQL구문을 작성하는데 
           이 때 필요한 컬럼만을 추출할 수 있습니다. 
 */
	//@Test
	public void testByTitle3(){

		repo.findByTitle3("88")
		.forEach(arr -> System.out.println(Arrays.toString(arr)));

	}
	
	@Test
	public void testByPaging(){
	
		Pageable pageable = new PageRequest(5, 10);
	
		repo.findBypage(pageable).forEach(
			board -> System.out.println(board));

	}	
	
}

/*
 * ...052p.console output :
			Hibernate: select board0_.bno as bno1_0_, board0_.content as content2_0_, board0_.regdate as regdate3_0_, board0_.title as title4_0_, board0_.updatedate as updateda5_0_, board0_.writer as writer6_0_ from ztbl_boards board0_ where board0_.title=?
			board : Board(bno=183, title=제목..177, writer=user07, content=내용 ....177 채우기 , regdate=2017-04-28 15:42:34.0, updatedate=2017-04-28 15:42:34.0)
		
 * ...052p.실행되는 SQL문을 보면 ‘from tbl_boards board0_ where board0_.title=?’와 같은 형태로 
           작성되는 것을 확인할 수 있음. 
           
 * ...052p.이러한 방식의 쿼리메소드를 작성하는 방법은 Spring Data JPA문서를 참고하면 됩니다. 
 *         아래의 표는 http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/jpa.repositories.html 의 
 *         내용을 그대로 사용한 것입니다. 
			Keyword / Sample / JPQL snippet
			
			And
			findByLastnameAndFirstname
			… where x.lastname = ?1 and x.firstname = ?2
			Or
			findByLastnameOrFirstname
			… where x.lastname = ?1 or x.firstname = ?2
			Between
			findByStartDateBetween
			… where x.startDate between 1? and ?2
			LessThan
			findByAgeLessThan
			… where x.age < ?1
			GreaterThan
			findByAgeGreaterThan
			… where x.age > ?1
			After
			findByStartDateAfter
			… where x.startDate > ?1
			Before
			findByStartDateBefore
			… where x.startDate < ?1
			IsNull
			findByAgeIsNull
			… where x.age is null
			IsNotNull,NotNull
			findByAge(Is)NotNull
			… where x.age not null
			Like
			findByFirstnameLike
			… where x.firstname like ?1
			NotLike
			findByFirstnameNotLike
			… where x.firstname not like ?1
			StartingWith
			findByFirstnameStartingWith
			… where x.firstname like ?1 (parameter bound with appended %)
			EndingWith
			findByFirstnameEndingWith
			… where x.firstname like ?1 (parameter bound with prepended %)
			Containing
			findByFirstnameContaining
			… where x.firstname like ?1 (parameter bound wrapped in %)
			OrderBy
			findByAgeOrderByLastnameDesc
			… where x.age = ?1 order by x.lastname desc
			Not
			findByLastnameNot
			… where x.lastname <> ?1
			In
			findByAgeIn(Collection<Age> ages)
			… where x.age in ?1
			NotIn
			findByAgeNotIn(Collection<Age> age)
			… where x.age not in ?1
			True
			findByActiveTrue()
			… where x.active = true
			False
			findByActiveFalse()
			… where x.active = false
           

 */


