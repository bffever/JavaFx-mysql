create database Javafx ;
use Javafx;

drop table if exists accounts;

create table accounts
(
	userid int not null auto_increment,
    userfirstname varchar(20) not null,
    userlastname varchar(20) not null,
	password varchar(6) not null,
    cash_amount int not null,
    account_number varchar(45),
    primary key(userid)
);

insert into accounts (userid,userfirstname , userlastname,password,cash_amount,account_number)
values (0,'Lee','Sheen',387612,1999,998877),(0,'Low','Zone',281992,3999,778899);

