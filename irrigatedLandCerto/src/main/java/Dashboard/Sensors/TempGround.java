package Dashboard.Sensors;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TempGround implements Serializable {
    @SerializedName("tmpG")
    private double temperature;

    public TempGround() {
    }

    public TempGround(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
