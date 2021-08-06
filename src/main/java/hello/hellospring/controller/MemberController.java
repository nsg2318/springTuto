package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
