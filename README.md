# MyIrrigation

This is a project of an embedded system for the control and monitoring of irrigation pivots. It consists of three modules:

1st Module Esp8266Server: Contains the code of the ESP8266 Board's WebServer. This WebServer is responsible for providing the EndPoints with the Sensor information.

2nd Module IrrigatedLand: This module contains the server responsible for consuming the information made available in the EndPoints of the previous module and making decisions regarding the activation of the irrigation pivots. This module also includes the registrations of products and users, as well as the planting strategy. Here, the service responsible for sending the command to activate the pivots makes the decision whether or not to activate, based on the registration made.

3rd Module myIrrigationApp: This module contains the Android application, where the user can access and consult the data collected from the sensors of the first module and also has the possibility to perform some tasks, such as activating and deactivating the service for the activation of the irrigation pivots.

The communication between the second module and the first, for the activation of the irrigation pivots, occurs through the MQTT protocol, using an MQTT server installed on an AWS EC2. On this MQTT server, the ESP8266 board is subscribed as a listener, and module 2 is subscribed as a publisher. The Android application is also subscribed to this same MQTT server, listening on a specific topic. Whenever the pivots are turned on or off by the service, it sends a notification with the message of the command performed.
