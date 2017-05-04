package yes.joywins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import yes.joywins.domain.SampleVO;

@RestController
public class SampleController {

  @GetMapping("/hi")
  public String sayHello(){
    
    return "Hi Springboot!";
  }
  
  @GetMapping("/sample")
  public SampleVO makeSample() {

    SampleVO vo = new SampleVO();
    vo.setVal1("v1");
    vo.setVal1("v2");
    vo.setVal1("v3");

    System.out.println(vo);

    return vo;

  }
	
}
