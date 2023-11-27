select * from account;

select AVAIL_BALANCE from account;

select * from branch as b 
where b.city = 'Waltham';

select b.city, b.CUST_ID from customer as b 
where b.state = 'MA'  and  b.Cust_type_cd = 'I';

select * from customer as b 
where b.state ='MA' order by b.cust_id desc;

insert into customer (cust_id, address , CITY , CUST_TYPE_CD, FED_ID , POSTAL_CODE , STATE )
values ('15' , 'Ngo Van So' , 'Da Nang' , 'B', '66-666-66' , '12344' , 'MA' );

UPDATE officer AS O SET o.end_date = '2023-3-23' 
where O.Officer_ID = '3';

select * from officer as o 
where o.end_date IS null;

delete from customer  
where customer.cust_id = '15';

select  * from customer
 where customer.CUST_TYPE_CD = 'I'  limit 3 ; 

select count(CUST_ID) from customer 
where customer.CUST_TYPE_CD = 'I';

select AVG(CUST_ID) from customer 
where customer.CUST_TYPE_CD = 'I';

select SUM(CUST_ID) from customer 
where customer.CUST_TYPE_CD = 'I';

select * from customer as c
 where c.address like '%n';

select * from customer as c
 where c.city in ('waltham' , 'salem') ;

select * from employee as e
 where e.Start_date 
 between '2002-6-1' and '2002-9-3';

select count(distinct c.CUST_TYPE_CD), c.city 
from customer as c 
group by c.city 
order by count(c.cust_id) desc;

select c.city , o.first from customer as c 
inner join officer as o 
on c.CUST_ID = o.CUST_ID
 where c.CUST_ID = '13';