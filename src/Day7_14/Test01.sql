#--------------------查询年级排名前十学生各科的分数 [学号,学生姓名，学生班级，科目名，分数]
#1.查询学生总分
select studentId,sum(score) as xszf
from score group by studentId;
#2.选出前10
select studentId,sum(score) as xszf
from score group by studentId order by xszf desc limit 10;
#3.关联学生信息
select a.id,a.name,a.clazz,c.name,b.score
from student as a
join score as b on a.id =b.studentId
join cource as c on b.courceId=c.id
join (select studentId,sum(score) as xszf
      from score group by studentId order by xszf desc limit 10) as d on a.id =d.studentId

#-----------------------查询总分大于学科(文理科)平均分的学生文理科分开 [学号，姓名，班级，总分]
#1.求出每个学生的总分
select studentId,a.name,a.clazz,sum(score) as xszf
from score as b
join student as a on a.id =b.studentId
group by studentId;

#2.求出文理科总分平均分
select substring(b.clazz,1,2) as lb,avg(b.xszf) as kmpjf
from (select studentId,a.name,a.clazz,sum(score) as xszf
      from score as b
      join student as a on a.id =b.studentId
      group by studentId)as b
group by lb;
#3.求出大于平均分的学生
select a.*,b.kmpjf
from(select studentId,a.name,a.clazz,sum(score) as xszf
     from score as b
     join student as a on a.id =b.studentId
     group by studentId) as a
join (select substring(b.clazz,1,2) as lb,avg(b.xszf) as kmpjf
      from (select studentId,a.clazz,sum(score) as xszf
            from score as b
            join student as a on a.id =b.studentId
            group by studentId)as b
      group by lb)as b on substring(a.clazz,1,2)=b.lb
where a.xszf>b.kmpjf;

#-----------------------查询总分大于班级平均分的学生文理科分开 [学号，姓名，班级，总分]
#1.求学生总分 [学号，姓名，班级，总分]
select a.id,a.name,a.clazz,sum(score) as xszf
from student as a
join score as b on a.id =b.studentId
group by a.id;
#2.求班级总分平均分 [班级，平均分]
select a.clazz,avg(b.xszf) as bjpjf
from student as a
join (select a.id,a.name,a.clazz,sum(score) as xszf
      from student as a
      join score as b on a.id =b.studentId
      group by a.id) as b on a.clazz=b.clazz
group by a.clazz;
#3.关联表格,选出大于班级平均分的人
select a.*,b.bjpjf
from (select a.id,a.name,a.clazz,sum(score) as xszf
      from student as a
      join score as b on a.id =b.studentId
      group by a.id) as a
join (select a.clazz,avg(b.xszf) as bjpjf
      from student as a
      join (select a.id,a.name,a.clazz,sum(score) as xszf
          from student as a
          join score as b on a.id =b.studentId
          group by a.id) as b on a.clazz=b.clazz
      group by a.clazz)as b on a.clazz=b.clazz
 where a.xszf>b.bjpjf order by a.clazz;


#-----------------------查询每科都及格的学生 [学号，姓名，班级，科目，分数]
#1.每门科目的及格线
select id,name,score*0.6 as jgx
from cource;
#2.统计每位学生考试的科目
select studentId,count(1) as ykkms
from score
group by studentId;
#3.统计每位学生及格的科目数
select b.studentId,count(1) as jgkms
from(select id,name,score*0.6 as jgx
     from cource) as a
join score as b on a.id =b.courceId
where b.score >= a.jgx
group by b.studentId ;
#4.及格科目与考试科目比较，选出相等的学号
select a.studentId
from (select studentId,count(1) as ykkms
      from score
      group by studentId) as a
join (select b.studentId,count(1) as jgkms
      from(select id,name,score*0.6 as jgx
           from cource) as a
      join score as b on a.id =b.courceId
      where b.score >= a.jgx
      group by b.studentId)as b on a.studentId=b.studentId
where a.ykkms=b.jgkms;
#5.关联所有表，获得全部数据[学号，姓名，班级，科目，分数]
select a.id,a.name,a.clazz,c.name,b.score
from student as a
join score as b on a.id =b.studentId
join cource as c on b.courceId=c.id
join (select a.studentId
      from (select studentId,count(1) as ykkms
            from score
            group by studentId) as a
      join (select b.studentId,count(1) as jgkms
            from(select id,name,score*0.6 as jgx
                 from cource) as a
            join score as b on a.id =b.courceId
            where b.score >= a.jgx
            group by b.studentId)as b on a.studentId=b.studentId
      where a.ykkms=b.jgkms) as d on a.id =d.studentId;

#------查询偏科最严重的前100名学生  [学号，姓名，班级，科目，分数]

POW(x,y) ----x的y次方
sqrt()  平方根
#1、查询每个学生所有科目的平均分
select a.id,a.name,avg(b.score) as pjf
from student as a
join score as b on a.id =b.studentId
group by a.id;
#2、计算每个学生科目分数和平均分的方差
select a.studentId,b.name,sqrt(sum(pow(abs(a.score-b.pjf),2))) as fc
from score as a
join (select a.id,a.name,avg(b.score) as pjf
      from student as a
      join score as b on a.id =b.studentId
      group by a.id) as b on a.studentId =b.id
 group by a.studentId;

#3、按分差排名，取方差最大的前100名学生
select a.studentId,b.name,sqrt(sum(pow(abs(a.score-b.pjf),2))) as fc
from score as a
join (select a.id,a.name,avg(b.score) as pjf
      from student as a
      join score as b on a.id =b.studentId
      group by a.id) as b on a.studentId =b.id
 group by a.studentId order by fc desc limit 100;
#4、管理学生成绩，得到结果
#[学号，姓名，班级，科目，分数]
select a.id,a.name,a.clazz,c.name,b.score
from student as a
join score as b on a.id =b.studentId
join cource as c on b.courceId = c.id
join (select a.studentId,b.name,sqrt(sum(pow(abs(a.score-b.pjf),2))) as fc
      from score as a
      join (select a.id,a.name,avg(b.score) as pjf
            from student as a
            join score as b on a.id =b.studentId
            group by a.id) as b on a.studentId =b.id
       group by a.studentId order by fc desc limit 100) as d on a.id =d.studentId







