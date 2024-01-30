package Mobile;

import Dashboard.AirData;
import Dashboard.Service.GetTemperature;
import Dashboard.Service.GetUmidity;
import Dashboard.Service.GetWaterFlowService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;

@Stateless
public class MeasurementsFacadeHome {

    @EJB
    private GetTemperature temp;
    @EJB
    private GetUmidity umid;
    @EJB
    GetWaterFlowService flow;
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public String getDatas() throws IOException {
        MeasurementDataResponse response = new MeasurementDataResponse();

        AirData airTemp = temp.getAirTemp();
        String tempGround = temp.getTempGround();
        String umidity = umid.getUmidity();
        double waterFlow = flow.getWaterFlow();
        String waterFlowFormat = String.format("%.2f", waterFlow);

        response.setAirTemp(airTemp.getTemperature());
        response.setAirUmid(airTemp.getHumidity());
        response.setTempGroud(tempGround);
        response.setWaterFlow(waterFlowFormat);
        response.setUmid(umidity);

        return gson.toJson(response);

    }
}
