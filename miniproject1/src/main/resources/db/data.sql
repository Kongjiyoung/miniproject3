--더미데이터에 들어가 있는 post 다 지우기
--개인정보 입력
insert into user_tb(role,email,password,username,tel, profile, created_at)
values(1, 'ssar@nate.com', '1234', 'ssar ', '010-1234-5678 ', 'wewewewe', now());


--회사정보 입력
insert into user_tb(role,email,password,username,tel,company_name,company_address, company_num, profile, created_at)
values(2, 'ssar@nate.com', '1234', 'ssar ', '010-1234-5678 ', '메타코딩','부산진구','12322232113','wewewewe', now());


--이력서정보 입력
insert into resume_tb(person_id, title, profile, username, birth, tel, address, email, portfolio, introduce, career, simple_introduce,created_at)
values(1,'돈많이벌거에요','2123232131','ssar','19991012','010-1234-5678','진해구','ssar@nate.com','http://qmfmqfqmf','돈많이줘요', '1개월근무','돈많이 벌거에요',now());
insert into resume_tb(person_id, title, profile, username, birth, tel, address, email, portfolio, introduce, career, simple_introduce,created_at)
values(1,'휴가갈꺼에요','333333331q1','love','19500524','010-1222-2222','부산진구','love@nate.com','http://dffdfddf','휴가많이줘요', '5일근무', '돈많이 줘요', now());


--채용공고 입력
insert into post_tb(company_id, title, career, pay, work_condition, work_start_time, work_end_time, deadline, introduce,task, profile, created_at)
values(2,'돈적게줄거야',1111111,'2000만','구림','8:00', '20:00', '20250910','악덕회사임', '잡일','121332121121', now());
insert into post_tb(company_id, title, career, pay, work_condition, work_start_time, work_end_time, deadline, introduce,task, profile, created_at)
values(2,'돈아주적게줄거야',22222,'3000만','구림','8:00', '20:00', '20240505','완전악덕회사임', '완전잡일','121332122323', now());

-- entity수정 career데이터 varchar로 고치기
--스킬
--이력서에서
insert into skill_tb(skill_id, resume_id, role)
values('JAVA', 1, 1);
insert into skill_tb(skill_id, resume_id, role)
values('JAVA', 2, 1);
insert into skill_tb(skill_id, resume_id, role)
values('C', 1, 1);
insert into skill_tb(skill_id, resume_id, role)
values('C', 2, 1);

--공고에서
insert into skill_tb(skill_id, post_id, role)
values('JAVA', 1, 1);


--지원
insert into apply_tb(resume_id, post_id, post_writer_id, resume_writer_id, is_pass )
values(1, 1, 2, 1,'합격');


--제안
insert into offer_tb(resume_id, post_id, post_writer_id, resume_writer_id, title,content,created_at )
values(1, 1, 2, 1,'우리회사와','돈많이줄게', now());


--ispass varchar타입에서 int로 바꾸기
--스크랩
--회사 이력서스크랩
insert into scrap_tb(resume_id, post_writer_id,created_at )
values(1, 2, now());


--개인 채용공고스크랩
insert into scrap_tb(post_id, resume_writer_id,created_at )
values(1, 1, now());

