package yes.joywins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
 * ...011p.@ComponentScan :
 *         기본 패키지(yes.joywins) 가 아닌 패키지에 작성된 코드를
 *         사용할 때 이용함.
		   @ComponentScan(basePackages={"org.sample", "org.sample2"}) 
 */

public class Zboot01Application {

	public static void main(String[] args) {
		SpringApplication.run(Zboot01Application.class, args);
	}
}
