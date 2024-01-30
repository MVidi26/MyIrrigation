package Dashboard.Sensors;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WaterFlow implements Serializable {

    @SerializedName("wf")
    private double waterFlowMeansurement;

    public WaterFlow() {
    }

    public double getWaterFlowMeansurement() {
        return waterFlowMeansurement;
    }

    public void setWaterFlowMeansurement(double waterFlowMeansurement) {
        this.waterFlowMeansurement = waterFlowMeansurement;
    }
}
