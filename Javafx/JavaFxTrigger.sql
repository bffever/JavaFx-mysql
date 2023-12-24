
create table account_audit
(
	account_id int not null,
    transfer_amount int not null,
    before_value int not null,
    after_value int not null
);


delimiter $$
create trigger account_after_update
	after update on accounts 
    for each row
begin 
	insert into account_audit
    values(new.userid,new.cash_amount-old.cash_amount,old.cash_amount,new.cash_amount);
end$$
delimiter ;