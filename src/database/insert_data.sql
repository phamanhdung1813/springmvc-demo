use springmvcfree;

insert into role(code, name)
values ('ADMIN', 'admin');
insert into role(code, name)
values ('USER', 'user');

insert into user(username, password, fullname, status)
values ('admin', '123', 'admin', 1);

insert into user(username, password, fullname, status)
values ('usera', '123', 'User A', 1);

insert into user(username, password, fullname, status)
values ('userb', '123', 'User B', 1);

INSERT INTO user_role(userid, roleid) VALUES (1, 1);
INSERT INTO user_role(userid, roleid) VALUES (2, 2);
INSERT INTO user_role(userid, roleid) VALUES (3, 2);

insert into new(content,shortdescription, title) values('content_1','des_1','title_1');
insert into new(content,shortdescription, title) values('content_2','des_2','title_2');
insert into new(content,shortdescription, title) values('content_3','des_3','title_3');
insert into new(content,shortdescription, title) values('content_4','des_4','title_4');
insert into new(content,shortdescription, title) values('content_5','des_5','title_5');
insert into new(content,shortdescription, title) values('content_6','des_6','title_6');