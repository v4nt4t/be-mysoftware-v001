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
	objek varchar(50) not null,
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

/*==============================================================*/
/* insert: mheadermenu*/
/*==============================================================*/
INSERT INTO mheadermenu (id, kode, headermenu, allowedf, urutan)
VALUES ('3303d4a5-e4f8-49ca-bf52-b4e16129d755', 'HM001', 'AKSEPTASI', 1, 1);

INSERT INTO mheadermenu (id, kode, headermenu, allowedf, urutan)
VALUES ('8d2421e9-c88a-4020-9958-0a5fedfbb0b9', 'HM002', 'KLAIM', 1, 2);

INSERT INTO mheadermenu (id, kode, headermenu, allowedf, urutan)
VALUES ('b46bd1c2-7333-4b8f-9924-e79adf9ef68b', 'HM003', 'TAGIHAN & PEMBAYARAN', 1, 3);

INSERT INTO mheadermenu (id, kode, headermenu, allowedf, urutan)
VALUES ('d30cd2c6-19ad-4fe2-9abf-a8f139d7a3a8', 'HM004', 'ADMIN', 1, 4);

INSERT INTO mheadermenu (id, kode, headermenu, allowedf, urutan)
VALUES ('d8c0d6ce-d38f-4014-906d-2421586bd576', 'HM005', 'LAPORAN', 1, 5);

/*==============================================================*/
/* insert: mgroupmenu*/
/*==============================================================*/
INSERT INTO mgroupmenu (id, kode, groupmenu, allowedf, urutan, mheadermenu_id)
VALUES ('3455a170-4154-11e8-8c3c-002622c1aebd', 'GM001', 'REFERENSI', 1, 1, 'd30cd2c6-19ad-4fe2-9abf-a8f139d7a3a8');

INSERT INTO mgroupmenu (id, kode, groupmenu, allowedf, urutan, mheadermenu_id)
VALUES ('47312882-4154-11e8-8c3c-002622c1aebd', 'GM002', 'MANAJEMEN USER', 1, 2, 'd30cd2c6-19ad-4fe2-9abf-a8f139d7a3a8');

/*==============================================================*/
/* insert: mgroupmenu*/
/*==============================================================*/
INSERT INTO mgroupmenu (id, kode, groupmenu, allowedf, urutan, mheadermenu_id)
VALUES ('3455a170-4154-11e8-8c3c-002622c1aebd', 'GM001', 'REFERENSI', 1, 1, 'd30cd2c6-19ad-4fe2-9abf-a8f139d7a3a8');

INSERT INTO mgroupmenu (id, kode, groupmenu, allowedf, urutan, mheadermenu_id)
VALUES ('47312882-4154-11e8-8c3c-002622c1aebd', 'GM002', 'MANAJEMEN USER', 1, 2, 'd30cd2c6-19ad-4fe2-9abf-a8f139d7a3a8');

/*==============================================================*/
/* insert: mmenu*/
/*==============================================================*/
INSERT INTO mmenu (id, kode, menu,objek, allowedf, urutan, mgroupmenu_id)
VALUES ('3e86e57d-420f-11e8-8c3c-002622c1aebd', 'M0001', 'Header Menu','mheadermenu', 1, 1, '3455a170-4154-11e8-8c3c-002622c1aebd');

INSERT INTO mmenu (id, kode, menu, objek, allowedf, urutan, mgroupmenu_id)
VALUES ('bdfef586-483d-11e8-8c3c-002622c1aebd', 'M0002', 'Group Menu', 'mgroupmenu', 1, 2, '3455a170-4154-11e8-8c3c-002622c1aebd');

INSERT INTO mmenu (id, kode, menu, objek, allowedf, urutan, mgroupmenu_id)
VALUES ('be0999a1-483d-11e8-8c3c-002622c1aebd', 'M0003', 'Menu', 'mmenu', 1, 3, '3455a170-4154-11e8-8c3c-002622c1aebd');

