package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * ...67p.@Entity 어노테이션을 붙여 클래스를 JPA 엔티티로 지정함.
 *    없으면 테이블 생성이 안됨.
 */
@Entity
public class BookDto {

	/*
	 * ...67p.@Id 어노테이션을 붙여 엔티티의 유일성을 식별함.
	 *    @GeneratedValue 어노테이션 : 자동으로 값을 제공함.
	 */
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
    private Long id;
    //private String reader; //...by87p.    
    private Reader reader;
    private String isbn;
    private String title;
    private String author;
    private String description;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	        
	
}
