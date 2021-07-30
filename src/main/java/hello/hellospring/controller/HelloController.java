package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //웹 어플리케이션에서 /hello 라고 들어오면 해당 메서드를 호출해준다.
                        //스프링이 Model 만들어서 넣어줌. 이 모델은 MVC 할 때 그 모델
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
        // hello 라는 templates 폴더 밑의 html을 찾아서 랜더링해라 라는 거 ..
    }

    @GetMapping("hello-mvc")              //여기 벨류는 웹에서 ?벨류 = 이거   쓸 때의 이름
    public String helloMvc(@RequestParam(value = "alpha", required = false) String name, Model model){
                          //여기 "name"은 단순 html과 맵핑 하는거
        model.addAttribute("beta",name);
        return "hello-template";
    }


    //API1 -그냥 맛보기
    @GetMapping("hello-string")
    //http에서 헤더와 바디 중 바디라는 뜻으로 그 바디에 아래 데이터를 직접 넣어주겠다는 의미
    //위 템플릿엔진(MVC) 방식과 다른 이유는. html자체가 없고 그냥 딱 그것만 있음 소스보기해보세요
    @ResponseBody
    public String helloString(@RequestParam("alpha2") String name){
        return "hello " + name;
    }

    //API2 - 진짜
    @GetMapping("hello-api")
    @ResponseBody
    public Halo helloApi(@RequestParam("alpha3") String name){
        // 위 Halo 타입임을 다시 한 번 보세요. 그니까 위는 String인데 여긴 객체임
        // @ResponseBody가 오면 뷰리졸버한테 안주고 http에 그대로 데이터를 넘겨야겠군 함.
        // 그 와중에 또 객체가 오면 그냥 JSON으로 만들어서 반환하겠다가 기본정책임
        Halo halo = new Halo();
        halo.setName(name);
        return halo;
    }

    static class Halo{
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


}
