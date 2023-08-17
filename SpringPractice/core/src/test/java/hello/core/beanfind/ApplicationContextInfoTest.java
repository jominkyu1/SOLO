package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.management.ConstructorParameters;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("모든 빈 출력하기")
    public void findAllBean(){
        String[] beanNames = ac.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            //System.out.println(beanName);
            Object bean = ac.getBean(beanName);
            System.out.println("name = " + beanName + " object = " + bean);
        }
    }
    
    @Test
    @DisplayName("AppConfig 빈만 출력")
    public void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // ROLE_APPLICATION = 직접 등록한 애플리케이션 빈
            // ROLE_INFRASTRUCTURE = 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}