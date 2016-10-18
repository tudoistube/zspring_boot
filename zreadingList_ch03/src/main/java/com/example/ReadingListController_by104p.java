package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * ...69p.@Controller : 컴포넌트 검색으로 자동으로 스프링 애플리케이션 컨텍스트에
 *    빈으로 등록함.
 *    @RequestMapping("requestedUrlPath") : 요청된 URL 경로("/")를 매핑해서 처리함. 
 */
//@Controller
//@RequestMapping("/")
public class ReadingListController_by104p {

	//private static final String reader = "JoyWins"; //...by86p.
	
	private ReadingListJpaRepository readingListJpaRepository;
	
	/*
	 * ...69p.생성자에 리포지터리를 주입함.
	 */
	@Autowired
	public ReadingListController_by104p(ReadingListJpaRepository readingListJpaRepository){
		this.readingListJpaRepository = readingListJpaRepository;
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
			model.addAttribute("reader", reader);
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
