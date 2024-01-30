# **Institute of Education, Science and Technology of Rio Grande do Sul - Veranópolis Campus**

## Technology Course in Systems Analysis and Development - Final Project
### **Developer: Maicon Vidi**

### About:

The idea proposed for this project was to build an embedded system for monitoring and controlling 
irrigation pivots using the MQTT protocol to activate irrigation equipment.

#### Front End

The system was built using JSF and Primefaces, and features CRUD interfaces to create records of crops, products, 
planting strategies, users and devices, scheduling tasks to activate the pivots on a specific date or time.

There are also screens that provide graphs for data observation and analysis, and buttons to start and stop 
the service that checks data from the sensors.

#### Back-End

The Back-End of this project was developed using Java EE 8, Hibernate, EJB's, CDI, Quartz and PostgreSQL.
Through these languages and the insertion of data by the CRUDs, the registration of data from the forms is carried out, 
as well as obtaining data from the sensors on the ESP8266 board and the system making a decision on whether or not the pivot should be activated.
The person responsible for making the decision to activate or deactivate the irrigation pivot is a Job built with Quartz, 
which in turn will obtain the value of the sensors, compare them and determine if the sensor is activated or deactivated, 
the command is published in an MQTT topic, on an AWS EC2 server, to which the ESP8266 board is subscribed. 
Using the same protocol, a message is posted to another topic on the same EC2 server where the Android System Application is subscribed.

#### Communication

Obtaining sensor values is carried out through RESTEasy, where the system makes POST requests to the EndPoints 
available on the ESP8266 Board's WebServer and uses this data to make decisions regarding the activation of the irrigation pivots, 
when making the decision, whether to activate or turn off the pivot, a message is published on the MQTT server which is present in an AWS EC2, 
which in turn makes the message available to the ESP8266 Board, where it will interpret the information and carry out the activation commands.

#### This project contains the technologies:
* Primefaces
* Hibernate
* Wildfly
* RESTEasy
* Java EE 8
* EJB
* CDI
* Quartz
* JSF
* Cloud AWS
* MQTT Protocol
* PostgreSQL