package hello2.core.discount;

import hello2.core.annotation.MainDiscountPolicy;
import hello2.core.member.Grade;
import hello2.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDCpolicy") ==> 빈 호출 시 같은 Qualifier name 매칭되는 것을 우선으로 의존관계 선택한다.
//단점 : 주입받는 메소드에 항상 Qualifier 코드를 쳐서 매칭을 시켜야 해서 번거로움.
@Primary //같은 타입에 빈이 2개 이상 등록되어있을 경우 primary어노테이션이 붙어있으면 우선적으로 선택된다!
//@MainDiscountPolicy // <== 직접 만든 애노테이션
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }
        else{return 0;}
    }
}
