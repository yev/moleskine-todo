CREATE TABLE public.todo (
                             id int4 NOT NULL,
                             title varchar(255) NULL,
                             is_complete bool NULL,
                             CONSTRAINT todo_pkey PRIMARY KEY (id)
);
