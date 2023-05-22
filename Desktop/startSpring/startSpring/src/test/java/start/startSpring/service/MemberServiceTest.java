package start.startSpring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import start.startSpring.domain.Member;
import start.startSpring.repository.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
//원래는 이걸로 생성해도 되는데 멤버서비스 클래스에서 인스턴스 생성하도록 수정하면서 여기서 바로 인스턴스를 만들지 않음.
//    MemberService memberService = new MemberService();
    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); //DB클리어를 위함
    // -> 그런데 테스트를 하는 객체는 멤버 리포지토리임. 애매함.
    //new로 다른 멤버의 리포지토리에 생성이되면 테스트가 아닌
    // 멤버 서비스 클래스의 것과 다른 인스턴스이기 때문에 내용물이 달라지거나 할 수 있음.
    //따라서 같은 인스턴스를 사용하게끔 하기 위해서는, 멤버 서비스 클래스에서,
    //리포지토리 인스턴스를 만들고, 멤버 리포지토리를 직접 생성하는 것이 아니라
    //외부에서 받아오게끔 멤버서비스 생성자를 만들어서 받아오도록 수정하면 된다.
    //그리고 테스트에서는 아래와 같이 생성한다.
    @BeforeEach
    public void beforeEach(){       //각 테스트를 실행하기 전에 실행됨.
        //각 테스트 실행하기 전에, 메모리 멤버 리포지토리를 만들고 이걸 멤버 서비스에 넣어줌.
        //그러면 같은 메모리 멤버 리포지토리가 사용될 것.
        // : DI: dependency injection: 직접 멤버 서비스에
        // new로 생성하지 않고 외부에서 받아서 생성하도록 코드를 구조화 하는 것.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    //돌 때마다 DB의 내용을 clear 해 줌.
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void join() {
        //given: 주어진 것
        Member member = new Member();
        member.setName("hello");

        //when: 어떨 때 사용하는지
        Long saveId = memberService.join(member);

        //then: 검증
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //test는 정상적인 상황에 대한 검증도 중요하지만,
    //그보다 더 중요한 것은 예외적인 상황에 대한 검증이다.
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        //멤버의 이름이 동일하게 들어간 경우,
        //예외가 동작하면서 validate에서 exception이 터짐.
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        
/*      방법 2.           
        //람다식을 실행할건데, 첫 번째 인수로 있는 예외가 터져야 하는 것. 
        assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
*/
/*      방법 1.   
        try {
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}