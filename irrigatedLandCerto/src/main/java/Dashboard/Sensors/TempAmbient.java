package Dashboard.Sensors;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TempAmbient implements Serializable {

    @SerializedName("tmpA")
    private double temperature;

    public TempAmbient() {
    }

    public TempAmbient(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

}
