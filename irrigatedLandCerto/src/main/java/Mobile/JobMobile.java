package Mobile;

import Job.JobScheduler;
import Mobile.Request.JobMobileRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("job")
public class JobMobile {

    @EJB
    private JobScheduler job;
    private Logger logger = Logger.getLogger(JobMobile.class.getSimpleName());
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @POST
    @Path("startStopJob")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public void startStopJob(String req){
        JobMobileRequest request = gson.fromJson(req, JobMobileRequest.class);
        String msgRequest = toJson(request);
        logger.log(Level.INFO, msgRequest);
        if (request.isStart()){
            logger.log(Level.INFO, "Mobile - Iniciando JOB");
            job.init();
        }

        if (!request.isStart()) {
            logger.log(Level.INFO, "Mobile - Parando JOB");
            job.pause();
        }
    }

    public String toJson(Object request){
        return gson.toJson(request);
    }

}
