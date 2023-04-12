INSERT INTO public.gift_certificates (id, name, description, price, duration, create_date, last_update_date)
VALUES (1, 'first cert', 'first descr', 1.11, 3888000000, '2021-01-18 18:34:54.000000', '2023-03-21 18:35:07.000000'),
       (2, 'second cert', 'second des', 2.22, 3888000000, '2022-02-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (3, 'third cert', 'third des', 3.22, 3888000000, '2022-03-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (4, 'fourth cert', 'fourth des', 4.22, 3888000000, '2022-04-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (5, 'fifth cert', 'fifth des', 5.22, 3888000000, '2022-05-25 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (6, 'sixes cert', 'sixes des', 6.22, 3888000000, '2022-06-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (7, 'seventh cert', 'seventh des', 7.22, 3888000000, '2022-08-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (8, 'eights cert', 'eights des', 8.22, 3888000000, '2022-07-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (9, 'ninth cert', 'ninth des', 9.22, 3888000000, '2020-12-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (10, 'tenth cert', 'tenth des', 10.22, 3888000000, '2011-11-21 18:35:34.000000', '2022-03-21 18:35:38.000000');


INSERT INTO public.tags (id, name)
VALUES (1, 'one'),
       (2, 'two'),
       (3, 'three'),
       (4, 'four'),
       (5, 'five'),
       (6, 'six'),
       (7, 'seven'),
       (8, 'eight'),
       (9, 'nine'),
       (10, 'tan'),
       (11, 'eleven'),
       (12, 'twelve');


INSERT INTO public.certificate_tag (tag_id, certificate_id)
VALUES (9, 2),
       (10, 3),
       (4, 4),
       (5, 5),
       (5, 6),
       (6, 7),
       (7, 8),
       (8, 8),
       (1, 1),
       (2, 1),
       (3, 1);


INSERT INTO public.users (id, username)
VALUES (1, 'first user'),
       (2, 'second user'),
       (3, 'third user');

INSERT INTO public.orders (id, cost, purchase_time_stamp, user_id)
VALUES (4, 7, '2023-04-12 17:55:43.000000', 1),
       (2, 8, '2023-04-12 17:55:40.000000', 2),
       (5, 6, '2023-04-12 17:55:44.000000', 2),
       (6, 8, '2023-04-12 17:55:45.000000', 3),
       (3, 9, '2023-04-12 17:55:42.000000', 3),
       (1, 17, '2023-04-12 17:55:39.000000', 1);

INSERT INTO public.orders_gift_certificates (order_id, certificatelist_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6);



INSERT INTO public.users_orders (user_id, order_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6);

