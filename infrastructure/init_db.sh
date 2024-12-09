#! /bin/bash

echo "Initializing databases: library and friends..."

# Create 'library' database
mysql -u root -p"${MYSQL_ROOT_PASSWORD}" <<EOF
CREATE DATABASE IF NOT EXISTS library;
GRANT ALL PRIVILEGES ON library.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF
echo "Created library database"

# Create 'friends' database
mysql -u root -p"${MYSQL_ROOT_PASSWORD}" <<EOF
CREATE DATABASE IF NOT EXISTS friends;
exFLUSH PRIVILEGES;
EOF
echo "Created friends database"

# Create 'lobby' database
mysql -u root -p"${MYSQL_ROOT_PASSWORD}" <<EOF
CREATE DATABASE IF NOT EXISTS lobby;
GRANT ALL PRIVILEGES ON lobby.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF
echo "Created lobby database"

# Create 'store' database
mysql -u root -p"${MYSQL_ROOT_PASSWORD}" <<EOF
CREATE DATABASE IF NOT EXISTS store;
GRANT ALL PRIVILEGES ON store.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF
echo "Created store database"

echo "Databases initialized successfully!"
