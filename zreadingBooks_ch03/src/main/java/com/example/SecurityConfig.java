/*
 * ...82p.스프링 시큐리티의 자동구성을 오버라이딩함.
 *    WebSecurityConfigurerAdapter 을 확장해서 자동구성을 오버라이딩함.
 */
package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//super.configure(auth);
		//...83p.사용자 정의 UserDetailService.
		auth.userDetailsService(
					new UserDetailsService() {
						
						@Override
						public UserDetails loadUserByUsername(String username) 
								throws UsernameNotFoundException {

							return (UserDetails) readerRepository.findOne(username);
							
						}
					}
				);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//super.configure(http);
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
