#!/bin/sh

# Load database connection properties
. ../config/db.properties

# SQL files
create_db=../sql/create_db.sql
create_tables=../sql/create_tables.sql

# Create database
echo "\nCreating database ..." 
mysql -h $db_url -P $db_port --user=$db_user --password=$db_pass --verbose < $create_db

# Create tables
echo "\nCreating tables ..." 
mysql imagedb -h $db_url -P $db_port --user=$db_user --password=$db_pass --verbose < $create_tables
 
