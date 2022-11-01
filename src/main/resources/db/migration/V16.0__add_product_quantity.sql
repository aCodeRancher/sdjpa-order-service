alter table product
    add column version integer;

alter table product
    add column quantity_on_hand int;

update  product
    set version = 0 where version is null;

update product
   set product.quantity_on_hand = 10;

