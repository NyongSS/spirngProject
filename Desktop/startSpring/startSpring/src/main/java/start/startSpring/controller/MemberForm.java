package start.startSpring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import start.startSpring.domain.Member;
import start.startSpring.service.MemberService;

public class MemberForm {
    private String name;
    public String getName(){
        return name;
    }
     public void setName(String name){
        this.name = name;
    }


}
