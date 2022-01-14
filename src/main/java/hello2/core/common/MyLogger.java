package hello2.core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

//Web Scope 중' request' 스코프를 알아보기 위한 테스트
//웹 스코프는 웹 환경에서만 동작
//프로토타입 스코프와는 달리 스프링 컨테이너가 종료 시점까지 관리한다. ==> 종료 메서드 호출 가능
//웹 스코프의 종류 중 request, session, websocket 등이 있는데, 모두 웹에서의 개념과 동일하여 request에 대한 테스트를 대표로 진행해볼 것이다.
//web에서는 동시에 많은 request들이 요청되어 들어오면, 싱글톤처럼 하나의 객체로 상대하기엔 너무 버거울 수 있다는 생각이 들었다.
//request 는 client의 web request 요청이 들어온 후 생성되고, 각 클라이언트마다 다른 객체를 생성해줌.
//싱글톤 빈 처럼 하나의 객체가 생성되고 공유되지 않고, 클라이언트들의 request들에 각각 대응하여 새로운 객체를 생성하고 주입해줌!
//중요한 것은 'request가 들어올 때 객체가 생성되고 주입' 되는 것!

@Component
@Scope(value="request") //스코프를 request로 설정 ==> request가 들어와야만 빈으로 등록되어 스프링 컨테이너로 등록됨.
public class MyLogger {

    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void log(String message) {
        System.out.println("["+uuid+"]"+"["+requestUrl+"] . . "+message);
    }

    @PostConstruct //request 스코프에 의하여 객체가 생성되고 초기화될 때
    public void init() {
        uuid = UUID.randomUUID().toString(); //UUID : 랜덤으로 부여되는 고유 Id.
        System.out.println("["+uuid+"] request scope bean creat : " + this);
    }

    @PreDestroy //request 스코프에 의하여 객체가 사라질 때(스프링 컨테이너가 종료될 떄)
    public void close() {
        System.out.println("["+uuid+"] request scope bean close : " + this);
    }





}
