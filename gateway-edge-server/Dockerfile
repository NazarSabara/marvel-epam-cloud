FROM openjdk:11
MAINTAINER Nazar Sabara <naz.sabara@gmail.com>
ADD ./target/gateway-edge-server.jar /app/
CMD ["./wait-for-it.sh discovery-server:8761 -t 15"]
CMD ["java", "-jar", "/app/gateway-edge-server.jar"]
EXPOSE 8080