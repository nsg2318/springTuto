package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 비즈니스 로직 구현 서비스는 비즈니스에 의존적으로 네이밍하고 리포지터리쪽은 약간 더 백단으로 네이밍을 함
//일단 서비스를 만드려면 리포지토리가 있어야겠죠?

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     *회원 가입
     */
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    //하... 그리고 이런 로직들은 보통 메서드로 뽑아주는게 좋음
    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복 회원 X 반환이 optional로 됐으니 우린 더 편하게(옵셔널의 메서드를 사용하여) 검증가능
        Optional<Member> result = memberRepository.findByName(member.getName());
        //과거에는 if null 어쩌고저쩌고로 코딩했지만 지금은 null이 있을경우엔 옵셔널로 감싸기때문에 옵셔널 메서드로 편하게 구현가능
        //값이 있으면(ifpresent)
        result.ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });


    }

//        사실 Optional<Member> result ... 이렇게 하는거 자체가 지저분? 하다 라고 함
//        그냥 아래와 같이 쓰면 어차피 findByName의 리턴이 옵션<Member>니까 바로 아래처럼 구현 가능하다고함 ... 근데 난 초보니까 위에로 쓰자
//        memberRepository.findByName(member.getName())
//                .ifPresent(member1 -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                })

    /**
     전체 회원 조회 서비스
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

//    public Optional<Member> findOne(Member member){
//        return memberRepository.findById(member.getId());
//    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
