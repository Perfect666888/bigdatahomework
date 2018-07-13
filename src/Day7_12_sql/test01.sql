--查询全部学生的总成绩
select id,name,gender,clazz,sum(score)  from student
join score on id= studentId group by id ;


--全部学生总成绩排序(降序)
select id,name,gender,clazz,sum(score) as zf from student
join score on id= studentId group by id order by zf desc;

--查询理科学生成绩总分并排序(降序)
select id,name,gender,clazz,sum(score) as zf from student
join score on id= studentId  where clazz like '理科%' group by id order by zf desc;

--查询文科学生成绩总分并排序(降序)
select id,name,gender,clazz,sum(score) as zf from student
join score on id = studentId where clazz like '文科%' group by id order by zf desc;

--全部学生总成绩排序(降序),增加自增列
set @rownum=0;
select A.*,@rownum:=@rownum+1  as '排名' from (select student.id,name,gender,clazz,sum(score) as zf from student
join score on id= studentId group by id order by zf desc) as A;

--查询理科学生成绩总分并排序(降序),增加自增列
set @rownum=0;
select A.*,@rownum:=@rownum+1 as '排名' from
 (
 select id,name,gender,clazz,sum(score) as zf from student
  join score on id =studentId where clazz like '理科%' group by id order by zf desc
  ) as A;

--查询文科学生成绩总分并排序(降序),增加自增列
set @rownum =0;
select A.*,@rownum:=@rownum+1 as '排名' from
(
 select id,name,gender,clazz,sum(score) as zf from student
 join score on id=studentId where clazz like '文科%' group by id order by zf desc
 )as A;


--按照班级划分，总成绩排序(降序),增加自增列

select distinct A.clazz,B.id,B.name,B.gender,B.zf from student as A left join
(
select id,name,gender,a.clazz,sum(score) as zf from student as a
 join score on id =studentId group by id order by zf desc
 )as B on A.clazz = B.clazz;



set @rownum =0;
select A.*,@rownum:=@rownum+1 as '排名' from
(
 select id,name,gender,clazz,sum(score) as zf from student
 join score on id=studentId where clazz = group by id order by zf desc
 )as A;


