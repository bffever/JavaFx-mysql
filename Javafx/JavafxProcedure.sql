delimiter $$ 
create procedure update_cash_amount(userid int , amount int)
begin 
start transaction;
	if amount<1 then  #ask teacher why <=0 cannot trigger
		signal sqlstate'22003' set message_text='invalid transfer amount , transfer amount cannot be less than 1';
    end if;
	update accounts s
    set s.cash_amount=s.cash_amount+amount
    where s.userid=userid;
commit;
end$$

delimiter ;



delimiter $$ 
create procedure get_cash_balance(userid int)
begin 
start transaction;
	select s.cash_amount 
    from accounts s
    where s.userid=userid;
commit;
end$$

delimiter ;


delimiter $$ 
create procedure get_cash_balance(userid int)
begin 
start transaction;
	select s.cash_amount 
    from accounts s
    where s.userid=userid;
commit;
end$$

delimiter ;



delimiter $$ 
create procedure get_userid_from_accountid(useraccount varchar(45))
begin 
start transaction;
	select s.userid
    from accounts s
    where s.account_number=useraccount;
commit;
end$$

delimiter ;

delimiter $$
create procedure decrease_cashAmount(userid int ,cash_amount int)
begin 
start transaction;
	update accounts s 
    set s.cash_amount = s.cash_amount-cash_amount
    where s.userid=userid;
commit;
end$$

delimiter ;

