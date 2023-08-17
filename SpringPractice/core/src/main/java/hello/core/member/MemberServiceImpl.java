package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    //추상화에만 의존
    private final MemberRepository memberRepository;

    //생성자로 memberRepository 구현체를 받아옴
    @Autowired // ac.getBean(MemberRepository.class) 처럼 동작
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findbyId(id);
    }

    //for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
