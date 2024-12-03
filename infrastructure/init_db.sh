#!/usr/bin/env bash

set -e  # Exit immediately on error

echo "Initializing databases: library and friends..."

# Create 'library' database
mysql -u root -p"${MYSQL_ROOT_PASSWORD}" <<EOF
CREATE DATABASE IF NOT EXISTS library;
GRANT ALL PRIVILEGES ON library.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF

# Create 'friends' database
mysql -u root -p"${MYSQL_ROOT_PASSWORD}" <<EOF
CREATE DATABASE IF NOT EXISTS friends;
GRANT ALL PRIVILEGES ON friends.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF


# Create 'friends' database
mysql -u root -p"${MYSQL_ROOT_PASSWORD}" <<EOF
CREATE DATABASE IF NOT EXISTS lobby;
GRANT ALL PRIVILEGES ON lobby.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF



mysql -u root -p"${MYSQL_ROOT_PASSWORD}" <<EOF
CREATE DATABASE IF NOT EXISTS store;
GRANT ALL PRIVILEGES ON store.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF


echo "Databases initialized successfully!"
