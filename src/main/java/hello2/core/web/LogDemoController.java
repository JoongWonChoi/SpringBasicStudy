package hello2.core.web;


import hello2.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //생성자에 @Autowired를 다는 것과 똑같은 효과. 주입받을 타입만 선언해주면 됨.
public class LogDemoController {

    private final LogDemoService logDemoService;
    //private final MyLogger myLogger; <== 이렇게 MyLogger에 대한 빈을 주입받으려하면 오류가 발생.
    //그 이유는, MyLogger 클래스의 스코프는 웹 스코프의 request인데, 이는 client의 요청이 들어와야지만 빈이 생성되고 기능을 작동함.
    //따라서 아무 request가 없을 때에는 MyLogger가 호출되어 빈으로 등록되지 않기 때문에 의존관계를 주입받을 수 없다. 빈(객체) 자체가 없어서!

    //따라서 다음과 같이 Provider를 사용하여, 원하는 시점에 의존관계를 주입받을 수 있도록 설정!
    //getObject 메서드로 원하는 시점에 객체를 받을 수 있다.
    //이를 의존성 검색, 즉 Dependency Lookup(DL)이라고 함.
    //의존성 주입(DI)는 설정해놓은 코드에 맞추어 스프링 컨테이너에서 등록된 의존관계(빈)를 외부에서 주입 받는 것.
    //반면 의존성 검색은 스프링 빈에 접근하기 위해 특정 API를 이용하여 직접 검색하는 방식!
    //private final ObjectProvider<MyLogger> myLoggerProvider;

    //====proxy 방식 사용====
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody//template으로 자동 렌더링 되지 않고 직접 해당 메서드에서 body를 반환하여 출력하는 방식
    public String logDemo(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());//proxyMode 의 TARGER_CLASS가 가짜 클래스를 생성해준다고 해서 테스트를 위해 주입받은 MyLogger의 클래스를 검색해보기
        //결과 ==> 내가 만든 클래스가 아닌 MyLogger$$EnhancerBySpringCGLIB$$~~ 라고 로그에 남겨진다.
        //우리가 만든 순수한 MyLogger 객체가 아닌 가짜 MyLogger 객체가 나타난 것.
        //MyLogger myLogger = myLoggerProvider.getObject(); //Provider의 getObject 메서드를 사용하여 의존성을 검색하는 방식.
        myLogger.setRequestUrl(requestUrl);
        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
