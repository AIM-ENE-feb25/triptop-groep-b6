package org.example;

public class App {
    public static void main(String[] args) {
        SmartHomeSystem smartHome = new SmartHomeSystem();

        // Scenario 1: Thuis komen van werk
        smartHome.returnFromWork();

        System.out.println();

        // Scenario 2: Naar bed gaan
        smartHome.goToBed();
    }
}
