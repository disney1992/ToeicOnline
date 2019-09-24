use toeiconline;
create table role (
  roleid BIGINT not null primary key,
  name nvarchar(255) not null
);
create table user (
    userid bigint not null primary key auto_increment,
    roleid bigint not null,
    name nvarchar(255) not null ,
    password nvarchar(255) not null,
    createddate TIMESTAMP not null,
    constraint fk_role_user foreign key (roleid) references role (roleid)
);
create table listenguideline(
    listenguidelineid bigint not null primary key,
    title nvarchar(255) not null ,
    imge nvarchar(255) not null ,
    content text not null,
    createddate timestamp not null,
    modifieddate timestamp not null
);
create table comment (
    commentid bigint not null primary key,
    userid bigint not null,
    listenguidelineid bigint not null,
    content text not null,
    constraint fk_user_comment foreign key (userid) references user (roleid),
    constraint fk_listenguideline_comment foreign key (listenguidelineid) references listenguideline (listenguidelineid)
);