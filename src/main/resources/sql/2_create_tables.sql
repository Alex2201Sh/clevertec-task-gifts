create table public.gift_certificates
(
    id               bigserial
        constraint gift_certificates_pk
            primary key,
    name             varchar(32),
    description      varchar(128),
    price            double precision,
    duration         interval,
    create_date      timestamp,
    last_update_date timestamp,
    constraint gift_certificates_pk2
        unique (name, description)
);


create table if not exists public.tags
(
    id               bigserial
        constraint tags_pk
            primary key,
    name             varchar(32)
);

create table if not exists public.certificate_tag
(
    certificate_id integer,
    tag_id         integer,
    constraint certificate_tag_pk
        primary key (certificate_id, tag_id)
);



