create database  ileiwedb;

create user if not exists 'school_user'@'localhost' identified by  'iwe123';
grant all privileges on ileiwedb.* to 'school_user'@'localhost';
flush privileges ;