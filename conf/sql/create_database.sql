CREATE DATABASE review_book CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'web'@'%' IDENTIFIED by 'web';
CREATE USER 'web'@'localhost' IDENTIFIED by 'web';
GRANT ALL PRIVILEGES ON review_book.* TO 'web'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON review_book.* TO 'web'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;