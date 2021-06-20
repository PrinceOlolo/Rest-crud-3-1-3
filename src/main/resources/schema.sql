drop table if exists roles;
drop table if exists users;
drop table if exists users_roles;
create table roles (id integer not null auto_increment, name varchar(255), primary key (id)) engine=MyISAM;
create table users (id integer not null auto_increment, email varchar(255), name varchar(30), last_name varchar(30), password varchar(255), primary key (id)) engine=MyISAM;
create table users_roles (user_id integer not null, role_id integer not null) engine=MyISAM;
/* пароль - admin123 */
/* пароль - user123 */
