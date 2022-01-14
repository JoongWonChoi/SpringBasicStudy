package hello2.core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
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
//@Scope(value="request")
// 스코프를 request로 설정 ==> request가 들어와야만 빈으로 등록되어 스프링 컨테이너로 등록됨.
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS) //proxyMode를 추가해보기
//프록시(proxy)란? ==> 사전적 의미로 대리인, 대리자 등의 의미로, 대신 행해주는 무언가를 지칭하는 용어라고 한다.
//즉 직접적 대상이 아닌 대리의, 가짜의 무언가를 생성하여 동작을 해주는 의미로 해석하면 될 것 같다.
//proxyMode를 적용한다는 것은 대리의, 가짜의 무언가를 생성하여 기능을 수행하게끔 한다는 의미인 것 같다.
//적용 대상이 인터페이스가 아닌 클래스면 TARGET_CLASS, 인터페이스면 TARGET_INTERFACES
//이는 MyLogger 클래스의 가짜 프록시(대리) 객체를 생성하여 스프링 컨테이너에 등록된다. 의존관계 또한 이 가짜 프록시 객체가 등록됨.
//가짜 프록시 객체는 원본 클래스를 상속받아 생성된 것이기 때문에, 진짜 클래스의 기능은 모두 구현된다. ==> 다형성의 힘!
//실제 request가 들어와서 사용할 때가 되면 그제서야 진짜 객체르 생성하여 호출한다.

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
