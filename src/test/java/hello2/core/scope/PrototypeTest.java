package hello2.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        //singleton Bean 의 Scope(범위) 확인해보기
        AnnotationConfigApplicationContext ac  = new AnnotationConfigApplicationContext(PrototypeBean.class); // AnnotationConfigApplicationContext에 클래스로 등록하면 해당 클래스가 컴포넌트 스캔의 대상으로 자동 등록됨.

        System.out.println("find prototypebean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find prototypebean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        //Assertions.assertThat(prototypeBean1).isEqualTo(prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        /*prototypeBean1.close();
        prototypeBean2.close();*/
        ac.close();

    //singleton scope ==> 생성 시점에만 의존관계를 주입 받고, 하나의 객체로 지속되고 공유됨.
    //prototype scope ==> 호출 시 생성되고, 의존관계 주입 후에는 스프링 컨테이너에서 제거되는 생존 범주(=scope?)
    }
    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");

        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }


    }
}
