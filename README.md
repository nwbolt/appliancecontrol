
# Smart Home Appliance Control System

This is my submision for the smart home appliance control solution assignment. 


# How to install and run 

clone the repository from this github page or Download the zip file from the Releases

```bash
git clone https://github.com/nwbolt/appliancecontrol.git
```
import as a maven project in the IDE of your choosing and run the App.java



# Usage 

This program uses a comand line interface 

status -  Prints the current state of all appliances 

off    -  Manually turns all appliances to the off state without background update service

exit   -  Exits the Program

Note: the backgroud update service can be triggered by manualy setting your local computers time to Jan 1st 12:59 am before starting the program. After starting the program wait 1 minute for backgroud service to be triggered and update all appliances to be turned off. 
