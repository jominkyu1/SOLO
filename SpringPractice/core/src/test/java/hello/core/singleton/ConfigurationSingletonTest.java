package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("getBeans를 통해 생성한 객체가 싱글톤을 지키는가?")
    void testGetBeans(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl bean = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl bean1 = ac.getBean(OrderServiceImpl.class);

        MemberRepository memberRepository = bean.getMemberRepository();
        MemberRepository memberRepository1 = bean1.getMemberRepository();

        Assertions.assertThat(memberRepository).isSameAs(memberRepository1);
        //멤버서비스 구현체와 오더서비스 구현체에 속한 memberRepository필드는 Bean내에서 new 키워드로
        //생성되어 싱글톤이 깨진것처럼 보이지만 같은 주소를 가리키고있음.
    }

    @Test
    void configurationDeep(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
    }
}