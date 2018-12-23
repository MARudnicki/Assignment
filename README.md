# Assignment
Assignment for the screening round

Above API consumes json data from multiple URLs , aggregate the data and return the aggregated data in json format.

To build the project, please use below command

a. mvn clean install

A war file will be created under target folder under the base folder. deploy the war file onto a server. I have Used WildFly for testing purpose. 

Steps to deploy

1. Copy tg-sg-api.war from target folder to {wildfly directory}\standalone\deployments
2. start the server by double clicking on {wildfly directory}\bin\standalone.bat (on windows)
3. logs can be seen on {wildfly directory}\standalone\log\server.log

Swagger2 has been used for API documentation. Once the application has been deploymed successfully, access the Swagger using link below

http://localhost:8080/tg-sg-api/swagger-ui.html

Resources have been secured with OAuth securiry. Please pass in below token as Authorisation header.

VGdBZG1pbjphc3NpZ25tZW50


Upon calling /flightManagement/flights method, it should return sorted list of both business and cheap flights.


Feel free to contact developer for any queries/suggestions.

Regards,
Syed Irfan
syedirfan33@ymail.com

