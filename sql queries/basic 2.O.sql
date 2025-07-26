use customer;

create table student(
id int NOT NULL auto_increment,
first_name varchar(15),
last_name varchar(15),
age int,
primary key(id));
show tables;

alter table student modify first_name varchar(15) NOT NULL, modify last_name varchar(15) NOT NULL;
alter table student modify email varchar(25) primary key;

insert into student(first_name,last_name,age) values
("Ashish","Dabhi",21),
("Tushar","Parmar",21),
("Chirag","Dabhi",23),
("Sumit","Makavana",21);

desc student;

update student set email = "ashish@gamil.com" where id IN (3);
update student set email = "chirag@gamil.com" where id IN (3);

select * from student;
select count(*) from student;
select age, count(*) as count from student group by age;
