#!/bin/bash
set -e

echo "Running init_db.sh to create library and friends databases..."

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE library;
    CREATE DATABASE friends;
    GRANT ALL PRIVILEGES ON DATABASE library TO $POSTGRES_USER;
    GRANT ALL PRIVILEGES ON DATABASE friends TO $POSTGRES_USER;
EOSQL

echo "Finished running init_db.sh"
