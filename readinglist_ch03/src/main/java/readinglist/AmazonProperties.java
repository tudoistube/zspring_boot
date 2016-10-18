package readinglist;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * ...106p.아마존과 항상 관련이 있을 이유가 없는 ReadingListController 에
 *    @ConfigurationProperties("amazon") 를 붙이지 않고, 아마존에 특화된 구성 프로퍼티만 모은
 *    별도의 빈을 생성함. 
 *    'amazon' 접두어가 붙은 프로퍼티를 주입함.
 *    application.yml 파일의 속성값을 읽음.
 */
@Component
@ConfigurationProperties("amazon")
public class AmazonProperties {

    private String associateId;

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    public String getAssociateId() {
        return associateId;
    }

}
