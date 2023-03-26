INSERT INTO public.gift_certificates (id, name, description, price, duration, create_date, last_update_date)
VALUES (1, 'first cert', 'first descr', 1.11, 'P90D', '2021-01-18 18:34:54.000000', '2023-03-21 18:35:07.000000'),
       (2, 'second cert', 'second des', 2.22, 'P43D', '2022-02-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (3, 'third cert', 'third des', 3.22, 'P90D', '2022-03-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (4, 'fourth cert', 'fourth des', 4.22, 'P43D', '2022-04-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (5, 'fifth cert', 'fifth des', 5.22, 'P43D', '2022-05-25 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (6, 'sixes cert', 'sixes des', 6.22, 'P90D', '2022-06-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (7, 'seventh cert', 'seventh des', 7.22, 'P65D', '2022-08-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (8, 'eights cert', 'eights des', 8.22, 'P33D', '2022-07-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (9, 'ninth cert', 'ninth des', 9.22, 'P91D', '2020-12-21 18:35:34.000000', '2022-03-21 18:35:38.000000'),
       (10, 'tenth cert', 'tenth des', 10.22, 'P42D', '2011-11-21 18:35:34.000000', '2022-03-21 18:35:38.000000')


INSERT INTO public.tags (id, name)
VALUES (1, 'onessss'),
       (2, 'two'),
       (3, 'three'),
       (4, 'four'),
       (5, 'five'),
       (6, 'six'),
       (7, 'seven'),
       (8, 'des'),
       (9, 'grdyu'),
       (10, 'ertyj'),
       (11, 'eleven'),
       (12, 'twelve');


INSERT INTO public.certificate_tag (certificate_id, tag_id)
VALUES (1, 2),
       (2, 2),
       (3, 2),
       (1, 12),
       (3, 4),
       (7, 5),
       (10, 6),
       (7, 7),
       (7, 8);

