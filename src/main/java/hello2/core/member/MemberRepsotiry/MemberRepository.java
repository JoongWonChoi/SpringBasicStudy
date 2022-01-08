package hello2.core.member.MemberRepsotiry;

import hello2.core.member.Member;

public interface MemberRepository {

    //접근제어자?
    //반환형?

    void save(Member member); //회원의 정보 저장
    Member findById(Long memberId); //회원의 정보 return


}
