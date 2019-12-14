insert into users(username, enabled, password, role)
values ( 'miro@miro.com',1,'$2a$10$.9rRB6hrHmd3Me6Rv4dDv.omhK8QQQxvCU2uVa3jh83AY/8tgx38i','FREE');

-- insert into post(comments, description, likes, picture, title, user_id, reports)
-- values ( 200, 'test',223,'smth.jpg','some title', 1, 0);

insert into post(comments, description, likes, picture, title, user_id,reports)
values ( 200, 'test',10,'smth.jpg','some title',1,20);
-- insert into post(comments, description, likes, picture, title, user_id)
-- values ( 200, 'test',223,'','some title',1);
-- insert into post(comments, description, likes, picture, title, user_id)
-- values ( 200, 'test',223,'','some title',1);
-- insert into post(comments, description, likes, picture, title, user_id)
-- values ( 200, 'test',223,'','some title',1);
-- insert into post(comments, description, likes, picture, title, user_id)
-- values ( 200, 'test',223,'','some title',1);
-- insert into post(comments, description, likes, picture, title, user_id)
-- values ( 200, 'test',223,'','some title',1);
-- insert into post(comments, description, likes, picture, title, user_id)
-- values ( 200, 'test',223,'','some title',1);
-- insert into post(comments, description, likes, picture, title, user_id)
-- values ( 200, 'test',223,'','some title',1);

insert into comments(text, post_id, user_id)
values ( 'this is not a pigeon, REPORTED',1,1);


insert into user_likes(user_id, post_id)
values ( 1,1 );

insert into user_reports(user_id, post_id)
values ( 1,1 )
