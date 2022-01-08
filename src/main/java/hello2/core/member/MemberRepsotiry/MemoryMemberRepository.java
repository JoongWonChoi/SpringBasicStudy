package hello2.core.member.MemberRepsotiry;

import hello2.core.member.Member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static Long index = 0L;


    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
    /*public void clearRepo() {
        store.clear();
    }*/

}
