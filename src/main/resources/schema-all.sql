drop table information if exists;

create table information (
	id bigint identity primary key, 
	name varchar(20) not null,
	age varchar(20),
	avg_mark double not null, 
	classification varchar(30) not null
)