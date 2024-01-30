package EndPoints;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class EndPoint implements Serializable {

    private String tempGround = "http://192.168.3.253/temperatureGround";
//    private String tempAmbient = "http://192.168.3.253/temperature";
    private String airTemp = "http://192.168.3.253/airTemp";
    private String umidity = "http://192.168.3.253/umidityGround";
    private String startStop = "http://192.168.3.253/startStop";
    private String waterFlow = "http://192.168.3.253/waterFlow";
    private String postMessageDevice = "";

    public EndPoint() {
    }

    public String getTempGround() {
        return tempGround;
    }
//    public String getTempAmbient() {
//        return tempAmbient;
//    }
    public String getAirTemp() {
        return airTemp;
    }
    public String getUmidity() {
        return umidity;
    }

    public String getStartStop() {
        return startStop;
    }

    public String getWaterFlow() {
        return waterFlow;
    }

    public String getPostMessageDevice() {
        return postMessageDevice;
    }
}
