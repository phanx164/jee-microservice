#FROM platform/meecrowave:0.3.1
#COPY target/ROOT.war ${INSTALL_DIR}/

FROM lx02184.starbucks.net:5000/platform/tomee7:latest
#FROM platform/tomee7:latest
#COPY tomee.xml /opt/apache-tomee/conf/
COPY target/ROOT.war /opt/apache-tomee/webapps/

