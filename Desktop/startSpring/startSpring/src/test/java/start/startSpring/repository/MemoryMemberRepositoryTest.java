package start.startSpring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import start.startSpring.domain.Member;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        //저장 잘 되는지 테스트
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //잘 들어갔는지 확인. 아이디를 잘 가지고 오는지 확인.
        //이 때 optional에서 값을 꺼내므로 .get()으로 값을 꺼낼 수 있음.
       Member result = repository.findById(member.getId()).get();

       //내가 저장한 것과 db에서 꺼낸 것이 동일하다면 참일 것.
        //확인하는 방법들:
        Assertions.assertEquals(member, result);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);
    }


    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(2);


    }
}
