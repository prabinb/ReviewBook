# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table User (
  emailid                   varchar(255) not null,
  fullname                  varchar(255),
  constraint pk_User primary key (emailid))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table User;

SET FOREIGN_KEY_CHECKS=1;

