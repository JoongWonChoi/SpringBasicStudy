package hello2.core.discount;

import hello2.core.member.Grade;
import hello2.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    //MemberRepository의 객체를 직접 생성하지 않고, 주문 서비스에서 넘어온 회원정보 객체를 넘겨받는 용도. 역할군 간의 의존도를 떨어뜨리기 위함?

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade()== Grade.VIP){
            return discountFixAmount;}
        else{return 0;}
    }
}
