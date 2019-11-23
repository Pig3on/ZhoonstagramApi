insert into users(username, enabled, password, role)
values ( 'miro@miro.com',1,'$2a$10$.9rRB6hrHmd3Me6Rv4dDv.omhK8QQQxvCU2uVa3jh83AY/8tgx38i','FREE');

insert into post(comments, description, likes, picture, title, user_id)
values ( 200, 'test',223,'','some title',1);
insert into post(comments, description, likes, picture, title, user_id)
values ( 200, 'test',223,'','some title',1);
insert into post(comments, description, likes, picture, title, user_id)
values ( 200, 'test',223,'','some title',1);
insert into post(comments, description, likes, picture, title, user_id)
values ( 200, 'test',223,'','some title',1);
insert into post(comments, description, likes, picture, title, user_id)
values ( 200, 'test',223,'','some title',1);
insert into post(comments, description, likes, picture, title, user_id)
values ( 200, 'test',223,'','some title',1);
insert into post(comments, description, likes, picture, title, user_id)
values ( 200, 'test',223,'','some title',1);
insert into post(comments, description, likes, picture, title, user_id)
values ( 200, 'test',223,'','some title',1);

insert into comment(text, post_id, user_id)
values ( 'this is some stuff',1,1);


insert into user_likes(user_id, post_id)
values ( 1,1 )