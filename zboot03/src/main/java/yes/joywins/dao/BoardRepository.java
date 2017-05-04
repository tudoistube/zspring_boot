package yes.joywins.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import yes.joywins.domain.Board;

/*
 * ...39p.인터페이스를 구현한는 것 만으로도, JPA와 관련된 모든 처리가 끝남. 
          인터페이스를 기준으로 해서 동적으로 실행 가능해지는 클래스가 만들어짐(동적 프록시 기법).  
 */
public interface BoardRepository extends CrudRepository<Board, Long> {
/*
 * ...51p.Spring Data JPA의 경우 메소드의 이름만으로 원하는 질의(query)를 실행
          할 수 있는 방법을 제공함. 
          이 때 쿼리라는 용어는 ‘select’에만 해당한다는 것을 주의해야 함.
          쿼리메소드의 시작은 다음과 같은 단어들로 시작함. 
		  find…By…, read…By, query…By, get…By, count…By 

          예를 들어 ‘find..By’로 작성한다면 ‘find’ 뒤에 엔티티타입을 지정함. 
          Board클래스의 경우라면 ‘findBoardBy…’가 됨. 
          만일 중간에 엔티티타입을 지정하지 않는다면 현재 실행하는 Repository의 
          타입 정보를 기준으로 동작하게 됨. 
         ‘By’의 뒤쪽에는 컬럼명을 이용해서 구성함. 
          예를 들어 제목으로 검색하고자 한다면 ‘findBoardByTitle’이 됨. 
          쿼리메소드의 리턴타입은 Page<>, Slice<>, List<>의 형태가 될 수 있음. 
          가장 무난한 것은 List<>타입을 이용하는 것임.
 */
	//List<Board> findBoardByTitle(String title);

/*
 * ...055p.@Query에 사용하는 것은 JPQL(객체 쿼리라고 합니다)을 이용함. 
          가장 기본적인 형태는 테이블 대신에 엔티티 타입을 이용한다는 것과 
          컬럼명 대신에 엔티티의 속성을 이용한다는 점이고, 
          기본적인 order by 나 group by 역시 지원합니다. 
          @Query의 내용물에 ‘%?1%’에서 ‘?’는 JDBC상에서 PreparedStatement에서 
          사용한 것과 동일하다고 생각하면 되고, ‘?1’은 첫 번째로 전달되는 파라미터라고
          생각하면 됨. 
          이에 대한 테스트 코드에서 JPQL 에 기술된 Board 는 yes.joywins.domain.Board 클래스이며
          이의 별명인 board 는 값을 담는데 사용됨. 
 */	
	@Query("select board from Board board"
			+ " where board.title like %?1% "
			+ " and board.bno > 0 order by board.bno desc")
	List<Board> findBoardByTitle(String title);
	
/*
 * ...057p.@Query에는 @Param 을 적용할 수 있음.
           내용에 대한 검색 처리 역시 이전과 동일한 방식으로 처리할 수 있습니다만, 
           @Query에는 @Param 적용할 수 있으므로 예제 작성을 해 보도록 합니다. 
           작성된 코드를 보면 기존과 달리 ‘%:content%’와 같이 처리되는 것을 볼 수 있음. 
           파라미터에서는 @Param이라는 어노테이션을 이용하는 것을 볼 수 있음. 
           @Param은 org.springframework.data.repository.query.Param 클래스를 이용함. 
           이를 이용해서 여러 개의 파라미터를 전달할 때 쉽게 이름으로 구분해서 전달할 수 있음.
 */
	@Query("select board from Board board"
			+ " where board.content like %:content%"
			+ " and board.bno > 0 order by board.bno desc")
	List<Board> findByContent(@Param("content")String content);	

	
	
/*
 * ...057p.현재 Repository의 엔티티 타입을 자동으로 사용하는 - #{#entityName}
           #{#entityName}은 Repository인터페이스를 정의할 때 <엔티티 타입, PK 타입 >에서 
           엔티티 타입을 자동으로 참고함. 
           작성자에 대한 검색 역시 위의 두 가지 방식을 이용해서 작성해 줄 수 있음. 
           예제에서는 현재 Repository의 엔티티 타입을 자동으로 사용하는 예제임.
           지금같이 단순한 예제의 경우에는 그다지 쓸모가 없지만, 유사한 상속 구조의 
           Repository인터페이스를 여러 개 생성하는 경우라면 유용하게 사용할 수 있음. 
 */
	@Query("select board from #{#entityName} board"
			+ " where board.writer like %?1%"
			+ " and board.bno > 0 order by board.bno desc")
	List<Board> findByWriter(String writer);

/*
 * ...057p.@Query를 활용하면서 좋은 장점을 몇 가지... : 
           1. 리턴 값이 반드시 엔티티 타입이 아니라 필요한 몇 개의 컬럼 값들만 추출할 수 있음.
			  @Query를 이용하면 ‘select ‘로 시작하는 JPQL구문을 작성하는데 이 때 필요한 컬럼만을 
			  추출할 수 있음. 
              예를 들어, List<Object[]> findByTitle2 메서드는 content 컬럼의 내용은 제외하고 
              조회하고 있음.
              특이한 점은 리턴 타입이 Object[ ]의 리스트의 형태로 작성된다는 점임. 
               
           2. nativeQuery 속성을 지정해서 데이터베이스에 사용하는 SQL을 그대로 사용할 수 있음.
            
           3. Repository에 지정된 엔티티 타입 뿐 아니라 필요한 엔티티 타입을 다양하게 사용할 수 있음.
 	
 */
	@Query("select board.bno, board.title, board.writer, board.regdate "
	      +" from Board board where board.title like %?1%"
	      +" and board.bno > 0 order by board.bno desc")
	List<Object[]> findByTitle3(String title);
	
/*
 * ...058p.nativeQuery의 사용 
           @Query의 경우 말 그대로 데이터베이스에 종속적인 SQL문을 그대로 사용할 수 있기 때문에 
           복잡한 쿼리를 작성할 때에는 유용하게 사용할 수 있습니다. 
           다만 이 경우 데이터베이스에 독립적이라는 장점을 어느정도 포기해야 함.
 */
	@Query(value="select bno, title, writer from ztbl_boards"
			    +" where title like CONCAT('%', ?1, '%')"
			    +" and bno > 0 order by bno desc", nativeQuery=true )
	List<Object[]> findByTitle4(String title);
	
/*
 * ...059p.Paging 처리와 정렬 
           Spring Data JPA의 경우 Pageable 인터페이스를 이용해서 별도의 SQL 작성없이 처리할 수 있음. 
           만일 페이징 처리가 필요한 구문이 있다면 메소드의 파라미터로 Pageable을 추가하는 것 만으로
           구현이 가능함. 
           @Query에는 다른 조건은 없고 단지 페이징 처리에 필요한 ‘bno > 0’조건과 ‘order by’ 조건
           만을 부여하고 있음. 
           
		   Pageable은 org.springframework.data.domain.Pageable 인터페이스임. 
		   따라서 구현하기 위해서 PageRequest라는 클래스를 이용해서 사용함. 
		   PageRequest는 다음과 같은 생성자를 가짐. 

		   PageRequest(int page, int size)
		   	페이지 번호(0부터 시작), 페이지당 데이터의 수 
		   PageRequest(int page, int size, Sort.Direction direction, String... props)
		   	페이지 번호, 페이지당 데이터의 수, 정렬 방향, 속성(컬럼)들 
		   PageRequest(int page, int size, Sort sort)
			페이지 번호, 페이지당 데이터의 수, 정렬 방향 
 */
	@Query("select board from Board board"
			+ " where board.bno > 0 order by board.bno desc")
	List<Board> findBypage(Pageable pageable);	
	
}

/*
 
 */