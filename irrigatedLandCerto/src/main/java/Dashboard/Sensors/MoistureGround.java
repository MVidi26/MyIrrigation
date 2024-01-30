package Dashboard.Sensors;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MoistureGround implements Serializable {

    @SerializedName("umid")
    private double moisture;

    public MoistureGround() {
    }
    public MoistureGround(double moisture) {
        this.moisture = moisture;
    }

    public double getMoisture() {
        return moisture;
    }

    public void setMoisture(double moisture) {
        this.moisture = moisture;
    }
}
