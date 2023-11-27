/*Select tất cả record*/
select * from account;

/* Select có điều kiện*/
select * from branch as b 
where b.city = 'Waltham';
/* Select có 2 điều kiện*/
select b.city, b.CUST_ID from customer as b 
where b.state = 'MA'  and  b.Cust_type_cd = 'I';
/* Select sắp xếp*/
select * from customer as b 
where b.state ='MA' order by b.cust_id desc;
/* Insert thêm mới*/
insert into customer (cust_id, address , CITY , CUST_TYPE_CD, FED_ID , POSTAL_CODE , STATE )
values ('15' , 'Ngo Van So' , 'Da Nang' , 'B', '66-666-66' , '12344' , 'MA' );
/* Update Cập nhật*/
UPDATE officer AS O SET o.end_date = '2023-3-23' 
where O.Officer_ID = '3';
/* Select tất cả giá trị null */
select * from officer as o 
where o.end_date IS null;
/* delete có điều kiện*/
delete from customer  
where customer.cust_id = '15';

/* Select giới hạn 3*/
select  * from customer
 where customer.CUST_TYPE_CD = 'I'  limit 3; 

/* Select đếm các record có CUST_TYPE_CD = 'I'*/
select count(CUST_ID) from customer 
where customer.CUST_TYPE_CD = 'I';

/* Select giá trị trung bình CUST_ID với CUST_TYPE_CD = 'I' */
select AVG(CUST_ID) from customer 
where customer.CUST_TYPE_CD = 'I';

/* Select tổng các CUST_ID với CUST_TYPE_CD = 'I' */
select SUM(CUST_ID) from customer 
where customer.CUST_TYPE_CD = 'I';
/* Select all điều kiện address kết thức 'n' */
select * from customer as c
 where c.address like '%n';
/* Select all điều kiện column city = waltham hoặc salem */
select * from customer as c
 where c.city in ('waltham' , 'salem') ;
/* Select all điều kiện nằm trong '2002-6-1' và '2002-9-3' */
select * from employee as e
 where e.Start_date 
 between '2002-6-1' and '2002-9-3';

/* Select tổng số CUST_TYPE_CD ở mỗi city với điều kiện số đếm > 1 
và sắp xếp giảm */
select count(c.CUST_TYPE_CD), c.city 
from customer as c 
group by c.city  having count(c.CUST_TYPE_CD) > 1
order by count(c.cust_id) desc;

/* Loại bỏ giá trị trùng lặp*/
select distinct  c.city from customer as c;

/* gộp 2 bảng và lấy record có CUST_ID giống nhau*/
select * from customer as c 
inner join officer as o 
on c.CUST_ID = o.CUST_ID;
/* gộp 2 bảng và lấy record có CUST_ID giống nhau và record có CUST_ID khác nhau thì chỉ lấy bảng 1 bằng 2 null*/
select * from customer as c 
left join officer as o 
on c.CUST_ID = o.CUST_ID;

/* gộp 2 bảng và lấy record có CUST_ID giống nhau và record có CUST_ID khác nhau thì chỉ lấy bảng 2 bằng 1 null*/
select * from customer as c 
right join officer as o 
on c.CUST_ID = o.CUST_ID;