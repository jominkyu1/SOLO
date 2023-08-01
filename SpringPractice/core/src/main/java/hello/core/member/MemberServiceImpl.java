package hello.core.member;

public class MemberServiceImpl implements MemberService{
    //추상화에만 의존
    private final MemberRepository memberRepository;

    //생성자로 memberRepository 구현체를 받아옴
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
}
