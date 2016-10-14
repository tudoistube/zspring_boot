/*
 * ...67p.ReadingListRepository 와는 다름.
 * ...87p.JpaRepository 를 상속받으므로 구현 코드를 별도로 작성할 필요는 없음.
 */
package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, String> {

}
