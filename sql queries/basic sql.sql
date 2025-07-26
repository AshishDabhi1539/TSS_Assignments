show databases;
create database customer;
drop database customer;
show databases;
use customer;

show tables;
CREATE TABLE customer_info(
id integer auto_increment,
first_name varchar(10),
last_name varchar(10),
salary integer,
primary key(id));
show tables;
select * FROM customer_info;

INSERT INTO customer_info(first_name,last_name,salary) 
values
("Ashish","Dabhi",90000),
("Dishant","Upadhyay",60000),
("Mohit","Makavana",85000),
("Tushar","Parmar",75000),
("Harshad","Dalsaniya",NULL);
select * FROM customer_info;
select * FROM customer_info where salary is NULL;
select * FROM customer_info where salary is not NULL;

update customer_info set salary = 85000 where id = 10;
delete from customer_info where id = 5;

alter table customer_info add email varchar(25);
update customer_info set email = "dishant@gmail.com" where id IN (2,7) ;
update customer_info set email = "mohit@gmail.com" where id IN (3,8);
update customer_info set email = "tushar@gmail.com" where id IN (4,9);
update customer_info set email = "harshad@gmail.com" where id IN (5,10);

select * FROM customer_info;

alter table customer_info add dob date;
alter table customer_info modify dob year;
alter table customer_info drop dob;
desc customer_info;

select * from customer_info where salary between 80000 and 90000;

select distinct last_name from customer_info;
select count(*) from customer_info;
select id, avg(salary) from customer_info group by id having avg(salary) > 75000;
drop table customer_info;