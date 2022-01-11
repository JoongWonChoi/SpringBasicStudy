package hello2.core;


import hello2.core.member.MemberRepsotiry.MemberRepository;
import hello2.core.member.MemberRepsotiry.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//ComponentScan ==> Component애노테이션이 붙은 클래스를 auto scan해서 스프링 빈으로 등록한다.
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //Configuration 애노테이션도 Component에 포함됨.
)
public class AutoAppConfig {
    /*@Bean
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/



}
