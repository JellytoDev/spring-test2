package springstart2.springtest2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springstart2.springtest2.repository.MemberRepository;
import springstart2.springtest2.repository.MemoryMemberRepository;
import springstart2.springtest2.service.MemberService;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
