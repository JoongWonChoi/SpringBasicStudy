package hello2.core.Autowired;

import hello2.core.discount.DiscountPolicy;
import hello2.core.member.Member;
import hello2.core.member.MemberRepsotiry.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }
    //Spring Bean이 아닌 Member를 의존관계로 주입하려고 할 때의 Autowired의 옵션 테스트.
    static class TestBean {
        //1. @Autowired의 옵션 : required
        @Autowired(required = false) // => 의존관계가 없으면 해당 수정자 메서드 자체가 수정이 안됨!
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // 2. @Nullable 사용
        @Autowired // => 호출은 되는데, 대신 null로 들어감.
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // 3. Optional 사용
        @Autowired // java8의 문법인 Optional, 호출했는데 값이 없으면 Optional의 기능에 의해 표현
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }


        @Autowired(required = false) //
        public void setBean1(DiscountPolicy discountPolicy) {
            System.out.println("Bean1 = " + discountPolicy);
        }
        @Autowired // => 컨테이너에 등록된 MemberRepository로 test
        public void setBean2(@Nullable MemberRepository memberRepository) {
            System.out.println("Bean2 = " + memberRepository);
        }
        @Autowired // => 컨테이너에 등록된 MemberRepository로 test
        public void setBean3(Optional<MemberRepository> memberRepository) {
            System.out.println("Bean3 = " + memberRepository);
        }

    }
}
