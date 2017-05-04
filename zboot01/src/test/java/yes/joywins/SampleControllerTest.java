package yes.joywins;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import yes.joywins.controller.SampleController;

/*
 * ...20p.Controller Test.
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleControllerTest {

 * ...20p.@WebMvcTest : 
 *        @WebMvcTest어노테이션을 사용하면 
 *        @Controller, @Component, @ControllerAdvice 등이 작성된 코드를 
 *        인식할 수 있음. 
 *        MockMvc 객체를 @WebMvcTest 와 같이 사용하면 별도의 생성없이도 주입(@Autowired)만으로 
 *        코드를 작성할 수 있기 때문에 편리함.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {
	
	  @Autowired
	  MockMvc mock;
	  
	  //@Test
	  public void testHi() throws Exception{
	    
	    mock.perform(get("/hi")).andExpect(content().string("Hi Springboot!"));
	  }
	  
/*
 * ...20p.org.springframework.test.web.servlet.MockMvc :
 *        MockMvc 객체의 경우 perform( )을 이용해서 객체를 브라우저에서 서버의 URL을 호출하듯이 
 *        테스트를 진행할 수 있고, 결과를 andExpect( )를 이용해서 확인할 수 있음. 
 *        andExpect( )는 Response에 대한 정보를 체크하는 용도로 사용할 수 있음. 
 *        예를 들어 정상적인 응답 상태이고, 응답으로 전송되는 결과를 보고 싶다면 아래와 같은 코드를
 *        작성하면 확인할 수 있음. 	  
 */
	  @Test
	  public void testHi2() throws Exception{
	    
		    MvcResult result = mock.perform(get("/hi"))
		    	     .andExpect(status().isOk())
		    	     .andExpect(content().string("Hi Springboot!")).andReturn();
		    	    
		    	    System.out.println("result : " + result.getResponse().getContentAsString());
	  }
}
