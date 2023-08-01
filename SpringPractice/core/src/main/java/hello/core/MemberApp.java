package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
            //appConfig를 통해 생성자 주입
        Member member = new Member(1L, "홍길동", Grade.VIP);

        memberService.join(member);

        /////////

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember.getName() = " + findMember.getName());
    }
}
