# hospital microservice

The hospital project uses Spring Boot 3.1.0, Java 20, RESTful, Spring MVC and fast in-memory H2 Database.
The project implements REST API for patient at hospital and Junit tests for the provided functionalities.
1.	Holds a repository of patients with the following attributes:
a.	Identifier
b.	First Name
c.	Last Name
d.	Date of Birth
2.	Provides a few endpoints that support the following:
a.	Add a patient
b.	Get a list of all patients
c.	Get a specific patient
d.	Search for a patient by first name

Following command can be used to demonstrate the implemented use cases:
Add a patient:  (Date format “yyyy-MM-dd” is specified in application.properties)
curl -d "{\"firstName\":\"John\", \"lastName\":\"Lancaster\", \"birthday\":\"1994-08-23\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/patients/create
curl -d "{\"firstName\":\"John\", \"lastName\":\"Gray\", \"birthday\":\"1990-02-24\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/patients/create
curl -d "{\"firstName\":\"Chris\", \"lastName\":\"Brown\", \"birthday\":\"2001-09-03\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/patients/create
curl -d "{\"firstName\":\"James\", \"lastName\":\"Brown\", \"birthday\":\"2001-09-03\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/patients/create

Get a list of all patients:
curl http://localhost:8080/patients
Get a specific patient:
curl http://localhost:8080/patients/1
Search for a patient by first name:
curl http://localhost:8080/patients/find/John
