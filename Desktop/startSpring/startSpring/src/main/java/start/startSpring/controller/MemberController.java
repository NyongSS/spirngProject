package start.startSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import start.startSpring.domain.Member;
import start.startSpring.service.MemberService;

import java.util.List;

@Controller
//controller가 되어 있으면 멤버 컨트롤러 객체를 생성해서 스프링 컨트롤러에 넣어둠
public class MemberController {

    private final MemberService memberService;

    @Autowired
    //autowired가 되어있으면 인수로 받은 memberservice를 스프링 컨테이너에서 가져옴.
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping(value="/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping(value = "/member/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/members/memberList";
    }


}





