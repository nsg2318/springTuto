package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//스프링이 컨트롤러 애너테이션을 보고 MemberController 객체 생성해서 "관리"를 한다고 보면 됨
@Controller
public class MemberController {

    private final MemberService memberService;

    //Autowired는 스프링컨테이너에있는 멤버서비스를 갖다가 연결시켜준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
