package hello2.core.order;

import hello2.core.discount.FixDiscountPolicy;
import hello2.core.member.Grade;
import hello2.core.member.Member;
import hello2.core.member.MemberRepsotiry.MemberRepository;
import hello2.core.member.MemberRepsotiry.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    @Test
    void createOrder() {
        //생성자 의존관계 주입을 하면 장점
        //1. final키워드 선언 가능 ==> 생성자에서만 값을 지정해주고, 나머지는 값을 수정할 수 없음 == 불변의 장점! 실수로 수정 혹은 누락의 경우 x
        //2. test진행 시 주입해야하는 의존관계를 파악하기 힘들 수 있는데, 이를 빨간 줄을 그어서 어떤 인자가 필요한지 지시해줌.
        //3. final키워드를 선택하면, 혹시라도 생성자에서 값이 설정되지 않는 오류를 막아줌!
        // 결론은 그냥 생성자 주입이 가장 낫고, 생성자에 final 키워드를 사용하자..!
        MemberRepository memberRepository= new MemoryMemberRepository();
        memberRepository.save(new Member("jwc", 1L, Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "jwc", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);


    }

}