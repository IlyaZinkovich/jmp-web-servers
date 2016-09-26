FROM java:8

# Apache HTTP

RUN apt-get update && \
    apt-get install -yq \
        apache2 \
        libapache2-mod-jk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

EXPOSE 80

# Apache Tomcat

RUN apt-get update && \
    apt-get install -yq --no-install-recommends \
    	wget \
    	pwgen \
    	ca-certificates && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

ENV TOMCAT_MAJOR_VERSION 8
ENV TOMCAT_MINOR_VERSION 8.5.5
ENV CATALINA_HOME /tomcat

# INSTALL TOMCAT
RUN wget -q https://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_MAJOR_VERSION}/v${TOMCAT_MINOR_VERSION}/bin/apache-tomcat-${TOMCAT_MINOR_VERSION}.tar.gz && \
    wget -qO- https://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_MAJOR_VERSION}/v${TOMCAT_MINOR_VERSION}/bin/apache-tomcat-${TOMCAT_MINOR_VERSION}.tar.gz.md5 | md5sum -c - && \
    tar zxf apache-tomcat-*.tar.gz && \
    rm apache-tomcat-*.tar.gz && \
    mv apache-tomcat* tomcat

COPY ./jmp-spring-core/target/spring-core.war /tomcat/webapps/
COPY ./static /var/www/html

#Set tomcat_home and java_home in workers.properties 
RUN sed -i "s/workers.tomcat_home.*/workers.tomcat_home=\/tomcat/g" /etc/libapache2-mod-jk/workers.properties
RUN sed -i "s/workers.java_home.*/workers.java_home=\/usr\/lib\/jvm\/java-8-openjdk-amd64/g" /etc/libapache2-mod-jk/workers.properties

RUN sed -i '1a\
    JkMount \/spring-core\/* ajp13_worker' /etc/apache2/sites-available/000-default.conf
#Set custom document root / default is var/www/html
#RUN sed -i "s@DocumentRoot.*@DocumentRoot /tomcat/webapps@g" /etc/apache2/sites-#available/000-default.conf

ADD create_tomcat_admin_user.sh /create_tomcat_admin_user.sh
ADD run.sh /run.sh

RUN apt-get update && apt-get install -y dos2unix

RUN dos2unix /run.sh && dos2unix /create_tomcat_admin_user.sh && apt-get --purge remove -y dos2unix && rm -rf /var/lib/apt/lists/*

RUN chmod +x /*.sh
CMD ["/run.sh"]
