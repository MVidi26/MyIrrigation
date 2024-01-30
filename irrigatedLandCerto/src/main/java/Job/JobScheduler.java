package Job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class JobScheduler implements Serializable {

    private Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

    private Logger logger = Logger.getLogger(JobScheduler.class.getSimpleName());


    public JobScheduler() throws SchedulerException {
    }

    public void init() {
        try {
            JobKey meuJob = JobKey.jobKey("meuJob", "grupo1");
            boolean exists = scheduler.checkExists(meuJob);
            if (exists) {
                scheduler.resumeJob(meuJob);
                scheduler.start();
            } else {
                // Define o JobDetail com a classe do job
                JobDetail jobDetail = JobBuilder.newJob(JobQuartz.class)
                        .withIdentity("meuJob", "grupo1")
                        .build();

                // Define o Trigger que dispara o job a cada 10 segundos
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity("meuTrigger", "grupo1")
                        .startNow()
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInMinutes(3)
                                .repeatForever())
                        .build();

                // Agenda o job com o scheduler
                scheduler.scheduleJob(jobDetail, trigger);

                // Inicia o scheduler
                scheduler.start();
            }
        } catch (SchedulerException e) {
            e.getMessage();
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public void pause() {
        // Pausa o job
        try{
            logger.log(Level.INFO, "Parando...");
            JobKey jobKey = new JobKey("meuJob", "grupo1");
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isStarted(){
        JobKey job = JobKey.jobKey("meuTrigger", "grupo1" );
        try {
            return scheduler.isStarted();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
