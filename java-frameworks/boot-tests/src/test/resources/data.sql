INSERT INTO employees
VALUES (
           100,
           'Steven',
           'King',
           'SKING',
           '515.123.4567',
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           'AD_PRES',
           24000,
           NULL,
           NULL,
           90
       );

INSERT INTO employees
VALUES (
           101,
           'Neena',
           'Kochhar',
           'NKOCHHAR',
           '515.123.4568',
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           'AD_VP',
           17000,
           NULL,
           100,
           90
       );

INSERT INTO employees
VALUES (
           102,
           'Lex',
           'De Haan',
           'LDEHAAN',
           '515.123.4569',
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           'AD_VP',
           17000,
           NULL,
           100,
           90
       );

INSERT INTO employees
VALUES (
           103,
           'Alexander',
           'Hunold',
           'AHUNOLD',
           '590.423.4567',
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           'IT_PROG',
           9000,
           NULL,
           102,
           60
       );

INSERT INTO employees
VALUES (
           104,
           'Bruce',
           'Ernst',
           'BERNST',
           '590.423.4568',
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           'IT_PROG',
           6000,
           NULL,
           103,
           60
       );

INSERT INTO employees
VALUES (
           105,
           'David',
           'Austin',
           'DAUSTIN',
           '590.423.4569',
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           'IT_PROG',
           4800,
           NULL,
           103,
           60
       );

INSERT INTO employees
VALUES (
           106,
           'Valli',
           'Pataballa',
           'VPATABAL',
           '590.423.4560',
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           'IT_PROG',
           4800,
           NULL,
           103,
           60
       );

INSERT INTO employees
VALUES (
           107,
           'Diana',
           'Lorentz',
           'DLORENTZ',
           '590.423.5567',
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           'IT_PROG',
           4200,
           NULL,
           103,
           60
       );

INSERT INTO employees
VALUES (
           108,
           'Nancy',
           'Greenberg',
           'NGREENBE',
           '515.124.4569',
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           'FI_MGR',
           12000,
           NULL,
           101,
           100
       );

INSERT INTO employees
VALUES (
           109,
           'Daniel',
           'Faviet',
           'DFAVIET',
           '515.124.4169',
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),
           'FI_ACCOUNT',
           9000,
           NULL,
           108,
           100
       );

INSERT INTO jobs
VALUES (
           'AD_PRES',
           'President',
           20000,
           40000
       );

INSERT INTO jobs
VALUES (
           'AD_VP',
           'Administration Vice President',
           15000,
           30000
       );

INSERT INTO jobs
VALUES (
           'AD_ASST',
           'Administration Assistant',
           3000,
           6000
       );

INSERT INTO jobs
VALUES (
           'FI_MGR',
           'Finance Manager',
           8200,
           16000
       );

INSERT INTO jobs
VALUES (
           'FI_ACCOUNT',
           'Accountant',
           4200,
           9000
       );



INSERT INTO jobs
VALUES (
           'IT_PROG',
           'Programmer',
           4000,
           10000
       );

INSERT INTO departments
VALUES (
           60,
           'IT',
           103,
           1400
       );



INSERT INTO departments
VALUES (
           90,
           'Executive',
           100,
           1700
       );

INSERT INTO departments
VALUES (
           100,
           'Finance',
           108,
           1700
       );


COMMIT;