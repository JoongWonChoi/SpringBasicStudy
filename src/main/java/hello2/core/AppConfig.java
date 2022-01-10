package hello2.core;

import hello2.core.discount.DiscountPolicy;
import hello2.core.discount.FixDiscountPolicy;
import hello2.core.discount.RateDiscountPolicy;
import hello2.core.member.MemberRepsotiry.MemberRepository;
import hello2.core.member.MemberRepsotiry.MemoryMemberRepository;
import hello2.core.member.MemberService.MemberService;
import hello2.core.member.MemberService.MemberServiceImpl;
import hello2.core.order.OrderService;
import hello2.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// <=== 관심사의 분리 ===>
//Config는 무대 기획자처럼 애플리케이션에 필요한 객체를 생성.
//객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입해줌! ==> 생성자 주입.
//객체의 생성과 연결은 이제 여기서 해결!
//공연 책임자는 공연 내부의 객체들은 모두 알아야 함!

@Configuration
public class AppConfig {
    //역할과 구현 클래스가 한 눈에 들어와야 함.
    // 중복을 최소화 해야함.
    //애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악 가능!
    @Bean
    public MemberService memberService() {return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() { //OrderService는 회원 정보와 할인 정보 두가지 정보 필요
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    //Appconfig의 등장으로 어플이 크게 사용 영역 - 구성 영역으로 분리.

}
