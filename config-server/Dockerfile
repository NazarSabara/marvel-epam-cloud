FROM openjdk:11
MAINTAINER Nazar Sabara <naz.sabara@gmail.com>
HEALTHCHECK --interval=10s --timeout=3s --retries=10 CMD curl --fail http://config-server:8898/actuator/health || exit 1
ADD ./target/config-server.jar /app/
CMD ["java", "-jar", "/app/config-server.jar"]
EXPOSE 8080