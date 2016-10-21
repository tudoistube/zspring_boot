package com.example;
/*
 * ...224p. 사용자 정의 메트릭 구현.
 *    엑츄에이터는 metircs() 메서드를 호출해서 ApplicationContextMetrics 가 제공하는 
 *    사용자 정의 메트릭을 수집함.
 *    metrics() 메서드는 주입받은 ApplicationContext 의 메서드를 호출하여 메트릭에
 *    보고할 숫자를 조회함.
 *    각 메서드는 메트릭 이름과 값을 지정하는 Metric 인스턴스를 생성하여 반환될 리스트에
 *    Metric 을 추가함.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class ApplicationContextMetrics implements PublicMetrics {

    private ApplicationContext context;

    @Autowired
    public ApplicationContextMetrics(ApplicationContext context) {
        this.context = context;
    }
  
    @Override
    public Collection<Metric<?>> metrics() {
    	
        List<Metric<?>> metrics = new ArrayList<Metric<?>>();
        //...시작 시간 기록.
        metrics.add(new Metric<Long>("zspring.context.startup-date",
            context.getStartupDate()));

        //...빈 정의 개수 기록.
        metrics.add(new Metric<Integer>("zspring.beans.definitions",
            context.getBeanDefinitionCount()));

        //...빈 개수 기록.
        metrics.add(new Metric<Integer>("zspring.beans",
            context.getBeanNamesForType(Object.class).length));

        //...컨트롤러 빈 개수 기록.
        metrics.add(new Metric<Integer>("zspring.controllers",
            context.getBeanNamesForAnnotation(Controller.class).length));

        return metrics;
    }

}
