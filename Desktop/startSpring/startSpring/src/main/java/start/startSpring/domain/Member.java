package start.startSpring.domain;

public class Member {
    //비지니스 요구사항에 따른 회원 id, 이름 데이터 필요
    private Long id;
    private String name;
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
