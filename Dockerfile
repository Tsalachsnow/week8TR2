# define  base docker image
FROM openjdk:17
LABEL maintainer="javaguides.net"
COPY target/contact-app.jar .
ENTRYPOINT ["java", "-jar","/contact-app.jar"]
