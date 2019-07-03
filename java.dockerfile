FROM java:openjdk-8-jre
COPY ./build/libs /usr/src/myapp
WORKDIR /usr/src/myapp
CMD java -jar demo-0.0.1-SNAPSHOT.jar