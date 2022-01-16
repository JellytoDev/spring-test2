package springstart2.springtest2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springstart2.springtest2.domain.Member;
import springstart2.springtest2.service.MemberService;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService; // autowired

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member.getName() = " + member.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }




/*

    @Autowired // spring container에서 member service로 가져옴, dependency injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
*/


}
