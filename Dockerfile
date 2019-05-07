FROM java:8
MAINTAINER bupt.dawsonlee1790
ADD build/libs/bupt-graduation-design-user-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT java -jar app.jar