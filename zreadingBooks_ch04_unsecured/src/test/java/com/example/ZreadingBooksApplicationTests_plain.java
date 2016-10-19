package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/*
 * ...54p.책의 예제에 있는 pom.xml 의 <artifactId> spring-boot-starter-parent 의 
 *    <version>1.3.6.RELEASE 를 기준으로 해서, STS 에서 직접 생성할 때의 어노테이션과
 *    다름에 주의함.
 *    1.4.1.RELEASE 기준으로 STS 에서 생성한 경우에는 아래 2개의 어노테이션이 생성됨.
 *    @RunWith(SpringRunner.class), @SpringBootTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ZreadingBooksApplication.class)
@WebAppConfiguration
public class ZreadingBooksApplicationTests_plain {

	@Test
	public void contextLoads() {
	}

}
