package hello2.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatelessServiceTest {

    @Test
    void statelessTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService statelessService1 = ac.getBean("statelessService", StatelessService.class);
        StatelessService statelessService2 = ac.getBean("statelessService", StatelessService.class);

        //ThreadA : A사용자의 주문금액 10000원
        int userA = statelessService1.price("userA", 10000);
        //ThreadB : B사용자의 주문금액 20000원
        int userB = statelessService2.price("userB", 20000);

        System.out.println("userA price = " + userA);

        Assertions.assertThat(userA).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }



}