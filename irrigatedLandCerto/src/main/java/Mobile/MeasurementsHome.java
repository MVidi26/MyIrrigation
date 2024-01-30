package Mobile;

import Dashboard.Service.GetTemperature;
import Dashboard.Service.GetTemperatureService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("ms")
public class MeasurementsHome {

    @EJB
    private MeasurementsFacadeHome measurementFacade;


    @Path("data")
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public String getMeasurements(){

        try {
            return measurementFacade.getDatas();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
