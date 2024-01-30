package Dashboard.Sensors;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AirMeasurements implements Serializable {

    @SerializedName("temperature")
    private double temperature;

    @SerializedName("humidity")
    private double airHumidity;

    public AirMeasurements() {
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(double airHumidity) {
        this.airHumidity = airHumidity;
    }
}
