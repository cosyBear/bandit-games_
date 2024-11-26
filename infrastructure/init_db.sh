#!/bin/bash
set -e

echo "Running init_db.sh to create library and friends..."

# Create first database 'warehouse_db'
mysql -u root -p${MYSQL_ROOT_PASSWORD} << EOF
CREATE DATABASE IF NOT EXISTS warehouse_db;
GRANT ALL PRIVILEGES ON library.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF

# Create second database 'water_db'
mysql -u root -p${MYSQL_ROOT_PASSWORD} << EOF
CREATE DATABASE IF NOT EXISTS water_db;
GRANT ALL PRIVILEGES ON friends.* TO '${MYSQL_USER}'@'%';
FLUSH PRIVILEGES;
EOF

echo "Finished running init_db.sh"
