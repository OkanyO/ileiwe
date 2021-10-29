set foreign_key_checks =0;

truncate table learning_party;
truncate table authority;
truncate table instructor;

insert into learning_party(`id`, `email`,`password`,`enabled` )
values(110, 'nuelteacher@gmail.com','okany123', false),
    (111, 'nuel@gmail.com','okany123',false),
   (112, 'Onyeka@gmail.com','okany123',false);

set foreign_key_checks =1;