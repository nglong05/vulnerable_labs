FROM tomcat:11.0.9

RUN groupadd -r app && useradd -r -g app -d /usr/local/tomcat app

RUN mkdir -p /usr/local/tomcat/uploads \
    && chown -R app:app /usr/local/tomcat/uploads \
    && chmod 770 /usr/local/tomcat/uploads

RUN chown -R app:app /usr/local/tomcat

COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

USER app

EXPOSE 8080

