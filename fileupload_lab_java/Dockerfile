FROM tomcat:11.0.9

RUN mkdir -p /usr/local/tomcat/uploads && \
    chmod 777 /usr/local/tomcat/uploads

COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

