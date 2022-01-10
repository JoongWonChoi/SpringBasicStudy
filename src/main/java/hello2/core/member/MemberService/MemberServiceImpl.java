package hello2.core.member.MemberService;

import hello2.core.member.Member;
import hello2.core.member.MemberRepsotiry.MemberRepository;
import hello2.core.member.MemberRepsotiry.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository; //===> 추상화에만 의존!

    //생성자를 통해 외부로부터 객체 주입 ==> '생성자 주입'
    @Autowired //ac.getBean(MemberRepository.class)와 거의 동일한 기능을 한다고 보면 됨.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    //아직메모리연동이 확실치 않으니 Interface(역할)에 접근하되, 구현체인 MemoryMemberRepository에 접근.

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        Member result = memberRepository.findById(memberId);
        return result;
    }

    //For test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }


}
