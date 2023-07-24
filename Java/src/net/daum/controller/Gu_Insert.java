package net.daum.controller;

public class Gu_Insert{
    public static void main(String[] args) {
        new GuFrame();
    }
}

 /*  문제 겸 과제)
            1. create table tbl_gu(
                gno int primary key,
                gname varchar(20) not null,
                gtitle varchar(200) not null,
                gcont varchar(4000) not null,
                gdate date default sysdate
                );

            2. net.daum.vo 패키지의 GuVO.java 데이터 저장 빈 클래스를 생성하고
               tbl_gu테이블의 컬럼명과 같은 변수 정의 세터 게터또한 정의

            3. gno_seq10 시퀀스 생성 1부터 1씩증가 nocache

            4. net.daum.dao패키지에 GuDAOImp1.java 오라클 db연동 클래스 만들어서 JDBC프로그래밍
             가. Gu_Insert.java에서 스캐너로 입력한 글쓴이, 글제목, 글내용, 글번호 저장하는
             public int insertGu(GuVO g){}메서드 작성

            5. net.daum.controller 패키지에 방명록 목록을 가져오는 Gu_List.java를 작성해서 목록을
            가져오는 public List(GuVO) getGuList(){} 를 GuDAOImp1.java에 작성한다.
        * */