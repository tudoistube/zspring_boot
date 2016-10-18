package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * ...69p.@Controller : 컴포넌트 검색으로 자동으로 스프링 애플리케이션 컨텍스트에
 *    빈으로 등록함.
 *    @RequestMapping("requestedUrlPath") : 요청된 URL 경로("/")를 매핑해서 처리함. 
 */
@Controller
@RequestMapping("/")
/*
 * ...104p.프로퍼티 주입.
 *    구성 프로퍼티에 있는 값을 빈의 프로퍼티에 주입시킴.
 *    즉, prefix 속성값(amazon)이 있는 프로퍼티를 ReadingListController 빈에 주입시킴.
 *    application.yml 에서 amazon.associateId 속성값을 readingList.html 에 전달함.
 *    pom.xml :: spring-boot-configuration-processor 의존성 추가해야 함.
 * ...106p.스프링 부트 프로퍼티 리졸버(Property Resolver)는 Camel-case 프로퍼티와
 *    하이픈 또는 밑줄로 비슷하게 명명된 프로퍼티를 상호 변환하여 처리할 수 있음.
 *    예) amazon.associateId 프로퍼티는 amazon.associate_id, amazon.associate-id 와 같음.
 *    
 *    책의 예제 소스에서는 AmazonProperties.java 클래스에서 application.yml 파일의 속성값을
 *    읽어서 처리함.
 */
//...S.107p.AmazonProperties 빈 생성으로 주석 처리.
//@ConfigurationProperties(prefix="amazon") //...104p.added.
//...E.107p.AmazonProperties 빈 생성으로 주석 처리.
public class ReadingListController {

	//private static final String reader = "JoyWins"; //...by86p.
	
	private ReadingListJpaRepository readingListJpaRepository;
	
	//...S.107p.AmazonProperties 빈 생성으로 주석 처리.
	//private String associateId; //...104p.added.
	//...E.107p.AmazonProperties 빈 생성으로 주석 처리.
	
	private AmazonProperties amazonProperties; //...107p.added.
	
	/*
	 * ...69p.생성자에 리포지터리를 주입함.
	 */
	@Autowired
	//public ReadingListController(ReadingListJpaRepository readingListJpaRepository) {//...by107p.
	public ReadingListController(ReadingListJpaRepository readingListJpaRepository,
								 AmazonProperties amazonProperties) {
		this.readingListJpaRepository = readingListJpaRepository;
		this.amazonProperties = amazonProperties;//...107p.added.AmazonProperties 주입.
	}	
	

	/*
	 * ...69p.독자의 Book 리스트를 생성자에 주입된 리포지터리에서 조회함.
	 */
	@RequestMapping(method=RequestMethod.GET)
	//public String readersBooks(Model model){ //...by87p.	
	public String readersBooks(READER_DTO reader, Model model){
		
		List<BookDto> readingList = readingListJpaRepository.findByReader(reader);
		
		if(readingList != null){
			model.addAttribute("books", readingList);
			model.addAttribute("reader", reader);//...87p.added.사용자정보.
			//...104p.application.yml 에서 amazon.associateId 속성값을 readingList.html 에 전달함.
			model.addAttribute("associateId", this.amazonProperties.getAssociateId()); //...107p.modified.
		}
		
		return "readingList";
	}
	
	/*
	 * ...69p.BookDto 의 reader 프로퍼티를 독자의 이름으로 설정하고 리포지터리의
	 *    save() 메서드를 이용하여 저장함.
	 *    '/'로 리다이렉트(redirect:/)하도록 지정하여 readersBooks 메서드가 처리하여
	 *    readingList 라는 논리적인 뷰를 반환함.
	 */
	@RequestMapping(method=RequestMethod.POST)
	//public String addToReadingList(BookDto book){ //...by87p.
	public String addToReadingList(READER_DTO reader, BookDto book){
		
		book.setReader(reader);
		
		readingListJpaRepository.save(book);
		
		return "redirect:/";
	}
	
	
}
