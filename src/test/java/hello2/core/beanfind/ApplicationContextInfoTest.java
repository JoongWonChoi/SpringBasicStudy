package hello2.core.beanfind;

import hello2.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

   /*ac.getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름을 조회한다.
     ac.getBean() : 빈 이름으로 빈 객체(인스턴스)를 조회한다.*/

   ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

   @Test
   @DisplayName("모든 빈 출력")
   void findAllBean() {
      String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // ==> Key : BeanNames
      //iter+tab
      for (String beanDefinitionName : beanDefinitionNames) {
         Object bean = ac.getBean(beanDefinitionName); // ==> Value : type of Beans . . Bean Name으로(k) Bean 객체(v) 조회 .
         System.out.println("name = " + beanDefinitionName + " // object" + bean);
      }


   }
}






