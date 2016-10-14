package readinglist;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * ...53p.컴포넌트 검색과 자동 구성 활성화.
 *    @SpringBootApplication = @Configuration + @ComponentScan + @EnableAutoConfiguration
 *    스프링 부트 1.2.0 ~ 사용함.
 *    @Configuration : @Configuration 이 붙은 클래스를 스프링의 자바 기반 구성 클래스로 지정함.
 *    @ComponentScan : 컴포넌트를 자동으로 검색해서 스프링 애플리케이션 컨텍스트에 빈으로 등록시킴.
 *    @EnableAutoConfiguration : 어떤 구성이든 자동으로 하는 만능 어노테이션임.
 *                               이 한줄로 스프링 부트의 자동 구성이 일어나고 많은 코드를 대체함.
 */
@SpringBootApplication
//public class ZreadingBooksApplication { //...by85p.
public class ReadingListApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		//...애플리케이션 부트스트랩.
		SpringApplication.run(ReadingListApplication.class, args);
	}
	
	/*
	 * ...85p.login 요청 경로를 받으면 로그인 페이지를 보여줌.
	 *    addViewControllers 에서 login 요청 경로를 login 템플릿으로 매핑함.
	 *    
	 */
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
	
	/*
	 * ...85p.Reader 타입의 객체가 컨트롤러 매개변수로 있을 때 처리할 리졸버를 설정함.
	 * 
	 */
	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ReaderHandlerMethodArgumentResolver());
    }
    
}
