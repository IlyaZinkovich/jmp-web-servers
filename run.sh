#!/bin/bash

# Start Apache in background
source /etc/apache2/envvars
apache2 -k start

# Create Tomcat Admin user
if [ ! -f /.tomcat_admin_created ]; then
    /create_tomcat_admin_user.sh
fi

# Start Tomcat in foreground
exec ${CATALINA_HOME}/bin/catalina.sh run
