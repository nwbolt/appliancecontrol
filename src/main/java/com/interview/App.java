package com.interview;

import java.util.Scanner;

import com.interview.controller.SmartApplianceController;
import com.interview.model.AirConditioner;
import com.interview.model.Fan;
import com.interview.model.Light;
import com.interview.service.YearlyUpdateService;

public class App 
{
    public static void main( String[] args )
    {
        SmartApplianceController controller = new SmartApplianceController();

        // Create appliance Objects
        Fan fan = new Fan(1, "Bed Room Fan");
        Light light = new Light(2, "Outside Light");
        AirConditioner airconditioner = new AirConditioner(3, "Upstairs Airconditioner");

        controller.addAppliance(fan);
        controller.addAppliance(light);
        controller.addAppliance(airconditioner);

        // Give appliances objects inital states 
        fan.setSpeed(1);
        light.turnOn();
        airconditioner.setMode(AirConditioner.Mode.COOLING);

        // start background service to update appliances at specified time
        YearlyUpdateService yearlyUpdateService = new YearlyUpdateService(controller);
        yearlyUpdateService.startService();   
        

        // print inital appliance state report to command line
        System.out.println(controller.currentstateReport());

        // Start command line interface loop
        System.out.println("Program Started: Type 'status', 'off' ,'exit'.");
        Scanner scanner = new java.util.Scanner(System.in);
        boolean running = true;
        try {
            while (running) {
                String input = scanner.nextLine().trim().toLowerCase();
                switch (input) {
                    case "status":
                        System.out.println(controller.currentstateReport());
                        break;

                    case "off":
                        controller.turnAllOff();
                        System.out.println("All appliances turned off.");
                        break;

                    case "exit":
                        running = false;
                        System.out.println("Exiting program.");
                        break;

                    default:
                        System.out.println("Unknown command. Please type 'status', 'off', or 'exit'.");
                }
            }
        } finally {
            scanner.close();
            yearlyUpdateService.shutdownService();
        }
    }
}
