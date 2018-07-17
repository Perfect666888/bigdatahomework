
#查询文科一班分数最高的同学 [学生id,学生姓名，学生分数]
select id,name,sum(score) as zf from student
join score on id= studentId
where clazz ='文科一班' group by id order by zf desc limit 1;

#查询文科一般各科的平均分 [科目名，科目平均分]
select C.name as '科目名',avg(B.score) as 科目平均分 from cource as C
join score as B  on C.id =B.courceId
join student as A on B.studentId =A.id
where A.clazz='文科一班' group by C.name order by 科目平均分desc ;


#查询文科班语文平均分班级排名 [班级名，科目名，平均分]
select A.clazz,C.name,avg(B.score) as pj from student as A
join score as B on A.id =B.studentId
join cource as C on B.courceId = C.id
where C.name ='语文' and A.clazz like '文科%'
group by A.clazz order by pj desc;

#查询理科班数学学生分数排名 [学生姓名，学生班级，数学分数]
select A.name,A.clazz,B.score as fs from student as A
join score as B on A.id=B.studentId
join cource as Con C.id =B.courceId
where A.clazz like '理科%' and C.name ='数学'
order by fs desc;

