FROM openjdk:11
MAINTAINER Nazar Sabara <naz.sabara@gmail.com>
HEALTHCHECK --interval=10s --timeout=3s --retries=10 CMD curl --fail http://integration-service:8091/actuator/health || exit 1
ADD ./target/integration-service.jar /app/
CMD ["java", "-jar", "/app/integration-service.jar"]
EXPOSE 8081