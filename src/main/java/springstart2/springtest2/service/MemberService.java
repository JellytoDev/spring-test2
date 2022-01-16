package springstart2.springtest2.service;

import org.springframework.stereotype.Service;
import springstart2.springtest2.domain.Member;
import springstart2.springtest2.repository.MemberRepository;
import springstart2.springtest2.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// 이거 넣어줘야 순수한 자바 클래스가 아닌 container로부터 가져올수 있음.
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
        회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
        // Optional을 바로 반환하는게 좋지는 않다. 바로 ifpresent 사용

        validateDuplicateMember(member);
        // 값이 있는지를 검사하는 것.

        //result.get()  꺼내고 싶으면 이거 쓰면 됨
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
        전체 회원 조회
     */

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
