package hello2.core.order;

import hello2.core.AppConfig;
import hello2.core.member.Grade;
import hello2.core.member.Member;
import hello2.core.member.MemberService.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class OrderServiceApp {
    public static void main(String[] args) {
        //OrderService에 대한 java test code

        /*AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();*/

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member("MemberA", memberId, Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(),"itemA",  20000);
        System.out.println("order = "+order);
        System.out.println("order.calculatePrice = "+order.calculatePrice());

    }
}
