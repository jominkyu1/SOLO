package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("Duplicate Error")
    void findBeanByParentTypeDuplicate(){
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, //복수개의 타입 발견시 발생하는 예외
                () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모타입 검색시, 자식이 둘 이상이면 자식 빈 이름 지정해주면된다.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(bean).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("부모타입으로 모두 조회")
    void discountPolicyFindAll(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String string : beansOfType.keySet()) {
            System.out.println("string = " + string);
        }
    }
    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
