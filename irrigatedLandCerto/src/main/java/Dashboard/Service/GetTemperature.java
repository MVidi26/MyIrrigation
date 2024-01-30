package Dashboard.Service;

import Dashboard.AirData;
import Dashboard.Sensors.AirMeasurements;

import javax.ejb.Local;
import java.io.IOException;


@Local
public interface GetTemperature {

//    String getTempAmbient() throws IOException;

    String getTempGround() throws IOException;

    AirData getAirTemp() throws IOException;

}
