package com.example;
//...129p.

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ZreadingBooksApplication.class)
@WebAppConfiguration
public class MockMvcWebTests {
	
	@Autowired
	private WebApplicationContext webContext;
	
	private MockMvc mockMvc;
	

	@Before
	public void setupMockMvc() throws Exception {
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
		/*
		 * ...129p.springSecurity() 는 SecurityMockMvcConfigurers 의 정적 메서드임. 
		 *    MockMVC 용으로 스프링 시큐리티를 활성화하는
		 *    Mock MVC 구성자를 반환하여, MockMvc 로 수행하는 모든 요청에 스프링
		 *    시큐리티가 적용됨.
		 */
									  .apply(springSecurity()) //...129p.added.
									  .build();
	}
	

	/*
	 * ...129p.스프링 시큐리티가 활성화되면 인증된 요청이 아니면 로그인 페이지로
	 *    리다이렉트되도록 기대됨.
	 */
    @Test
    public void homePage_unauthenticatedUser() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().is3xxRedirection())
            .andExpect(header().string("Location", "http://localhost/login"));
    }

    /*
     * ...130p.인증된 요청의 테스트 수행과 관련한 2개의 어노테이션.
     *    @WithMockUser : 지정한 사용자 이름, 패스워드, 권한으로 UserDetails 를 생성한 후
     *       보안 컨텍스트를 로드함.
     *    @WithUserDetails : 지정한 사용자 이름으로 UserDetails 객체를 조회하여 보안
     *       보안 컨텍스트를 로드함.
     *    보통 @WithMockUser 어노테이션을 사용함.
     *       예) @Test
     *           @WithMockUser(username="2Be", password="2Do", roles="READER")
     *           public void homePage_authenticatedUser() throws Exception {
     *           ...
     *    하지만, 여기서는 일반적인 UserDetails 가 아닌 UserDetails 를 구현한 Reader 가
     *       필요하므로 @WithUserDetails 어노테이션을 사용해야 함.
     *       @WithUserDetails 어노테이션은 UserDetails 객체를 로드하도록 구성된 
     *       UserDetailsService 를 사용함.
     * 
     */
    /*
     * ...실행결과 오류 메시지 : Unable to create SecurityContext using @org.springframework.security.test.context.support.WithUserDetails
     *    https://stackoverflow.com/questions/38275420/issue-with-withuserdetails-and-spring-boot-1-4-testentitymanager
     *    http://arahansa.github.io/docs_spring/security.html
     * 
     */
    @Test
    @WithUserDetails("2Be") //...2Be 를 사용자로 사용함.
    public void homePage_authenticatedUser() throws Exception {
    	//...반환할 Reader 생성.
        Reader expectedReader = new Reader();
        expectedReader.setUsername("2Be");
        expectedReader.setPassword("password");
        expectedReader.setFullname("Craig Walls");

        mockMvc.perform(get("/")) //...GET 요청 수행함.
            .andExpect(status().isOk())
            .andExpect(view().name("readingList"))
            .andExpect(model().attribute("reader", samePropertyValuesAs(expectedReader)))
            .andExpect(model().attribute("books", hasSize(0)))
            .andExpect(model().attribute("amazonID", "habuma-20"));
    }    
    
    
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
