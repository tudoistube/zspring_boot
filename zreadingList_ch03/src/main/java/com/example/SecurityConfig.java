/*
 * ...82p.스프링 시큐리티의 자동구성을 오버라이딩함.
 *    WebSecurityConfigurerAdapter 을 확장해서 자동구성을 오버라이딩함.
 */
package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/*
 * ...108p.프로파일은 다른 빈이나 구성 클래스를 런타임에 활성화된 프로파일을 기반으로
 *    사용하거나 무시하는 조건부 구성 타입임.
 *    예) 보안구성을 출시 환경에서만 사용하게 하고, 개발 환경에서는 자동으로 구성된
 *        보안 구성을 사용함.
 *        'production' 은 application.yml 에 정의되어 있음.
 *        application.properties 파일에는 spring.profiles.active=production 으로 
 *        설정되있음.
 *    application-{profile}.properties, application-{profile}.yml 형태로 추가적인
 *    프로퍼티 파일을 생성하고, 공통적인 프로퍼티는 application.properties,
 *    application.yml 에 정의함.
 *    YAML 파일을 이용하면 application.yml 파일 하나에 모든 프로파일의 구성 프로퍼티를
 *    담을 수 있음.
 *     
 */
//@Profile("production")
/*
 * ...84p.Customised 정의 보안 구성 클래스를 선언하면, 스프링 부트는 보안 자동 구성을
 *    건너뛴 채 사용자 정의 보안 구성을 사용함.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ReaderRepository readerRepository;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) 
			throws Exception {
		
		//...83p.사용자 정의 UserDetailService.		
		auth.userDetailsService(new UserDetailsService(){

			@Override
			public UserDetails loadUserByUsername(String username) 
					throws UsernameNotFoundException {

				return (UserDetails) readerRepository.findOne(username);
			}
			
		});
	}

	@Override
	protected void configure(HttpSecurity http) 
			throws Exception {

		/*
		 * ...83p.READER 권한 필요.
		 *    "/" 요청 경로에는 READER 롤이 있는 인증된 사용자만 요청함.
		 *    "/" 요청 경로외에는 별도 인증없이 허용함.
		 */
		http.authorizeRequests()
			.antMatchers("/").access("hasRole('READER')")
			.antMatchers("/**").permitAll()
			.and()
			//...83p. 로그인 폼 경로 설정.			
			.formLogin().loginPage("/login")
						.failureUrl("/login?error=true");

	}

	
	
}
