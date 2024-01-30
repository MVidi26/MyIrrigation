package Dashboard;

import java.io.Serializable;

public class AirData implements Serializable {

    private String temperature;
    private String humidity;

    public AirData() {
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
