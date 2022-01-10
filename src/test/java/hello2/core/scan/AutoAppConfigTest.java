package hello2.core.scan;

import hello2.core.AutoAppConfig;
import hello2.core.member.MemberService.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    //AutoAppConfig(Component scan을 사용하는 configuration) 검증 테스트
    //이전의 Appconfig는 수작업으로 각 메소드에 @Bean을 붙여 스프링 빈에 등록하였지만, Autoconfig에는 아무런 코드가 없이 @Component 애노테이션만 설정하였다.
    //과연 자동으로 컴포넌트들을 스캔하여 정상적으로 자바빈에 등록하였는지에 대한 test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);//설정 정보로 AutoAppConfig.class

        MemberService memberService = ac.getBean(MemberService.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
