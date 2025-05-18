insert into todo(id,description,done,target_date,username)
values (10001,'Learn AWS~!',false, current_date(),'in28minutes');

insert into todo(id,description,done,target_date,username)
values (10002,'Learn Azure!',false, current_date(),'in28minutes');

insert into todo(id,description,done,target_date,username)
values (10003,'Learn GCP!!!!!!',false, current_date(),'in28minutes');


insert into user_details(id,birth_date,name)
VALUES (10001,current_date(),'in28minutes');

insert into user_details(id,birth_date,name)
VALUES (10002,current_date(),'jihun');

insert into user_details(id,birth_date,name)
VALUES (10003,current_date(),'mingu');

insert into post(id,description,user_id)
VALUES(20001,'I want to learn AWS',10001);

insert into post(id,description,user_id)
VALUES(20002,'I want to learn Dev',10001);

insert into post(id,description,user_id)
VALUES(20003,'I want to learn full',10002);

insert into post(id,description,user_id)
VALUES(20004,'I want to learn Stack',10002);
