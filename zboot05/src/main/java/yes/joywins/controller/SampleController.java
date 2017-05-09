package yes.joywins.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * ...098p.Thymeleaf 를 사용하는 컨트롤러의 구성 :
           Thyemeleaf는 기본적으로 아무런 추가 설정이 없는 경우에는 
           확장자는 ‘.html’을 사용하고, 작성된 화면은 서버의 내부에서 
           보관되어, 재처리 없이 빠르게 서비스할 수 있는 환경으로 세팅됨. 
           
           기본적으로 개발시에 알아두어야 하는 내용들은 다음과 같습니다.
            1. 확장자는 ‘html’로 기본 설정함.
               application.properties에서 별도의 확장자를 지정하지 않았다면 
               ‘.html’이 확장자가 됨.
            2. 인코딩은 ‘utf-8’방식으로 설정함.
            3. Mime타입은 ‘text/html’로 설정함.
            4. 서버 내부의 cache설정은 true 로 함.
            
           이 기본 설정의 경우 Thymeleaf로 개발된 화면을 수정하면, 매번 프로젝트를 
           재시작하는 불편함을 가져야 하기 때문에 개발시에는 서버 내부의 보관(caching)을 
           하지 않도록 설정해 주는 것이 좋음.
           
           application.properties 파일 
           spring.thymeleaf.cache=false
            
           Thymeleaf를 이용하는 예를 만들기 위해서는 필수적으로 
           Spring MVC의 컨트롤러들을 구성할 필요가 있음.  
           기존의 JSP대신에 Thymeleaf로 된 템플릿을 이용하지만, 
           Web MVC의 구성 자체가 달라지는 것은 아님.
           작성된 SampleController는 @Controller 어노테이션과 
           @RequestMapping 혹은 @GetMapping, @PostMapping등을 
           이용해서 구성함. 
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import yes.joywins.domain.MemberVO;

@Controller
public class SampleController {
	
	@GetMapping("/sample1")
	public void sample1(Model model){
/*
 * ...102p.DevTools 속성 : 
           현재 프로젝트의 경우 프로젝트의 생성시에 ‘DevTools’를 추가해 주었기 때문에, 
           컨트롤러를 수정하는 작업 역시 자동으로 재시작됨.  

           프로젝트가 실행된 상태에서도 위와 같이 코드를 수정하면 자동으로 재시작되므로, 
           개발시에 유용하게 사용할 수 있음. 
 */
		model.addAttribute("greeting", "안녕, JoyWins!!!");

	}
	
/*
 * ...105p.객체를 화면에 출력하기.
 */
	@GetMapping("/sample2")
	public void sample2(Model model){

		MemberVO vo = new MemberVO(123,"u00","p00","홍길동", new Date());
	
		model.addAttribute("vo", vo);

	}
	
/*
 * ...106p.리스트를 화면에 출력하기. 
           템플릿으로 가장 많이 사용하는 루프를 처리해야하는 상황에서는 
           th:each를 이용해서 처리할 수 있음. 	
 */
	@GetMapping("/sample3")
	public void sample3(Model model){

		List<MemberVO> list = new ArrayList<>();
	
		for(int i = 0; i < 10; i++){
	
		  list.add(new MemberVO(123,"u0"+i,"p0"+i,"홍길동"+i, new Date()));
		}
	
		model.addAttribute("list", list);

	}	

/*
 * ...108p.지역변수의 선언, if ~unless 제어 처리 :
           Thyemeleaf에서는 특정한 범위에서만 유효한 지역변수를 th:with를 이용해서 
           선언할 수 있음.
 */
	@GetMapping("/sample4")
	public void sample4(Model model){

		List<MemberVO> list = new ArrayList<>();
	
		for(int i = 0; i < 10; i++){
	
		  list.add(new MemberVO(i,"u0"+i,"p0"+i,"홍길동"+i, new Date()));
		}
	
		model.addAttribute("list", list);  

	}	
	
/*
 * ...111p.인라인 스타일로 Thymeleaf 사용하기 : 
           JSP페이지를 작성하다보면 가끔은 EL을 이용해서 자바스크립트 코드를 작성하는 
           경우가 종종 있음. 
           Thymeleaf에서는 이러한 경우에 별도의 표기방법을 지원해 주고 있음. 
           th:inline이라는 것을 이용해서 ‘javascript’나 ‘text’를 지정해서 사용함.
           
		   화면에 result라는 결과를 문자열로 전달하는 경우임.            	
 */
	@GetMapping("/sample5")
	public void sample5(Model model){

		String result = "SUCCESS";
	
		model.addAttribute("result", result);  
		
		List<MemberVO> list = new ArrayList<>();
		
		for(int i = 0; i < 10; i++){
	
		  list.add(new MemberVO(i,"u0"+i,"p0"+i,"홍길동"+i, new Date()));
		}
	
		model.addAttribute("list", list);  		

	}
	

/*
 * ...112p.Thymeleaf의 inlining은 일반 태그 작성시에도 사용할 수 있음.	
 */
	@GetMapping("/sample6")
	public void sample6(Model model) {
	
		List<MemberVO> list = new ArrayList<>();
	
		for (int i = 0; i < 10; i++) {
	
		  list.add(new MemberVO(i, "u0" + i, "p0" + i, "홍길동" + i, new Date()));
		}
	
		model.addAttribute("list", list);
	
		String result = "SUCCESS";
	
		model.addAttribute("result", result);

	}

/*
 * ...113p.Thymeleaf의 레이아웃 기능.	
 */
	@GetMapping("/sample7")
	public void sample7(Model model) {
	
		List<MemberVO> list = new ArrayList<>();
	
		for (int i = 0; i < 10; i++) {
	
		  list.add(new MemberVO(i, "u0" + i, "p0" + i, "홍길동" + i, new Date()));
		}
	
		model.addAttribute("list", list);

	}
	
/*
 * ...114p.Thymeleaf layout dialect를 이용한 레이아웃 재사용하기.	
 */
	@GetMapping("/sample8")
	public void sample8(Model model){

		model.addAttribute("page_title", "...114p.Thymeleaf layout dialect를 이용한 레이아웃 재사용하기.");  

	}  		
	

}
