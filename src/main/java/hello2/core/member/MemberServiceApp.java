package hello2.core.member;

import hello2.core.AppConfig;
import hello2.core.member.MemberService.MemberService;
import hello2.core.member.MemberService.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceApp {
    //MemberService에 대한 java test code

    public static void main(String[] args) {
        /*AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();*/

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member("jwc", 1L, Grade.VIP);
        memberService.join(member);

        Member test = memberService.findMember(1L);

        System.out.println("joined name = "+test.getName()+" is equal to "+member.getName());


    }
}
