package com.example;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/*
 * ...54p.책의 예제에 있는 pom.xml 의 <artifactId> spring-boot-starter-parent 의 
 *    <version>1.3.6.RELEASE 를 기준으로 해서, STS 에서 직접 생성할 때의 어노테이션과
 *    다름에 주의함.
 *    1.4.1.RELEASE 기준으로 STS 에서 생성한 경우에는 아래 2개의 어노테이션이 생성됨.
 *    @RunWith(SpringRunner.class), @SpringBootTest
 *    
 * ...120p.@RunWith 어노테이션에 SpringJUnit4ClassRunner.class 를 전달하여 통합 
 *    테스트를 활성화함.
 *    스프링4.2부터는 선택적으로 SpringJUnit4ClassRunner 대신에 JUnit 규칙 기반의
 *    SpringClasRule과 SpringMethodRule을 사용할 수 있음.
 *    
 *    @ContextConfiguration 어노테이션에는 애플리케이션을 어떻게 로드할지 지정함.    
 *    SpringJUnit4ClassRunner.class 는 애플리케이션 컨텍스트를 로드할 뿐만 아니라
 *    자동 연결(Autowiring)하여 애플리케이션 컨텍스트에서 테스트로 빈을 주입할 수 있음.
 *    ( 단, @ContextConfiguration 어노테이션은 @SpringApplicationConfiguration 어노테이션
 *    처럼 외부 프로퍼티(application.properties 또는 application.yml)로딩과 스프링 부트의
 *    로깅 및 다른 기능을 활성화하는 통합 테스트 기능이 없음 )
 *      
 *    @SpringApplicationConfiguration 어노테이션을 사용해서 출시용 애플리케이션에서 로드
 *    할때와 동일한 방식으로 SpringApplication을 사용하여 스프링 애플리케이션 컨텍스트를
 *    로드하고 외부 프로퍼티 로딩과 스프링 부트 로깅을 포함한 스프링 부트의 통합 테스트
 *    기능을 사용함.    
 *     
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ZreadingBooksApplication.class)
/*
 * ...123p. MockMvcBuilders 의 webAppContextSetup() 는 WebApplicationContext 를 인자로 받아서
 *    테스트 클래스에 @WebAppConfiguration 어노테이션을 붙이고, @Autowired 어노테이션을 사용
 *    하여 WebApplicationContext 인스턴스 변수를 주입하도록 함.
 *    SpringJUnit4ClassRunner 가 어플리케이션 컨텍스트로 기본값인 비웹용 ApplicationContext 가
 *    아니라 WebApplicationContext 를 생성하도록 선언함. 
 */
@WebAppConfiguration
public class ZreadingBooksApplicationTests_by124p {
	
	@Autowired
	private WebApplicationContext webContext;
	
	private MockMvc mockMvc;

	/*
	 * ...124p.@Before 어노테이션을 붙여 다른 테스트 메서드보다 먼저 실행함을 나타냄.
	 *    주입된 WebApplicationContext 를 webAppContextSetup() 메서드에 전달한 후
	 *    build() 메서드를 호출하여 MockMvc 인스턴스를 생성하고, 테스트 메서드에서
	 *    사용할 인스턴스 변수에 할당함.
	 */
	@Before
	public void setupMockMvc() {
		/*
		 * ...122p.실제 서블릿 컨테이너에서 컨트롤러를 실행하지 않고도 컨트롤러에
		 *    HTTP 요청을 할 수 있음.
		 *    MockMVC 를 설정하려면 MockMvcBuilders 를 사용하고, 2개의 정적 메서드를
		 *    제공함.
		 *    1. standaloneSetup() : 테스트할 컨트롤러를 수동으로 초기화하고 주입함.
		 *       하나의 컨트롤러에 집중하여 테스트하는 용도로만 사용하는 유닛 테스트.
		 *    2. webAppContextSetup() : 스프링이 로드한 WebApplicationContext 의
		 *       인스턴스로 작동함. 
		 *       완전한 통합테스트를 할 수 있게 함.
		 *              
		 */
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
	
	@Test
	public void homePage() throws Exception{
		/*
		 * ...124p. 간단한 '/'로 HTTP GET 요청을 수행하고 기대한 모델과 뷰를 반환하는지 검증함.
		 */
		this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
				    .andExpect(MockMvcResultMatchers.status().isOk())
				    .andExpect(MockMvcResultMatchers.view().name("readingList"))
				    .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
				    .andExpect(MockMvcResultMatchers.model().attribute("books", Matchers.empty()));
	}

}
