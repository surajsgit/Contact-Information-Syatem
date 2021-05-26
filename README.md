# Contact-Information-Syatem

Clone the application

git clone https://github.com/surajsgit/Contact-Information-Syatem.git

Create MySQL database

create database ehealth_db

Change MySQL username and password as per your MySQL installation

open src/main/resources/application.properties file. change spring.datasource.username and spring.datasource.password properties as per your mysql installation

Run app

mvn clean package

cd target

java -jar ContactManagement-0.0.1-SNAPSHOT.jar

The server will start on port 8080.

# For UI Testing:

Go to URL : http://localhost:8080/index 

# For REST API Testing

Download or add Postman pluggin.

1)Fot creating contact :

URI: http://localhost:8080/api/contacts/ Method : POST

Body : { "firstName":"suraj", "lastName":"sontakke", "email":"suraj@gmail.com", "phoneNumber":8055320800, "status":"Active" }

2)For getting all contacts :

URI: http://localhost:8080/api/contacts/ Method : GET

3)For getting particular contact:

URI: http://localhost:8080/api/contacts/{id} Method : GET

For updating particulat contact :
URI: http://localhost:8080/api/contacts/{id} Method : PUT

Body : { "firstName":"suraj", "lastName":"sontakke", "email":"suraj@gmail.com", "phoneNumber":8055320800, "status":"Inactive" }

For Deleting particular contact :
URI: hhttp://localhost:8080/api/contacts/{id} Method : DELETE
