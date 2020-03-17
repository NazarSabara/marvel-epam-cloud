FROM openjdk:11
MAINTAINER Nazar Sabara <naz.sabara@gmail.com>
ADD ./target/core-service.jar /app/
CMD ["./wait-for-it.sh hero-db:5432 -t 15"]
CMD ["./wait-for-it.sh integration-service:8081 -t 15"]
CMD ["java", "-jar", "/app/core-service.jar"]
EXPOSE 8080