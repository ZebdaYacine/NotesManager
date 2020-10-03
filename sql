
use NotesManagres;
create table specialty(
id int primary key auto_increment,
name varchar(20) not null
);

create table module(
id int primary key auto_increment,
name varchar(20) not null,
semestterNumber int not null,
coefficient int not null,
specialtyID int,
FOREIGN KEY(specialtyID) REFERENCES specialty(id) on delete cascade on update cascade
);

create table teacher(
id int primary key auto_increment,
firstname varchar(50) not null,
lastname varchar(50) not null,
matrucula int not null
); 

create table student(
id int primary key auto_increment,
firstname varchar(50) not null,
lastname varchar(50) not null,
matrucula int not null
); 

create table follows(
id int primary key auto_increment,
studentID int,
teacherID int,
moduleID int,
FOREIGN KEY(studentID) REFERENCES student(id) on delete cascade on update cascade,
FOREIGN KEY(teacherID) REFERENCES teacher(id) on delete cascade on update cascade,
FOREIGN KEY(moduleID) REFERENCES module(id) on delete cascade on update cascade,
examenNote float not null,
tdNote float ,
avaregeModule float not null
); 



