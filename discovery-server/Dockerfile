FROM openjdk:11
MAINTAINER Nazar Sabara <naz.sabara@gmail.com>
ADD ./target/discovery-server.jar /app/
CMD ["./wait-for-it.sh config-server:8888 -t 15"]
CMD ["java", "-jar", "/app/discovery-server.jar"]
EXPOSE 8080