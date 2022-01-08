package hello2.core.discount;

import hello2.core.member.Grade;
import hello2.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class RateDiscountPolicyTest {
    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10퍼 할인 적용되어야함") //Test문구에 나옴
    void vip_o() {
        //given
        Member member = new Member("memberV", 1L, Grade.VIP);
        //when
        int discountPrice = rateDiscountPolicy.discount(member, 10000);
        //then
        assertThat(discountPrice).isEqualTo(1000);

    }
    @Test
    @DisplayName("VIP아니면 10퍼 할인 적용ㄴㄴㄴ") //Test문구에 나옴
    void vip_X(){
        //given
        Member member = new Member("memberV", 1L, Grade.BASIC);
        //when
        int discountPrice = rateDiscountPolicy.discount(member, 10000);
        //then
        assertThat(discountPrice).isEqualTo(0);
    }

}