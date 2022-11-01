alter table order_line
    add column version integer;

update order_line
     set version = 0 where version = null;