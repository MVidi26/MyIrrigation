package Mobile;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MeasurementDataResponse implements Serializable {

    @SerializedName("airTemp")
    private String airTemp;
    @SerializedName("airUmid")
    private String airUmid;
    @SerializedName("tempGround")
    private String tempGroud;
    @SerializedName("umid")
    private String umid;
    @SerializedName("waterFlow")
    private String waterFlow;


    public MeasurementDataResponse() {
    }

    public String getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(String airTemp) {
        this.airTemp = airTemp;
    }

    public String getAirUmid() {
        return airUmid;
    }

    public void setAirUmid(String airUmid) {
        this.airUmid = airUmid;
    }

    public String getTempGroud() {
        return tempGroud;
    }

    public void setTempGroud(String tempGroud) {
        this.tempGroud = tempGroud;
    }

    public String getWaterFlow() {
        return waterFlow;
    }

    public void setWaterFlow(String waterFlow) {
        this.waterFlow = waterFlow;
    }

    public String getUmid() {
        return umid;
    }

    public void setUmid(String umid) {
        this.umid = umid;
    }
}
