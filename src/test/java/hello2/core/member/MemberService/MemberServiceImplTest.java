package hello2.core.member.MemberService;

import hello2.core.AppConfig;
import hello2.core.member.Grade;
import hello2.core.member.Member;
import hello2.core.member.MemberRepsotiry.MemberRepository;
import hello2.core.member.MemberRepsotiry.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hello2.core.member.Grade.VIP;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceImplTest {
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    /*MemberService memberService = new MemberServiceImpl();
    MemberRepository memberRepository = new MemoryMemberRepository();*/

    @Test
    void join() {
        //given
        String name = "jwc";
        Long id = 1L;
        Grade grade = VIP;
        //when
        Member member = new Member(name, id, grade);
        memberService.join(member);
        //then
        Member result = memberService.findMember(id);
        org.assertj.core.api.Assertions.assertThat(result.getName()).isEqualTo(name);



    }

    @Test
    void findMember() {
    }
}