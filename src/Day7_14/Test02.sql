#查询每个班级有多少人
select clazz,count(1) as 班级人数
from student
group by clazz;

#查询每班的总分平均分
#1.查询学生总分
select a.id,a.name,a.clazz,sum(score) as zf
from student as a
join score as b on a.id =b.studentId
group by a.id
#2.班级平均分
select a.clazz,avg(zf) as bjpjf
from student as a
join (select a.id,a.name,a.clazz,sum(score) as zf
      from student as a
      join score as b on a.id =b.studentId
      group by a.id) as b on a.id =b.id
group by a.clazz;


#查询班级平均分大于文理科平均排名前的班级
#1.查询文理科平均分
select substring(b.clazz,1,2) as km ,avg(b.zf) as kmpjf
from (select a.id,a.name,a.clazz,sum(score) as zf
      from student as a
      join score as b on a.id =b.studentId
      group by a.id) as b
group by km;
#2.比较
select a.*,b.kmpjf
from  (select a.clazz,avg(zf) as bjpjf
       from student as a
       join (select a.id,a.name,a.clazz,sum(score) as zf
             from student as a
             join score as b on a.id =b.studentId
             group by a.id) as b on a.id =b.id
       group by a.clazz)as a
 join (select substring(b.clazz,1,2) as km ,avg(b.zf) as kmpjf
       from (select a.id,a.name,a.clazz,sum(score) as zf
             from student as a
             join score as b on a.id =b.studentId
             group by a.id) as b
       group by km)as b on substring(a.clazz,1,2)=b.km
where a.bjpjf>b.kmpjf

#查询总分高于班级总分平均分的学生
#1.查询学生总分
select a.id,a.name,a.clazz,sum(score) as zf
from student as a
join score as b on a.id =b.studentId
group by a.id
#2.班级平均分
select a.clazz,avg(zf) as bjpjf
from student as a
join (select a.id,a.name,a.clazz,sum(score) as zf
      from student as a
      join score as b on a.id =b.studentId
      group by a.id) as b on a.id =b.id
group by a.clazz;
#3.比较出信息 [名字,班级,总分]
select a.*,b.bjpjf
from (select a.id,a.name,a.clazz,sum(score) as zf
      from student as a
      join score as b on a.id =b.studentId
      group by a.id)as a
join (select a.clazz,avg(zf) as bjpjf
      from student as a
      join (select a.id,a.name,a.clazz,sum(score) as zf
            from student as a
            join score as b on a.id =b.studentId
            group by a.id) as b on a.id =b.id
      group by a.clazz) as b on a.clazz =b.clazz
where a.zf> b.bjpjf order by a.clazz desc ,a.zf desc


