package yes.joywins.dao;

import org.springframework.data.repository.CrudRepository;

import yes.joywins.domain.Board;

/*
 * ...39p.인터페이스를 구현한는 것 만으로도, JPA와 관련된 모든 처리가 끝남. 
          인터페이스를 기준으로 해서 동적으로 실행 가능해지는 클래스가 만들어짐(동적 프록시 기법).  
 */
public interface BoardRepository extends CrudRepository<Board, Long> {

}
