TRUNCATE public.account CASCADE;
TRUNCATE public.account_assignment CASCADE;
TRUNCATE public.assignment CASCADE;
TRUNCATE public.background CASCADE;
TRUNCATE public.role CASCADE;
TRUNCATE public.account_role CASCADE;

-- Create Accounts
INSERT INTO public.account (id, email_address)
VALUES ('11079105-8be2-4ab4-ab14-a39efa70f447', 'test1@gmail.com');

INSERT INTO public.account (id, email_address)
VALUES ('21079105-8be2-4ab4-ab14-a39efa70f447', 'test2@gmail.com');

-- Add Background to accounts
INSERT INTO public.background (id, bootcamp, first_name, github_user, last_name, account_id)
VALUES ('11079105-8bf2-4ab4-ab14-a39efa70f447', 'JAVA', 'John', 'johndoe', 'Doe',
        '11079105-8be2-4ab4-ab14-a39efa70f447');

INSERT INTO public.background (id, bootcamp, first_name, github_user, last_name, account_id)
VALUES ('21079105-8bf2-4ab4-ab14-a39efa70f447', 'JAVA', 'Jane', 'janelee', 'Lee',
        '21079105-8be2-4ab4-ab14-a39efa70f447');


-- Create roles
INSERT INTO public.role (id, name)
VALUES (1, 'developer');

INSERT INTO public.role (id, name)
VALUES (2, 'instructor');

-- Assign developer role to an account
INSERT INTO public.account_role (id, account_id, role_id)
VALUES ('12279105-8be2-4ab4-ab14-a39efa70f447', '11079105-8be2-4ab4-ab14-a39efa70f447', 1);

INSERT INTO public.account_role (id, account_id, role_id)
VALUES ('22279105-8be2-4ab4-ab14-a39efa70f447', '21079105-8be2-4ab4-ab14-a39efa70f447', 1);



-- Create Assignments
INSERT INTO public.assignment (id, category, description, score, title)
VALUES ('110791aa-8be2-4ab4-ab14-a39efa70f447', 'BACKEND', 'Java', 0, 'Springboot');

INSERT INTO public.assignment (id, category, description, score, title)
VALUES ('210791aa-8be2-4ab4-ab14-a39efa70f447', 'BACKEND', 'C#', 0, '.Net');

INSERT INTO public.assignment (id, category, description, score, title)
VALUES ('310791aa-8be2-4ab4-ab14-a39efa70f447', 'FRONTEND', 'JavaScript', 0, 'React');

-- Assign Assignment to an Account with Developer role
INSERT INTO public.account_assignment (id, score, account_id, assignment_id)
VALUES ('110791aa-8be2-4ab4-ab14-aa9efa70f447', 30, '11079105-8be2-4ab4-ab14-a39efa70f447',
        '110791aa-8be2-4ab4-ab14-a39efa70f447');

INSERT INTO public.account_assignment (id, score, account_id, assignment_id)
VALUES ('210791aa-8be2-4ab4-ab14-aa9efa70f447', 25, '11079105-8be2-4ab4-ab14-a39efa70f447',
        '310791aa-8be2-4ab4-ab14-a39efa70f447');


