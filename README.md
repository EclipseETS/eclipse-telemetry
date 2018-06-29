eclipse-telemetry
=================

Telemetry for eclipse solar car

This software is reading standard CAN Bus and display it in a GUI

This program is ment to be use for Éclipse Solar Car. http://eclipse.etsmtl.ca/

It is running on Java 7, should be retrocompatible with Java 6
To run this project open in Eclipse and run the ant file to generate the binary. It will also create all the needed infrastructure to run it. Used librairy sould be automatically added to the JAr file

Part of this code is under Apache License.

Basic concept of this application:

It read standard CANBus from a standard serial interface (tty) or COM in Windows and store, show, and calcul many information with these datas.

GUI is mostely design for our application but core could easely be reuse.


You can access documentation and JAVADOC here : http://eclipseets.github.io/eclipse-telemetry/

you can get release here: https://github.com/EclipseETS/eclipse-telemetry/releases

### Using the app on Linux
Depending on the version of Linux you use, the app might not have the required permissions to use devices connected on serial ports. Start by identifying the connected device's group owner and add yourself to this group (the group might be `dialout`). Then, after restarting your computer, the app should have access to the serial ports.

The connected devices are normally reported under `/dev/`. This also where the app looks for connected devices.

To add yourself to the desired group, use `sudo usermod -a -G dialout <username>`.
