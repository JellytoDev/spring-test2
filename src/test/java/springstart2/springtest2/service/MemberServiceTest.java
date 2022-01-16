package springstart2.springtest2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springstart2.springtest2.domain.Member;
import springstart2.springtest2.repository.MemoryMemberRepository;
import springstart2.springtest2.repository.MemoryMemberRepositoryTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    // given when then 적인 테스트 방식이 좋음
    /*
        given : 무언가 주어졌는데
        when : 이것을 실행했을떄
        then : 결과가 이렇게 나와야 한다.
     */

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach // 같은 memberRepository 공유
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 이렇게 하면 중복되서 안좋다
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

        //test는 정상 flow도 중요하지만 예외 flow도 엄청 중요하다.
    }

    // 중복 회원 체크 테스트
    @Test
    public void DuplicateMemberException() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //class 타입을 받고 예외 상황 체크

        /*
        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
        }
        */


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
    // test 같은 경우에는 함수 이름을 한글로 바꿔도 됨
}