#ElecMatter 代办事宜
create table elecMatter(
       matterId varchar(100) primary key,
       stationRunStatus text, #站点运行情况
       devRunStatus text, #设备运行情况
       createDate date #创建日期
)
#用户表
create table elecUser(
          #基本数据，为了满足基本需求设置
          userId varchar(100) primary key ,#ID
          username varchar(30),#用户名
          password varchar(100),#密码
          account varchar(30) unique,#账户
          gender varchar(20) ,#性别
          birthday date,#出生日期
          address varchar(255),#地址
          homeTel varchar(20),#家庭电话
          email varchar(30),#邮箱
          phone varchar(20),#手机号
          units varchar(30),#所属单位
          isDuty varchar(30),#是否入职
          onDutyDate date,#入职日期
          offDutyDate date,#离职日期
          comment text,#备注
          
          #特殊数据，主要是为了实现问责机制，记录操作者
          createUser varchar(100),#创建者的ID
          createDate date,#创建日期
          lastUpdateUser varchar(100),#最后一次修改者，其他的修改者放在另外的历史表或者修改日志中
          lastUpdateDate date,#最后一次修改日期
          
          #假删除，删除时并不真的删除，仅仅设置该条数据，查询时条件中就会加上该条件，如果已被设置为已删除就读取记录
          isDelete boolean
          );
          
 #角色表
 create table elecRole(
          roleId varchar(100) primary key,
          roleName varchar(50) unique
 );
 
 #权限表
 create table elecFunction(
          functionId varchar(100) primary key,
          path varchar(255) unique,#请求路径
          functionName varchar(50) not null,#权限名称
          groups varchar(50) not null#组名
 );
 
 #用户角色关联表
 create table elecUserRole(
          userId varchar(100) not null,
          foreign key(userId) references elecUser(userId)  on delete cascade on update cascade,
          roleId varchar(100) not null,
           foreign key(roleId) references elecRole(roleId)  on delete cascade on update cascade
 );
 
 #角色权限关联表
 create table elecRoleFunction(
          roleId varchar(100) not null,
           foreign key(roleId) references elecRole(roleId)  on delete cascade on update cascade,
           functionId varchar(100) not null,
          foreign key(functionId) references elecFunction(functionId)  on delete cascade on update cascade
 );
 
 #申请模板文件表
 create table elecApplyTemplate(
         templateId varchar(100) primary key,#模板ID
         filename varchar(100),#文件名
         path text,#文件路径
         processDefinitionKey varchar(100) #对应的流程定义的名称
 );
 
 #申请信息表
 create table elecApply(
         applyId varchar(100) primary key,
         userId varchar(100),
         username varchar(50),
         account varchar(50),
         processDefinitionId varchar(100),
         processDefinitionKey varchar(100),
         applyTime date,
         applyStatus varchar(50),
         filename varchar(100),
         path text
 );