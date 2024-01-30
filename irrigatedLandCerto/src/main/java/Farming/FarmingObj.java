package Farming;

import javax.ejb.Stateless;
import java.io.Serializable;

public class FarmingObj implements Serializable {

    private String name;

    private String temperature;

    private String moisture;

    public FarmingObj() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getMoisture() {
        return moisture;
    }

    public void setMoisture(String moisture) {
        this.moisture = moisture;
    }
}
