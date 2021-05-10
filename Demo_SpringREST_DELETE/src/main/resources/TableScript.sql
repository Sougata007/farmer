drop database if exists customer_db;
create database customer_db;
use  customer_db;


create table customer(
   customer_id int auto_increment,
   email_id varchar(50),
   name varchar(20),
   date_of_birth date,
   constraint ps_customer_id_pk primary key (customer_id)
);

create table User(
    fullname varchar(100),
    email_id varchar(100),
    username varchar(100),
    password varchar(100),
    constraint ps_username_pk primary key (username)
);

insert into customer (customer_id, email_id, name, date_of_birth) values (1, 'martin@infy.com', 'Martin', sysdate()- interval 9000 day);
insert into customer (customer_id, email_id, name, date_of_birth) values (2, 'tim@infy.com', 'Tim', sysdate()- interval 5000 day);
insert into customer (customer_id, email_id, name, date_of_birth) values (3, 'jack@infy.com', 'Jack', sysdate()- interval 6000 day);

commit;
select * from customer;
desc User;