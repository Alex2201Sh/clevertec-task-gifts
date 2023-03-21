create table public.gift_certificates
(
    id               bigserial
        constraint gift_certificates_pk
            primary key,
    name             varchar(32),
    description      varchar(128),
    price            double precision,
    duration         integer,
    create_date      timestamp,
    last_update_date timestamp
);

alter table public.gift_certificates
    owner to postgres;

