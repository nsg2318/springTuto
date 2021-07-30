package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		//Spring은 톰캣이라는 웹서버를 내장하고있는데 웹서버를 띄우면서 같이 올라온다? 흠  1강 15분 쯤
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
