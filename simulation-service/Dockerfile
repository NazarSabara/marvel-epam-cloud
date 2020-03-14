FROM openjdk:11
MAINTAINER Nazar Sabara <naz.sabara@gmail.com>
ADD ./target/simulation-service.jar /app/
CMD ["./wait-for-it.sh core-service:8080 -t 15"]
CMD ["java", "-jar", "/app/core-service.jar"]
EXPOSE 8082