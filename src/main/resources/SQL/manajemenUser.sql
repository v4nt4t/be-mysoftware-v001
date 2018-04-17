/*==============================================================*/
/* Table: muser                                                 */
/*==============================================================*/
create table muser
(
   id					varchar(36) not null,
   login                varchar(50) not null,
   password             varchar(60),
   first_name           varchar(50),
   last_name            varchar(50),
   email                varchar(100),
   image_url            varchar(256),
   activated            bit not null default 0,
   lang_key             varchar(5),
   activation_key       varchar(20),
   reset_key            varchar(20),
   created_by           varchar(50),
   created_date         timestamp,
   reset_date           timestamp,
   last_modified_by     varchar(50),
   last_modified_date   timestamp,
   primary key (id),
   UNIQUE (login),
   UNIQUE (email)
);


/*==============================================================*/
/* Table: mauthority                                            */
/*==============================================================*/
create table mauthority
(
   name                 varchar(50) not null,
   primary key (name),
   unique(name)
   
);


/*==============================================================*/
/* Table: userauthority                                         */
/*==============================================================*/
create table userauthority
(
   user_id              varchar(36) not null,
   authority_name       varchar(50) not null,
   primary key (user_id, authority_name)
);

alter table userauthority add constraint FK_reference_1 foreign key (user_id)
      references muser (id) on delete restrict on update restrict;

alter table userauthority add constraint FK_reference_2 foreign key (authority_name)
      references mauthority (name) on delete restrict on update restrict;
      
      
/*==============================================================*/
/* insert: muser                                         */
/*==============================================================*/     

INSERT INTO muser (id, login, password, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date)
VALUES ('10b59bb2-9c40-11e7-b148-00ff100e46c4', 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', 'admin@localhost', '', 1, 'id', NULL, NULL, 'system', '2017-09-13 13:53:48', '2017-09-20 15:41:27', 'system', '2018-04-11 12:11:19');

/*==============================================================*/
/* insert: mauthority                                         */
/*==============================================================*/ 
INSERT INTO mauthority (name)
VALUES ('ROLE_ADMIN');

INSERT INTO mauthority (name)
VALUES ('ROLE_USER');

/*==============================================================*/
/* insert: userauthority                                         */
/*==============================================================*/ 
INSERT INTO userauthority (user_id, authority_name)
VALUES ('10b59bb2-9c40-11e7-b148-00ff100e46c4', 'ROLE_ADMIN');

INSERT INTO userauthority (user_id, authority_name)
VALUES ('10b59bb2-9c40-11e7-b148-00ff100e46c4', 'ROLE_USER');
