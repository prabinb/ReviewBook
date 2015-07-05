# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table posted_reviews_interest (
  review_id                 integer,
  email_id                  varchar(255),
  helpful                   tinyint(1) default 0,
  constraint uq_posted_reviews_interest_1 unique (email_id,review_id))
;

create table user (
  email_id                  varchar(255) not null,
  full_name                 varchar(255),
  constraint pk_user primary key (email_id))
;

create table user_reviews (
  review_id                 integer auto_increment not null,
  email_id                  varchar(255),
  product_name              varchar(255),
  review_content            TEXT,
  review_title              varchar(255),
  is_recommended            tinyint(1) default 0,
  image_name                varchar(100) not null,
  image_data                varbinary(100000) not null,
  constraint uq_user_reviews_1 unique (email_id,product_name),
  constraint pk_user_reviews primary key (review_id))
;

alter table user_reviews add constraint fk_user_reviews_user_1 foreign key (email_id) references user (email_id) on delete restrict on update restrict;
create index ix_user_reviews_user_1 on user_reviews (email_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table posted_reviews_interest;

drop table user;

drop table user_reviews;

SET FOREIGN_KEY_CHECKS=1;

