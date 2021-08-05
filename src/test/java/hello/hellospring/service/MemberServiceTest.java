package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    //멤버 서비스는 레포지토리랑 다르게 클리어하기가 힘듦... 여까진 ㅇㅋ 근데 갑자기 리포지토리를 왜가져와
    // 밑에 메멤리포지토리는 멤버서비스 내 리포지토리와 다른 인스턴스임.. 그래서 아래처럼 하면 aftereach가 좀 에바임 (물론 메멤리포지토리의 sotre는 static이긴 하지만 뭐 스태틱아니라고
    // 했을 땐 완전 문제겠지?... ㅇㅇ
    // 그래서 memberService 클래스도 수정되고(생성자 형태로) 여기 밑에도 비포어each로 수정됨
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void aterEach(){
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);
        //then
        //옵셔널로 반환한거에 .get()을 하면 옵셔널 까지낭?
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        //회원 서비스 테스트 10분 쯤 내용
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다?!?!");
        }*/


        //then

    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}