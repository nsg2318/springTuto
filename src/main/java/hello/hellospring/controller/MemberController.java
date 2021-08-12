package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//스프링이 처음 시작할 때 스프링 컨테이너가 생성이되는데 @Controller가 있는 클래스는 스프링이 알아서 컨테이너에 객체 생성해서 넣어주고 관리해줌
//스프링이 컨트롤러 애너테이션을 보고 MemberController 객체 생성해서 "관리"를 한다고 보면 됨
//멤버컨트롤러는 멤버서비스에 의존적이다(멤버서비스에 의해 어쩌고하고 저쩌고한다.)
@Controller
public class MemberController {

    private final MemberService memberService;

    //Autowired는 스프링컨테이너에있는 멤버서비스를 갖다가 연결시켜준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
