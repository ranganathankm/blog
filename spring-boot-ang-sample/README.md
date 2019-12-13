# SpringBootAngSample

This project demonstrates how to develop an Angular app with Spring-boot as the front-end. Importantly, following goals are achieved:
a) During development run spring-boot seperately with live reload
b) During development run Angular separately with live reload
c) Enables both front and back end to run separately during development allowing for fast development cycle.
d) When, project is built, should run as a single entity (spring-boot app)

## Development 

Backend:
Run 'mvn spring-boot:run' in the 'spring-boot-ang-sample-service' project. Endpoints should be accessible from `http://localhost:8080/`

UI:
Run `ng serve` in the 'spring-boot-ang-sample-ui' folder for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.
Will call the api running on `http://localhost:8080/`.


## Build

Run mvn clean install in the 'spring-boot-ang-sample' folder. The build jar will be stored in the 'spring-boot-ang-sample-service/target' directory.
