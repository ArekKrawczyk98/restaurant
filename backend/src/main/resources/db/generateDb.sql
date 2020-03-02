--
-- PostgreSQL database dump
--

-- Dumped from database version 11.7 (Ubuntu 11.7-1.pgdg18.04+1)
-- Dumped by pg_dump version 12.2 (Ubuntu 12.2-1.pgdg18.04+1)

-- Started on 2020-02-24 22:22:51 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- TOC entry 198 (class 1259 OID 24591)
-- Name: bills; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.bills (
    id integer NOT NULL,
    date date,
    money_paid numeric(10,2),
    table_number smallint NOT NULL
);


ALTER TABLE public.bills OWNER TO test;

--
-- TOC entry 197 (class 1259 OID 24589)
-- Name: bills_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

ALTER TABLE public.bills_id_seq OWNER TO test;


ALTER SEQUENCE public.bills_id_seq OWNED BY public.bills.id;

--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 197
-- Name: bills_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

CREATE SEQUENCE public.bills_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 201 (class 1259 OID 24626)
-- Name: invoices; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.invoices (
    id character varying(64) NOT NULL,
    date date,
    money_paid numeric(5,2),
    money_given numeric(5,2)
);


ALTER TABLE public.invoices OWNER TO test;

--
-- TOC entry 196 (class 1259 OID 16395)
-- Name: orders; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    bill_id integer
);


ALTER TABLE public.orders OWNER TO test;

--
-- TOC entry 202 (class 1259 OID 32818)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO test;

--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 202
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- TOC entry 203 (class 1259 OID 32823)
-- Name: orders_products; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.orders_products (
    order_id integer,
    product_id integer
);


ALTER TABLE public.orders_products OWNER TO test;

--
-- TOC entry 200 (class 1259 OID 24603)
-- Name: products; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying NOT NULL,
    cost numeric(4,2) NOT NULL
);


ALTER TABLE public.products OWNER TO test;

--
-- TOC entry 199 (class 1259 OID 24601)
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO test;

--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 199
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- TOC entry 2823 (class 2604 OID 24594)
-- Name: bills id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.bills ALTER COLUMN id SET DEFAULT nextval('public.bills_id_seq'::regclass);


--
-- TOC entry 2822 (class 2604 OID 32820)
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- TOC entry 2824 (class 2604 OID 24606)
-- Name: products id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- TOC entry 2957 (class 0 OID 24591)
-- Dependencies: 198
-- Data for Name: bills; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.bills (id, date, money_paid, table_number) FROM stdin;
\.


--
-- TOC entry 2960 (class 0 OID 24626)
-- Dependencies: 201
-- Data for Name: invoices; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.invoices (id, date, money_paid, money_given) FROM stdin;
6b732e11-d9b6-4d8d-a077-9f29cffe9ac9	2020-02-03	62.00	62.00
d7c260ef-cb75-4ee9-aba4-f58b25b4a450	2020-02-03	62.00	64.00
d96fa9d4-616a-4de9-8feb-1ee2f1d28c55	2020-02-20	0.00	0.00
93cefbf0-fcaf-4ce5-aecd-55938f959ea3	2020-02-21	0.00	0.00
97baa993-7e37-4498-a516-3f4ceeda2e2c	2020-02-21	0.00	5.00
1938ca95-0894-4d84-8e79-8cecfad69fae	2020-02-24	0.00	0.00
974ea770-bd12-4e28-afb4-b6c94d465145	2020-02-24	9.00	9.00
a0729f45-0aa1-4b03-a431-9e5ba9390290	2020-02-24	9.00	9.00
de7d6ce7-8ffe-453c-98fa-8253b5413b01	2020-02-24	92.00	92.00
a55bc609-4c38-4be6-889e-10d69e495bd1	2020-02-24	80.00	80.00
601f61c3-162e-4b5e-81c9-fc56b379dbae	2020-02-24	18.00	18.00
c58ea361-5a9c-4e60-a07d-d44a47bb57c1	2020-02-24	23.00	24.00
580c4088-412c-45e0-b4d4-bb637c2878f1	2020-02-24	0.00	1.00
0b128f21-b146-4e94-80e1-9b7b01309b02	2020-02-24	20.00	21.00
\.


--
-- TOC entry 2955 (class 0 OID 16395)
-- Dependencies: 196
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.orders (id, bill_id) FROM stdin;
\.


--
-- TOC entry 2962 (class 0 OID 32823)
-- Dependencies: 203
-- Data for Name: orders_products; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.orders_products (order_id, product_id) FROM stdin;
\.


--
-- TOC entry 2959 (class 0 OID 24603)
-- Dependencies: 200
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: test
--

insert into products values (1,'Vodka 30ml',9.00);
insert into products values (2,'Jack Daniels 30ml',10.00);
insert into products values (3,'Light Beer'	,6.00);
insert into products values (4,	'Dark Beer',7.00);
insert into products values (5,'Wine',11.00);
insert into products values (6,'Ice creams',11.00);
insert into products values (7,'Hot tea',6.00);
insert into products values (8,'Latte',7.00);
insert into products values (9,'Espresso',6.00);
insert into products values (10,'Iced Coffee',8.00);
insert into products values (11,'Grilled supreme chicken breast',20.00);
insert into products values (12,'Grilled beef steak',27.00);
insert into products values (41,'Sasuage',11.00);



--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 197
-- Name: bills_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.bills_id_seq', 114, true);


--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 202
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.orders_id_seq', 63, true);


--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 199
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.products_id_seq', 41, true);


--
-- TOC entry 2828 (class 2606 OID 24596)
-- Name: bills bills_pkey; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.bills
    ADD CONSTRAINT bills_pkey PRIMARY KEY (id);


--
-- TOC entry 2832 (class 2606 OID 24630)
-- Name: invoices invoices_pkey; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.invoices
    ADD CONSTRAINT invoices_pkey PRIMARY KEY (id);


--
-- TOC entry 2826 (class 2606 OID 32822)
-- Name: orders orders_pk; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (id);


--
-- TOC entry 2830 (class 2606 OID 24608)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- TOC entry 2833 (class 2606 OID 24645)
-- Name: orders orders_bills_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_bills_id_fk FOREIGN KEY (bill_id) REFERENCES public.bills(id);


--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT USAGE ON SCHEMA public TO test;


-- Completed on 2020-02-24 22:22:51 CET

--
-- PostgreSQL database dump complete
--

