FROM openjdk:21-jdk-slim

# netcat 설치
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

COPY /build/libs/capstone-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
