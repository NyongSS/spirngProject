package start.startSpring.repository;

import org.springframework.stereotype.Repository;
import start.startSpring.domain.Member;

import java.util.*;

@Repository
//멤버 리포지토리를 implement함
public class MemoryMemberRepository implements MemberRepository {
    //데이터를 저장해 두는 곳: 키, 값 쌍
    private static Map<Long, Member>store = new HashMap<>();
    //시퀀스: 0, 1, 2키 값을 생성해 줌
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        //id는 시스템에서 하나씩 늘려서 자동으로 저장해 주도록 할 것임.
        member.setId(++sequence);           //store에 넣기 전 member에 id값 setting 해 줌
        store.put(member.getId(), member);  //저장: map에 저장될 것.
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        //Optional.ofNullable: null이어도 반환할 수 있게끔 해 주는 아이
        //null이어도 반환을 해 주니까 클라이언트에서 뭘 해줄 수 있음.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //람다를 사용해서 name이 파라미터로 넘어온 name과 같은지 확인
        //그리고 그 중에서 찾으면, 반환을 한다.
        //루프를 돌면서 하나 찾아지면 반환함.
        //없으면 optional에 null이 포함되어 반환됨.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    //루프도 돌리기 쉽고 헤서 자바에서는 리스트 많이 사용함
    //store에 있는 values: 멤버들. => 이러면 멤버들이 반환됨.
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
