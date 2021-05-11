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
    user_type varchar(40),
    constraint ps_username_pk primary key (username)
);

create table Product(
    seller_name varchar(100),
	product_name varchar(100),
	product_price varchar(100),
	constraint ps_seller_name_pk primary key (seller_name)
);

create table Cart(
    product_name varchar(100),
    price varchar(200),
    constraint ps_product_name_pk primary key (product_name)
);


insert into Product (seller_name, product_name,product_price) values('ram','Potato','23');
insert into Product (seller_name, product_name,product_price) values('shyam','Tomato','22');
insert into Product (seller_name, product_name,product_price) values('arjun','Rice','30');
insert into Product (seller_name, product_name,product_price) values('mamta','wheat','27');

commit;
select * from customer;
desc User;

commit;
desc Product;