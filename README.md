# BookMyTrain

Instructions -

1 - Start Eureka-Server Microservice first.

2 - Start ActiveMq server using command from the bin folder in the ActiveMq folder. > activemq start

3 - Start all remaining miceroservices in any order.

4 - Open swagger in the browser -> http://localhost:8011/swagger-ui.html#/

5 - Open Sonar Qube in the browser -> http://localhost:9999/projects. [Note - > I have update sonar.web.url =9999 so my Sonar application running in the port 9999.]

6 - Use MySql database Workbench and create databases for Users, Insurance, Payment, Ticket and Train.
          use sql command -> create database <database name>

7 - Table will be autocreated once the database created as we are using spring-data-jpa.

8 - Then register user and generate token by authenticating the user using SWAGGER URL -> http://localhost:8011/swagger-ui.html#/ .

9 - Add Authorization token to the swagger (present in top right corner) -> Bearer < Token value >.

10- Now you can directly access API's createTrain, BookTrain, GetAll train Etc api as token is added to the header in every request by the swagger(swagger takes care
    of the authorization once the token is added to it).
