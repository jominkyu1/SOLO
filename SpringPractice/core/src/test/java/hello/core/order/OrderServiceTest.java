package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;
    Member member = new Member(1L, "테스트", Grade.VIP);

    @BeforeEach //테스트 실행 전 각 메소드 단계마다 무조건 실행
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
     void createOrder() {
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "아이템", 50000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(5000);
    }
}
