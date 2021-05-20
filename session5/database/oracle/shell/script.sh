#!/bin/bash
$ORACLE_HOME/bin/sqlplus sys/oracle@//localhost:1521/XE as sysdba << EOF
@/home/workshop_ms.sql oracle oracle hrpw SYSTEM TEMP /home/log/ localhost:1521/XE
quit
EOF
