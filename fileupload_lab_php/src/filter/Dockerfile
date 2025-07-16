FROM php:7.4-apache

# common in uploading file in php
RUN docker-php-ext-install fileinfo

# enable overrides .htaccess
RUN a2enmod rewrite && sed -ri 's!AllowOverride None!AllowOverride All!g' /etc/apache2/apache2.conf

RUN mkdir -p /var/www/html/uploads/ && \
chown -R www-data:www-data /var/www/html && \
chmod 770 /var/www/html/uploads

COPY . /var/www/html/

EXPOSE 80

CMD ["apache2-foreground"]

