package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//MemberRepository의 구현체 클래스임
public class MemoryMemberRepository implements MemberRepository {

    //key는 Long이고, value는 Member 타입이다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    // member 타입 파라미터를 받아 member 타입으로 리턴하는 메서드
    @Override
    public Member save(Member member) {
        //멤버 set에 시퀀셜 번호 할당
        member.setId(++sequence);
        //스토어 맵에다가 아이디와 멤버 put
        store.put(member.getId(), member);
        //이건 member 리턴하는 의미가 뭘지? save는 그냥 void 아닌가 ?
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //과거엔 그냥 store에서 id를 꺼내왔음. 그러나 id가 없는경우도 있을거아님 ? 그래서 밑에처럼 옵셔널로 씀
        //return store.get(id);
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //람다식은 좀...백엔드 개발 2강 6:20 부근
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }


    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    //테스트 때 Each당 클리어 시키려고 만든 메서드임 ㅇㅇ; 왤케많냐 내용이
    public void clearStore(){
        store.clear();
    }
}


