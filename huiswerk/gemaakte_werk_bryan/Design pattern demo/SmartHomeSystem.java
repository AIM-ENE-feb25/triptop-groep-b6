package org.example;

import org.example.Systems.Lighting;
import org.example.Systems.MusicSystem;

public class SmartHomeSystem {
    private MusicSystem musicSystem = new MusicSystem();
    private Lighting lighting = new Lighting();
    private ClimateControl climateControl = new ClimateControl();

    void returnFromWork() {
        System.out.println("Thuis komen van werk...");
        climateControl.setTemperature(22);
        musicSystem.on();
        musicSystem.playMusic();
        lighting.on();
        lighting.dim();
    }

    void goToBed() {
        System.out.println("Naar bed gaan...");
        musicSystem.off();
        lighting.dim();
        climateControl.setTemperature(18);
    }
}
