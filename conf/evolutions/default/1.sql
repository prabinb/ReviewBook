# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  email_id                  varchar(255) not null,
  full_name                 varchar(255),
  constraint pk_user primary key (email_id))
;

create table user_reviews (
  email_id                  varchar(255),
  product_name              varchar(255),
  review_content            varchar(255),
  review_title              varchar(255),
  constraint uq_user_reviews_1 unique (email_id,product_name))
;

alter table user_reviews add constraint fk_user_reviews_user_1 foreign key (email_id) references user (email_id) on delete restrict on update restrict;
create index ix_user_reviews_user_1 on user_reviews (email_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table user;

drop table user_reviews;

SET FOREIGN_KEY_CHECKS=1;

