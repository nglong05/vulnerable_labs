#!/bin/bash

sudo -u postgres psql -d task2 <<EOF
SET ROLE task2_user;
\i init.sql;
EOF

node backend/app.js