#!/bin/bash
set -e

echo "Creating additional databases: 'library' and 'friends'..."

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d postgres <<-EOSQL
    CREATE DATABASE library;
    CREATE DATABASE friends;
    GRANT ALL PRIVILEGES ON DATABASE library TO $POSTGRES_USER;
    GRANT ALL PRIVILEGES ON DATABASE friends TO $POSTGRES_USER;
EOSQL

echo "Databases 'library' and 'friends' created successfully."
