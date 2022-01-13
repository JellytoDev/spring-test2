package springstart2.springtest2.service;

import springstart2.springtest2.domain.Member;
import springstart2.springtest2.repository.MemberRepository;
import springstart2.springtest2.repository.MemoryMemberRepository;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
/*
    회원가입
 */
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }
}
