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
