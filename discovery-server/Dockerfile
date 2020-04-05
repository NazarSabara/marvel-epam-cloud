FROM openjdk:11
MAINTAINER Nazar Sabara <naz.sabara@gmail.com>
HEALTHCHECK --interval=10s --timeout=3s --retries=10 CMD curl --fail http://discovery-server:8761/actuator/health || exit 1
ADD ./target/discovery-server.jar /app/
CMD ["java", "-jar", "/app/discovery-server.jar"]
EXPOSE 8080