package hello2.core.order;

import hello2.core.discount.DiscountPolicy;
import hello2.core.discount.FixDiscountPolicy;
import hello2.core.discount.RateDiscountPolicy;
import hello2.core.member.Member;
import hello2.core.member.MemberRepsotiry.MemberRepository;
import hello2.core.member.MemberRepsotiry.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    /*private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //  ===> DIP위반!! 역할(인터페이스, 추상화)에도 의존하고 있지만 구체화된 객체에도 의존하고 있음.
    //  ===> OCP위반!! 한 역할의 다른 객체(구현)에 접근하려면 위처럼 선택할 객체에 접근하기 위해 코드를 추가해야함.
    // 그렇다면 어떻게 해야할까?? ===> 인터페이스에만 의존할 수 있도록 설계를 해야함!!*/


    //인터페이스에만 의존 ==> DIP 준수!
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //final 접근자가 있으면 직접적 or 생성자로 반드시 할당이 되어야 함.

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //설계 변경으로 구현체들(객체)를 의존하지 않음. 오로지 인터페이스(MemberRepom Discountpolicy) 만 의존!
    //생성자를 통해 어떤 객체, 구현체가 주입될지는 오로지 외부에서 결정됨.
    //앞으으로 의존 관계에 대한 고민은 전부 외부에 맡기고, 연기, 실행에만 집중하면 됨.
    //이는 마치 외부에서 의존 관계를 주입하는 것과 같이 보인다고 해서, '의존관계 주입(Dependency Injection)'이라고 함.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
