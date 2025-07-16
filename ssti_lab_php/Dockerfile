FROM php:8.2-apache

RUN apt-get update
RUN apt-get install -y --no-install-recommends git unzip curl
RUN curl -fsSL https://getcomposer.org/installer -o /tmp/composer-setup.php
RUN php  /tmp/composer-setup.php --install-dir=/usr/local/bin --filename=composer 
WORKDIR /var/www/html
COPY composer.json ./
RUN composer install --no-dev --no-interaction --prefer-dist

COPY . .

EXPOSE 80

