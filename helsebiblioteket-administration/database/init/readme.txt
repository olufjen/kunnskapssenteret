Database server: PostgresSQL Server 8.1.X (rhel5 compatible) 
Windows: http://www.postgresql.org/ftp/binary/v8.1.11/win32/


Preferred database client: 
PgAdmin3
Contained in the package linked to above.


To crate and init the database: 
0) Open the pgadmin tool
1) connect to database server (localhost / other)
2) open database "postgres"
3) click on the sql query button
4) open and execute "create_roles.sql"
5) open and execute "create_database.sql"
6) close sql query window
7) click on the node "databases" in the tree viewer, press f5
8) open database "helsebiblioteket-administration"
9) click on the sql query button for the new database
10) open and execute "create_tables.sql"
11) open and execute "init_database.sql"