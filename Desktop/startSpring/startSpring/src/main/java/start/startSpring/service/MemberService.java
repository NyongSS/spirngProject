package start.startSpring.service;

import org.springframework.stereotype.Service;
import start.startSpring.domain.Member;
import start.startSpring.repository.MemberRepository;
import start.startSpring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    //    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public long join(Member member){

        /*
        //같은 이름이 있는 중복 회원 x
        //왼쪽 항만 작성하고 ctrl alt v 하면 자동으로 optional 변수 만들어줌
        Optional<Member> result = memberRepository.findByName(member.getName());
        //그냥 꺼내고 싶으면 get으로 꺼내면 됨.
        Member member1 = result.get();
        //만약 member의 값이 있다면, 에러 반환하기.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");

        });
        */

        validateDuplicateMember(member);        //중복회원 검증. 위의 코드가 축약되고, 축약된 것을 메서드로 뺌.

        memberRepository.save(member);          //저장
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
