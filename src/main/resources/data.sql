insert into users (email, name, last_name, password)
values
('admin@mail.com', 'Семён', 'Фомичев' , '$2y$12$k/GhE.A6dgHIYUqW3LUA6emWoKZCxlUJpMXs94nwI0EknywMlfUgW'),
('user@mail.com', 'Поликард', 'Дробидонтович' , '$2y$12$H2HGGUK4uI64co3Yv08Yhe6c11JEP0OH0gKcU3BunlbU8VdItKLOC'),
('1', 'Prince', 'Ololo' , '$2y$12$GiP4oZ7gDuRPAzXS9AVsiO2cG4NAob0UpWbsKDQzD4CmTUaXzJ0gC');
insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users_roles (user_id, role_id)
values
(1,2),
(2,1),
(3,1),
(3,2);
