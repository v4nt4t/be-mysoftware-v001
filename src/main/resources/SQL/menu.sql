/*==============================================================*/
/* Table: mheadermenu                                                 */
/*==============================================================*/

create table mheadermenu(
	id varchar(36) not null,
	kode varchar(5) not null,
	headermenu varchar(50) not null,
	allowedf boolean not null,
	urutan int not null,
	primary key (id),
	unique(kode)
);

/*==============================================================*/
/* Table: mgroupmenu                                                 */
/*==============================================================*/

create table mgroupmenu(
	id varchar(36) not null,
	kode varchar(5) not null,
	groupmenu varchar(50) not null,
	allowedf boolean not null,
	urutan int not null,
	mheadermenu_id varchar(36) not null,
	primary key (id),
	foreign key (mheadermenu_id) references mheadermenu(id),
	unique (kode)
);


/*==============================================================*/
/* Table: mmenu                                                 */
/*==============================================================*/

create table mmenu(
	id varchar(36) not null,
	kode varchar(5) not null,
	menu varchar(50) not null,
	allowedf boolean not null,
	urutan int not null,
	mgroupmenu_id varchar(36) not null,
	primary key (id),
	foreign key (mgroupmenu_id) references mgroupmenu(id),
	unique (kode)
);

/*==============================================================*/
/* Table: usermenu                                                 */
/*==============================================================*/

create table usermenu(
	muser_id varchar(36) not null,
	mmenu_id varchar(36) not null,
	primary key (muser_id, mmenu_id),
	foreign key (muser_id) references muser(id),
	foreign key (mmenu_id) references mmenu(id),
);