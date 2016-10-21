package com.example;
/*
 * ...229p. 독서 목록 애플리케이션의 독서 목록에 등록된 책은 아마존으로 이동하는
 *    링크를 포함하므로 아마존에 접속이 가능한지 알아보기 위해,
 *    아마존 접속 가능 여부와 상관없이 아마존의 Health Indicator 를 만듦.
 *    
 */
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AmazonHealth implements HealthIndicator {

    @Override
    public Health health() {
        try {
            RestTemplate rest = new RestTemplate(); //...아마존에 요청 전송함.
            rest.getForObject("http://www.amazon.com", String.class);
            return Health.up().build();
        } catch (Exception e) { //...다운 상태 보고.
        	//return Health.down().build(); //...by230p.
            return Health.down().withDetail("zreason", e.getMessage()).build();
        }
    }

}
