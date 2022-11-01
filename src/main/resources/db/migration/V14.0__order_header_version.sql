alter table order_header
    add column version integer;

update order_header
    set version = 0 where version = null;