package hello2.core.order;

import hello2.core.discount.DiscountPolicy;
import hello2.core.discount.FixDiscountPolicy;
import hello2.core.discount.RateDiscountPolicy;
import hello2.core.member.Member;
import hello2.core.member.MemberRepsotiry.MemberRepository;
import hello2.core.member.MemberRepsotiry.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // Lombok lib ~ 자동으로 생성자 생성!! ==> 생성자를 안만들어도 됨. .
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
    //final 키워드가 있으면 어떠한 방식으로든 무조건 값이 들어와야함. 직접적이든 생성자 등의 외부 경로로든.
    //final 키워드가 있으면 직접적 or 생성자로 반드시 할당이 되어야 함.
    //final 키워드는 한번 설정 되면 수정이 불가능하기 때문에, 변경 및 누럭의 위험성을 줄여줌
    //또한 생성자에서 혹시나 값 설정이 누락되거나 하면 바로 오류 처리를 해줘서 알려줌!

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy DiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = DiscountPolicy;
    }

    //설계 변경으로 구현체들(객체)를 의존하지 않음. 오로지 인터페이스(MemberRepom Discountpolicy) 만 의존!
    //생성자를 통해 어떤 객체, 구현체가 주입될지는 오로지 외부에서 결정됨.
    //앞으으로 의존 관계에 대한 고민은 전부 외부에 맡기고, 연기, 실행에만 집중하면 됨.
    //이는 마치 외부에서 의존 관계를 주입하는 것과 같이 보인다고 해서, '의존관계 주입(Dependency Injection)'이라고 함.

    //Setter로 의존성 주입하기
    // == > 선택적, 변경 가능성이 있는 의조 관계 주입 시 사용
    // =========================================================
   /* @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }*/
    //===========================================================

    //일반 메서드에 의존관계 주입
    /*@Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

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
