SET FEEDBACK 1
SET NUMWIDTH 10
SET LINESIZE 80
SET TRIMSPOOL ON
SET TAB OFF
SET PAGESIZE 999
SET ECHO OFF
SET CONCAT '.'
SET SHOWMODE OFF

PROMPT 
PROMPT specify password for SYSTEM as parameter 1:
DEFINE password_system     = &1
PROMPT 
PROMPT specify password for SYS as parameter 2:
DEFINE password_sys        = &2
PROMPT 
PROMPT specify password for HR as parameter 3:
DEFINE password_hr         = &3
PROMPT 
PROMPT specify default tablespace as parameter 4:
DEFINE default_ts          = &4
PROMPT
PROMPT specify temporary tablespace as parameter 5:
DEFINE temp_ts             = &5
PROMPT 
PROMPT specify log file directory (including trailing delimiter) as parameter 6:
DEFINE logfile_dir         = &6
PROMPT 
PROMPT specify connect string as parameter 7:
DEFINE connect_string     = &7
PROMPT
PROMPT Sample Schemas are being created ...
PROMPT
DEFINE vrs = v3

host mkdir &&logfile_dir

CONNECT system/&&password_system@&&connect_string

DROP USER hr CASCADE;

CONNECT system/&&password_system@&&connect_string
SET SHOWMODE OFF

@/home/hr_main.sql &&password_hr &&default_ts &&temp_ts &&password_sys &&logfile_dir &&connect_string

CONNECT system/&&password_system@&&connect_string

SPOOL OFF

DEFINE veri_spool = &&logfile_dir.mkverify_&vrs..log

@/home/mkverify &&password_system &veri_spool &&connect_string
