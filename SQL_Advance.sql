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

/*Tạo view khách hàng ở Salem */
Create view Salem_customers as 
select * from Customer 
where customer.city = 'Salem';

/* đổi tên view */
rename table databasename.Waltham to Waltham_customer;

 /* thay đổi câu lệnh trong view */
alter view databasename.Waltham_customer as 
Select c.CUST_ID , c.address from customer as c where c.city = 'Waltham';

/* lấy record từ view */
select * from databasename.Waltham_customer;

/*Xoá view */
drop view databasename.salem_customers;

/* tạo view test_add_column, thêm cột cáu trúc Customer và lấy record không có column test 
 => không gây ra sử thay đổi*/  
Create view test_add_column as 
select * from Customer 
where customer.city = 'Salem';

alter table customer add test varchar(30);

select * from databasename.test;

/*tạo view test_drop_column, xoá cột cáu trúc Customer và lấy record  
 => Error */
alter table customer drop test ;

create view test_drop_column as
select * from Customer
where customer.city = 'Salem';

select * from test_drop_column;

/*So sánh subquery lấy customer có CUST_ID lớn hơn hoặc bặng giá trị min ở cột CUST_ID trong bảng officer */
Select * from customer as c where c.CUST_ID  >=  (Select min(o.CUST_ID) from officer as o );

/*So sánh subquery lấy customer có CUST_ID lớn hơn hoặc bặng giá trị số đếm CUST_ID = '1' ở bẳng account*/
select * from customer as c where c.CUST_ID  >= (select count(CUST_ID) from account as a where a.CUST_ID = '1');

/* Subquery any	lấy record customer  có CUST_ID lơn hơn hoặc bằng bất ký giá trị nào từ CUST_ID trong bảng officer với điều kiện lớn hơn 11 */
select * from customer as c where c.CUST_ID >= any ( select o.CUST_ID from officer as o  where o.CUST_ID > '11');

/* Subquery All lấy record customer có CUST_ID lớn hơn tất cả giá trị CUST_ID trong bảng officer với điều kiện <=11*/
select * from customer as c where c.CUST_ID > All (Select o.CUST_ID from officer as o where o.CUST_ID <= '11') ;

/*Subquery Row so sánh giá trị trong cột CUST_ID và STATE ở bảng customer với CUST_ID 
với STATE ở bằng officer với điều kiện CUST_ID = '11'*/
alter table officer add STATE varchar(10);

select * from customer as c where row(c.CUST_ID , c.STATE) = (select o.CUST_ID , o.STATE from officer as o where o.CUST_ID = '11');
/*Subquery lấy record customer với ít nhất 1 record c.CUST_ID tồn tại trong account với điều kiện o.CUST_ID = c.CUST_ID và o.ACCOUNT_ID > 20 */
select * from customer as c where exists (Select * from account as o where o.CUST_ID = c.CUST_ID and o.ACCOUNT_ID > 20);
/* Case*/
select a.CUST_ID , a.AVAIL_BALANCE , a.ACCOUNT_ID ,
	case 
		when a.AVAIL_BALANCE > 10000  then 'good'
        when a.AVAIL_BALANCE = 10000 then 'not bad'
        else 'bad'
	end as 'text'
from account as a	order by a.AVAIL_BALANCE desc