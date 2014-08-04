andruino_project
================

android app and arduino to automate lights,fans,motors,actuators,relays,etc.

This project has a few key elements :


*Android application for smartphone user
(makes a request on/off to web application)

*Web application to route requests to home server
(This is necessary because an android application running on wifi in your home cannot ping your server at same IP address)
This application is a Python/Django web application running on Google App Engine

*Home server w/ port forwarded router
A python script ( utilizing the socket and thread imports ) acts as a home server running on an Ubuntu OS.  Port forwarding is necessary and reservation of DHCP for the Ubuntu OS device */ Don't buy a router from D-Link ;) /*

*Arduino Sketch for microcontroller
The arduino sketch is the logic that is uploaded to the volatile memory on the Arduino microcontroller board

