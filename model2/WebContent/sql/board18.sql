--board18 테이블 생성

create table board18(
board_no int primary key
,board_name varchar2(20)
,board_pass varchar2(15)
,board_title varchar2(50)
,board_cont varchar2(3000)
,board_file varchar2(50)
,board_re_ref int
,board_re_lev int
,board_re_seq int
,board_hit int
,board_date date
);

