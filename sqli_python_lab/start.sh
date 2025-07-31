#!/bin/bash
sudo mysql <<'EOF'
CREATE DATABASE IF NOT EXISTS task2;
CREATE USER IF NOT EXISTS 'task2_user'@'localhost' IDENTIFIED WITH mysql_native_password BY 'task2_password';
GRANT ALL PRIVILEGES ON task2.* TO 'task2_user'@'localhost';
FLUSH PRIVILEGES;
EOF

mysql -u task2_user -ptask2_password task2 < init.sql

