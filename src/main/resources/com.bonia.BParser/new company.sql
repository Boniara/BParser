create database if not exists company;

use company;

create table if not exists address (
id int(11) auto_increment not null,
country varchar(255) not null,
city varchar(255) not null,
street varchar(255) not null,
house int(11) not null check(house > 0),
primary key(id));

create table if not exists employee (
id int(11) auto_increment not null,
firstName varchar(255) not null,
lastName varchar(255) not null,
age int(3) not null check (age > 0),
address_id int(11) not null,
primary key(id),
foreign key(address_id) references address(id) on delete cascade);

create table if not exists company (
id int(11) auto_increment not null,
companyName varchar(255) not null,
primary key(id));

create table if not exists department (
id int(11) auto_increment not null,
departmentName varchar(255) not null,
address_id int(11) not null,
company_id int(11) not null,
primary key(id),
foreign key(address_id) references address(id) on delete cascade,
foreign key(company_id) references company(id) on delete cascade);

create table if not exists _position (
id int(11) auto_increment not null,
positionName varchar(255) not null,
primary key(id));

create table if not exists employee_department (
employee_id int(11) not null,
department_id int(11) not null,
position_id int(11) not null,
foreign key(employee_id) references employee(id) on delete cascade,
foreign key(department_id) references department(id) on delete cascade,
foreign key(position_id) references _position(id) on delete cascade);