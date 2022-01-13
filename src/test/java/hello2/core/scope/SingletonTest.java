package hello2.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        //singleton Bean 의 Scope(범위) 확인해보기
        AnnotationConfigApplicationContext ac  = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);
        ac.close();

    }

    //singleton scope ==> 생성 시점에만 의존관계를 주입 받고, 하나의 객체로 지속되고 공유됨.
    //prototype scope ==> 호출 시 생성되고, 의존관계 주입 후에는 스프링 컨테이너에서 제거되는 생존 범주(=scope?)
    @Scope("singleton")//default = singleton
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");

        }
        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }

    }
}
