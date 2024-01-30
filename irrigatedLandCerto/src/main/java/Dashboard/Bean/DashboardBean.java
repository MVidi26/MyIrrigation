package Dashboard.Bean;

import Dashboard.AirData;
import Dashboard.Service.GetTemperature;
import Dashboard.Service.GetUmidity;
import Dashboard.Service.GetWaterFlowService;
import Job.JobScheduler;
import org.quartz.SchedulerException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class DashboardBean implements Serializable {
    @EJB
    private GetTemperature getTemp;
    @EJB
    private GetUmidity getUmid;
    @EJB
    private JobScheduler qrtzConfig;
    @EJB
    private GetWaterFlowService waterFlowService;
    private String tempAmbient;

    private String tempGround;

    private String moisture;

    private String evaporation;
    private String waterFlow;

    private AirData air;


    @PostConstruct
    void init() throws IOException, SchedulerException {
        boolean started = qrtzConfig.isStarted();
        if (!started) qrtzConfig.init();
        createDataTable();
    }

    private void createDataTable() throws IOException {
//        this.tempAmbient = getTemp.getTempAmbient();
        this.tempGround = getTemp.getTempGround();
        this.air = getTemp.getAirTemp();
        this.moisture = getUmid.getUmidity();
        double waterFlowS = waterFlowService.getWaterFlow();
        this.waterFlow = String.format("%.2f", waterFlowS);
    }

    public String getTempAmbient() {
        return tempAmbient;
    }

    public void setTempAmbient(String tempAmbient) {
        this.tempAmbient = tempAmbient;
    }

    public String getMoisture() {
        return moisture;
    }

    public void setMoisture(String moisture) {
        this.moisture = moisture;
    }

    public String getTempGround() {
        return tempGround;
    }

    public void setTempGround(String tempGround) {
        this.tempGround = tempGround;
    }

    public String getEvaporation() {
        return evaporation;
    }

    public void setEvaporation(String evaporation) {
        this.evaporation = evaporation;
    }

    public AirData getAir() {
        return air;
    }

    public void setAir(AirData air) {
        this.air = air;
    }

    public String getWaterFlow() {
        return waterFlow;
    }

    public void setWaterFlow(String waterFlow) {
        this.waterFlow = waterFlow;
    }
}
