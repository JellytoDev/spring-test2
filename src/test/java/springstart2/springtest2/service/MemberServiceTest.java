package springstart2.springtest2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import springstart2.springtest2.domain.Member;

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

    MemberService memberService = new MemberService();

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

    @Test
    public void DuplicateMemberException() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
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