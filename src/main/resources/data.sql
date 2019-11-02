insert into user(username, enabled, password)
values("miro@miric.com",1,"$2a$10$VnzVnBobdvFtvhM.cBB7b.dvkGEUE/0O8wO8.22//D9X2A8TzNOR.");

insert into role(role_name)
values("ROLE_ADMIN");

insert into role(role_name)
values("ROLE_USER");


insert into user_role(id_user, role_id)
values(1,1);
