package hello2.core.beanfind;

import hello2.core.discount.DiscountPolicy;
import hello2.core.discount.FixDiscountPolicy;
import hello2.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Dictionary;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
    }
    @Test
    @DisplayName("부모타입으로 조회시, 자식이 둘 이상 있으면 오류 발생")
    void findByParentDuplicate() {
    /*    DiscountPolicy discountPolicy = ac.getBean(DiscountPolicy.class);
        Assertions.assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);*/
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean( DiscountPolicy.class));
    }
    @Test
    @DisplayName("부모타입으로 조회시, 자식의 이름으로 검색")
    void findByParentByName() {
        DiscountPolicy discountPolicy = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);

    }
    @Test
    @DisplayName("특정타입으로 자식 타입 조회")
    void findByParentByType() {
        RateDiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", RateDiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);

    }
    @Test
    @DisplayName("모든 자식 타입 조회")
    void findAll() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        //assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " // value" + beansOfType.get(key));
        }

    }
    @Test
    @DisplayName("모든 객체 타입 bean 조회")
    void findAllByObjec() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        //assertThat(beansOfType.size()).isEqualTo(2);
        System.out.println("size : "+beansOfType.size());
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " // value" + beansOfType.get(key));
        }

    }
}
