SET ECHO OFF
SET VERIFY OFF

PROMPT 
PROMPT specify password for HR as parameter 1:
DEFINE pass     = &1
PROMPT 
PROMPT specify default tablespeace for HR as parameter 2:
DEFINE tbs      = &2
PROMPT 
PROMPT specify temporary tablespace for HR as parameter 3:
DEFINE ttbs     = &3
PROMPT 
PROMPT specify password for SYS as parameter 4:
DEFINE pass_sys = &4
PROMPT 
PROMPT specify log path as parameter 5:
DEFINE log_path = &5
PROMPT
PROMPT specify connect string as parameter 6:
DEFINE connect_string     = &6
PROMPT

DEFINE spool_file = &log_path.hr_main.log
SPOOL &spool_file

DROP USER hr CASCADE;

CREATE USER hr IDENTIFIED BY &pass;

ALTER USER hr DEFAULT TABLESPACE &tbs
              QUOTA UNLIMITED ON &tbs;

ALTER USER hr TEMPORARY TABLESPACE &ttbs;

GRANT CREATE SESSION, CREATE VIEW, ALTER SESSION, CREATE SEQUENCE TO hr;
GRANT CREATE SYNONYM, CREATE DATABASE LINK, RESOURCE , UNLIMITED TABLESPACE TO hr;

CONNECT sys/&pass_sys@&connect_string AS SYSDBA;
GRANT execute ON sys.dbms_stats TO hr;

CONNECT hr/&pass@&connect_string
ALTER SESSION SET NLS_LANGUAGE=American;
ALTER SESSION SET NLS_TERRITORY=America;

@/home/hr_cre
@/home/hr_popul
@/home/hr_idx
@/home/hr_comnt
@/home/hr_analz

spool off
