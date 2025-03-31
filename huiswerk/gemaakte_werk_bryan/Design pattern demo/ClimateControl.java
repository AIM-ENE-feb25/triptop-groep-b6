package org.example;

import org.example.Systems.Cooling;
import org.example.Systems.Heating;

public class ClimateControl {
    private Heating heating = new Heating();
    private Cooling cooling = new Cooling();

    void setTemperature(int temperature) {
        if (temperature > 20) {
            heating.heatToTemperature(temperature);
        } else {
            cooling.coolToTemperature(temperature);
        }
    }
}