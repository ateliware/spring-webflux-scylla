FROM openjdk:11.0.3-jdk
RUN apt-get update && apt-get install bash
COPY ./build/libs /usr/src/myapp
WORKDIR /usr/src/myapp
CMD java -jar demo-0.0.1-SNAPSHOT.jar