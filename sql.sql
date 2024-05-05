create table user
(
    id       integer not null auto_increment primary key,
    username varchar(50)  default null,
    password varchar(500) default null,
    enabled  boolean not null
);

create unique index uix_users_username on user (username);

-- 插入用户数据 密码是：abc
insert into user (username, password, enabled)
values ('admin', '$2a$10$EEDfpm7tlBt7yBVcGlFKPeXlsFnnPmcW8cvRcyQrwwM1seLwZEr5K', true),
       ('Helen', '$2a$10$EEDfpm7tlBt7yBVcGlFKPeXlsFnnPmcW8cvRcyQrwwM1seLwZEr5K', true),
       ('Tom', '$2a$10$EEDfpm7tlBt7yBVcGlFKPeXlsFnnPmcW8cvRcyQrwwM1seLwZEr5K', true);