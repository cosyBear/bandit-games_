#!/bin/bash
set -e

echo "Initializing databases: library and friends..."

# Create 'warehouse_db'
mysql -u root -p"${MYSQL_ROOT_PASSWORD}" <<EOF
CREATE DATABASE IF NOT EXISTS library;
GRANT ALL PRIVILEGES ON library.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF

# Create 'water_db'
mysql -u root -p"${MYSQL_ROOT_PASSWORD}" <<EOF
CREATE DATABASE IF NOT EXISTS friends;
GRANT ALL PRIVILEGES ON friends.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF



# Create 'water_db'
mysql -u root -p"${MYSQL_ROOT_PASSWORD}" <<EOF
CREATE DATABASE IF NOT EXISTS lobby;
GRANT ALL PRIVILEGES ON lobby.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF


echo "Databases initialized successfully!"
