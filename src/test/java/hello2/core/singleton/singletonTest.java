package hello2.core.singleton;

import hello2.core.AppConfig;
import hello2.core.member.MemberService.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class singletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 호출할 때 마다 객체 생성하는지 조회
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        //memberservice1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
        // ==> 호출(요청) 시 마다 각기 다른 객체 생성!
        // ==> 고객 트래픽이 증가함에 따라 같이 생성되고 소멸. 매우 비효율적. 메모리 낭비 매우 심함
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);

    }

    @Test
    @DisplayName("스프핑 컨테이너와 싱글톤")
    void springcontainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig appConfig = new AppConfig();
        //1. 호출할 때 마다 객체 생성하는지 조회
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        //2. 참조값이 다른지 같은지 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        //memberservice1 != memberService2
        //3. 테스트 검증
        assertThat(memberService1).isSameAs(memberService2);
        // ==> 호출(요청) 시 AppConfig에서 생성된 객체 호출!

    }




}
