#!/bin/bash
#echo "BULDING DOCKER IMAGE"
#docker build -t chinmayver/booking-backend-test .
#docker push chinmayver/booking-backend-test
#docker run -p 9000:9000 chinmayver/booking-backend-test
#heroku login
#
#heroku container:push booking-backend-test
#heroku container:release booking-backend-test

heroku deploy:jar target/Car-Parking-Booking-System-0.0.2-SNAPSHOT.jar -a booking-slot-backend-test
