package hello2.core.order;

import hello2.core.AppConfig;
import hello2.core.member.Grade;
import hello2.core.member.Member;
import hello2.core.member.MemberRepsotiry.MemoryMemberRepository;
import hello2.core.member.MemberService.MemberService;
import hello2.core.member.MemberService.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;
    @BeforeEach // 각 Test 돌기 전 실행
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void CreateOrder() {
        Long memberId = 1L;
        Member member = new Member("memberA", memberId, Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "A", 20000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(2000);
    }

}
