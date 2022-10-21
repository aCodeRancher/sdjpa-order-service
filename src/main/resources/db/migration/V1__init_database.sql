drop table if exists customer;
drop table if exists order_header cascade;

create table customer
(
    id bigint not null auto_increment primary key,
    phone varchar(10),
    name varchar(50),
    email varchar(20),
    created_date timestamp,
    last_modified_date timestamp,
    address varchar(30),
    city varchar(30),
    state varchar(30),
    zip_code varchar(30)

) engine = InnoDB;

create table order_header
(
    id        bigint not null auto_increment primary key,
    customer_id      bigint,
    constraint order_header_fk FOREIGN KEY (customer_id) references customer(id)
) engine = InnoDB;

