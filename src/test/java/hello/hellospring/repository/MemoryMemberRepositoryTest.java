package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


// 테스트 케이스를 먼저 만들어놓고 그 뒤에 구현 클래스를 만드는게 TDD (테스트 주도 개발)
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트케이스 할 때는 테스트에는 순서에 의존되지 않게 짜야함.ㅇㅇ 그래서 clear를 넣어줘야함
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        //메인에서 하는거랑 뭔가 비슷하게 하는겨..
        //멤버 객체 하나 생성하고 거기에 이름 spring으로 셋하고
        //save를 한다.
        //그 뒤 레포지터리에서 id로 member.getId() 값을 찾고 리턴이 Optional이므로 .get()으로 값을 꺼낸다...
        //그리고 그 두개를 비교한다.
        Member member = new Member();
        member.setName("spring");
        repository.save(member);


        Member result = repository.findById(member.getId()).get();
        //검증방법1
        Assertions.assertThat(member).isEqualTo(result);
        //검증방법2
//        Assertions.assertEquals(member, result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("summer");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("winter");
        repository.save(member2);

        Member result = repository.findByName("summer").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member3 = new Member();
        member3.setName("fall");
        repository.save(member3);

        Member member4 = new Member();
        member4.setName("fall2");
        repository.save(member4);

        Member member5 = new Member();
        member5.setName("fall3");
        repository.save(member5);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(3);
    }

}
