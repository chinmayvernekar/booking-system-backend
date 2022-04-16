#FROM --platform=linux/x86-64  openjdk:17
##From openjdk:8-jre-alpine
##EXPOSE 8083
#ADD target/shramin-backend-java-sb.jar shramin-backend-java-sb.jar
#ENTRYPOINT ["java","-jar","shramin-backend-java-sb.jar"]

FROM openjdk:17
ADD target/Car-Parking-Booking-System-0.0.2-SNAPSHOT.jar Car-Parking-Booking-System-0.0.2-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Car-Parking-Booking-System-0.0.2-SNAPSHOT.jar"]