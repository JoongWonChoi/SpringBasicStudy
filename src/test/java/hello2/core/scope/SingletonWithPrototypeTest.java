package hello2.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }
    @Test //singletonBean 내부에 prototype Bean을 생성하여 주입하였을 때 어떻게 되는지에 대한 test
        //prototype bean은 호출 시 마다 새롭게 생성된다.
        //prototype bean은 호출 시 생성되고 주입이 되면 스프링 컨테이너에서 제거된다. 그 후 책임은 주입된 빈에 있는 것.
        //그렇다면 singleton Bean에서 prototype빈을 호출한다면, 호출 시 마다 새로운 prototype빈이 생성되어 주입될까?
    void prototypeWithSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class); //Spring 컨테이너에서 등록된 'ClinetBean' 타입의 빈 가져오기.(객체 불러오기)
        int count1 = clientBean1.logic(); //호출된 빈(객체)로 해당 메소드에 접근이 가능하다. 객체 생성과 같은 원리!
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }
    @Scope("singleton")
    static class ClientBean {
        //=============기존과 같이 생성자를 통한 의존관계 주입============
        //prototype 빈이 singleton Bean인 ClientBean 에 삽입되고, 스프링 컨테이너에서는 사라졌기 때문에 ClientBean 내부에서 숙주?가 됨.
        //singletonBean은 생성된 후 지속되기 때문에 prototype으로 주입된 bean은 어떻게 보면 공유자원이 되는 것!
        /*private final PrototypeBean prototypeBean; //생성 시점에 주입*/
        /*@Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }*/
        /*public int logic() {
            prototypeBean.addCount();
            int result = prototypeBean.getCount();
            return result;
        }*/
       /*
                    //====================ObjectProvider 사용====================
        //ObjectProvider의 getObject를 호출하면 내부에서 스프링 컨테이너를 통해 해당 빈을 찾아 반환한다.
        //따라서 prototype 빈을 getObject로 호출하면 그 시점에 스프링컨테이너에 prototype 빈을 생성하고 반환하게 됨. 그 후엔 스프링 컨테이너에서 사망!
        //이로써 원할 때 싱글톤 빈 내에서 항상 새로운 프로토타입 빈을 생성하고 받아올 수 있다.
        //but spring프레임워크에 의존적인 단점.
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        //private ObjectFactory<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();//getObject를 통해 새로운 prototype bean을 반환해줌.
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }*/
        //=================자바 표준(javax) Provider 사용=====================
        //get() 메서드 하나로 단순한 기능
        //자바 표준이지만, 별도의 라이브러리가 필요함.
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();//getObject를 통해 새로운 prototype bean을 반환해줌.
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init() {
            System.out.println("Prototype.init"+this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
