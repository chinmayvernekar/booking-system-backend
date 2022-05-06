#!/bin/bash
echo "BULDING DOCKER IMAGE"
docker build -t chinmayver/booking-backend-test .
echo "PUSHING THE IMAGE"
docker push chinmayver/booking-backend-test
echo "Sucessfull!!!!!!!!!!!!!!!"
#docker run -p 9000:9000 chinmayver/booking-backend-test
#heroku login
#
#heroku container:push booking-backend-test --app booking-slot-backend-test
#heroku container:release booking-backend-test  --app booking-slot-backend-test

echo "Deploying To Heroku"
heroku deploy:jar target/Car-Parking-Booking-System-0.0.2-SNAPSHOT.jar -a slot-booking-backend-test
echo "Deployed To Heroku Sucessfull!!!!!!!!!!!!!!!"
