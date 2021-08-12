package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// "/" 는 index 랑 같은 거 아닌가 ? ... 근데 정적파일보다 controller 가 우선순위가 높다고 합니다. 강의자료에
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
