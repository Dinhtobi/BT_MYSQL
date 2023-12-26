
alter table User
add column verification_code varchar(6);

alter table User
add column enabled boolean;
