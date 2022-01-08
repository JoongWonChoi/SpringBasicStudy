package hello2.core.beanfind;

import hello2.core.AppConfig;
import hello2.core.member.MemberService.MemberService;
import hello2.core.member.MemberService.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    /*
    ac.getBean(빈이름, 타입);
    ac.getBean(타입)
    */
    //getBean("검색할Bean이름", 해당Bean의 타입(어떤 타입을 갖는지))
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("구체타입으로 조회")
    void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class); //.class => 가능한 유형이지만 객체가 없을 때.
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @Test
    @DisplayName("이름 없ㅇ ㅣ타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    //이때의 Assertions은 junit꺼
    void findByNameX() { //assertThrow ==> 반드시 해당하는 오류/예외가 발생해야함.
        //ac.getBean("XXXX", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("XXXX", MemberService.class));
        //존재하지 않느 bean 이름을 검색할 경우, 해당 예외가 발생하는지 Test

    }
}
