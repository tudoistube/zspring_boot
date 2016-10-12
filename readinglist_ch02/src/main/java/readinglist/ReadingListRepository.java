package readinglist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * ...67p.스프링 데이터 JPA 의 JpaRepository 인터페이스를 상속한 인터페이스로
 *    DTO 객체를 영속화할 수 있는 리포지토리를 선언함.
 *    JpaRepository 인터페이스는 리포지터리가 사용할 도메인 타입과 클래스의 ID
 *    프로퍼티 타입을 매개변수로 받음.
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
