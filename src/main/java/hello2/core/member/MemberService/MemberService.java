package hello2.core.member.MemberService;

import hello2.core.member.Member;

public interface MemberService {
    void join(Member memeber);
    Member findMember(Long memberId);
}
