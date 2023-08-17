package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //interface(추상체)에만 의존하여야하는데, 전통적인 방식으로는 SOLID 원칙을 위배하지 않고 객체(구현체)를 할당할 수가 없음.
    // 따라서 AppConfig가 등장(생성자 주입)

    //생성자가 1개이면 @Autowired생략가능 (매개변수가 1개인게 아니라, 생성자 메소드가 1개인경우)
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    } 롬복의 @RequiredArgsConstructor 어노테이션을 통해 final 키워드가 붙은 생성자세팅 자동


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findbyId(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}