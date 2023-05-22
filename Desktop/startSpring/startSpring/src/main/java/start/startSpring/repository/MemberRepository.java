package start.startSpring.repository;

import start.startSpring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //회원 저장 -> 저장된 회원이 반환됨
    Member save(Member member);
    //id로 회원 찾기 만들기:: optional: findby로 가져오는데 null일 경우,
    //null을 그대로 반환하지 않고 optional로 감싸서 반환함
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //지금까지 저장된 회원의 모든 리스트 반환
    List<Member> findAll();

}
