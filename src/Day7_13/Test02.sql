#---------查询所有学生的某门科目的成绩排名
#(科目名,科目编号,学号,学生名字,班级,学生成绩)
select C.name,C.id,A.id,A.name,A.clazz,B.score
from student as A join score as B on A.id=B.studentId
join cource as C on B.courceId=C.id
where C.name ='语文'
order by score desc;

#-----------查询所有学生的某门科目的成绩排名
#(科目名,科目编号,学号,学生名字,班级,学生成绩)
#增加序号
set @rownum=0;
select AA.*,@rownum:=@rownum+1 as 排名 from (select C.name,C.id as 课程编号,A.id,A.name as 学生名字,A.clazz,B.score
from student as A join score as B on A.id=B.studentId
join cource as C on B.courceId=C.id
where C.name ='语文'
order by score desc)as AA;

#------------查询每个学生考的最好的分数，和科目编号
#学号,课程编号，分数
select A1.studentId,courceId,A1.score from score as A1 join( select studentId,max(score) as zgf from score
group by studentId) as B1 on A1.studentId=B1.studentId and A1.score =B1.zgf;

#-------------查询每位学生考的最高的分数
#学生id，名字,班级，课程名字，分数
select A.id,A.name,A.clazz,C.name,B.cj
from student as A join (select A1.studentId as xh,courceId as kch,A1.score as cj from score as A1 join( select studentId,max(score) as zgf from score
group by studentId) as B1 on A1.studentId=B1.studentId and A1.score =B1.zgf) as B on A.id =B.xh
join cource as C on B.kch =C.id;

#----------查询有多门课程是最高分的学生
#1.获得多门成绩最高分的学生,[学号,分数]
select A1.studentId, B1.zgf from score as A1 join( select studentId,max(score) as zgf from score
group by studentId) as B1 on A1.studentId=B1.studentId and A1.score =B1.zgf group by A1.studentId having count(A1.studentId)>1;

#2.通过学号,和分数取获得，信息和课程编号
select a.id,a.name,a.clazz,c.name,b.score
from student as a
join score as b on a.id =b.studentId
join cource as c on b.courceId=c.id
join (select A1.studentId, B1.zgf from score as A1 join( select studentId,max(score) as zgf from score
      group by studentId) as B1 on A1.studentId=B1.studentId and A1.score =B1.zgf group by A1.studentId having count(A1.studentId)>1
) as d  on a.id =d.studentId and b.score =d.zgf;

#-------------查询各学科(文理科)成绩最好的100位
#1.查询每个学生的总分
select a.id,a.clazz,sum(b.score) as xszf
from student as a
join score as b on a.id=b.studentId
group by a.id;


select a.id,a.clazz,sum(b.score) as xszf
from student as a
join score as b on a.id=b.studentId
where substring(a.clazz,1,2)='文科' group by a.id order by xszf desc limit 100 ;




























