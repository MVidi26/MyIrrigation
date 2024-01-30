package Job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class JobSchedulerBean implements Serializable {

    private Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

    private List<JobExecutionContext> list;

    public JobSchedulerBean() throws SchedulerException {
    }

    @PostConstruct
    void init() throws SchedulerException {
        populate();
    }

    public void startJob() throws SchedulerException {
        JobKey jobKey = new JobKey("meuJob", "grupo1");
        TriggerKey triggerKey = new TriggerKey("meuTrigger", "grupo1");
        boolean exists = scheduler.checkExists(jobKey);
        boolean exists1 = scheduler.checkExists(triggerKey);
        if (exists && exists1) {
            scheduler.deleteJob(jobKey);
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
    }

    public void pauseJob() {
        try {
            // Pausa o job
            System.out.println("Parando ...");
            JobKey jobKey = new JobKey("meuJob", "grupo1");
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void createNewTask(Date beginDate, Date endDate) throws SchedulerException {

        JobKey jobKey = new JobKey("taskedJob", "groupTask");
        TriggerKey triggerKey = new TriggerKey("taskedTrigger", "groupTask");

        boolean existsJobKey = scheduler.checkExists(jobKey);
        boolean existsTriggerKey = scheduler.checkExists(triggerKey);
        if (existsTriggerKey && existsJobKey) scheduler.deleteJob(jobKey);

        JobDetail detail = JobBuilder.newJob(JobWaterFlow.class)
                .withIdentity("taskedJob", "groupTask")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("taskedTrigger", "groupTask")
                .startAt(beginDate)
                .endAt(endDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .build();

        // Agenda o job com o scheduler
        scheduler.scheduleJob(detail, trigger);

        // Inicia o scheduler
        scheduler.start();
    }

    void populate(){
        try {
            this.list = scheduler.getCurrentlyExecutingJobs();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public String navigation(){
        return "Jobs?faces-redirect=true";
    }

    public List<JobExecutionContext> getList() {
        return list;
    }

    public void setList(List<JobExecutionContext> list) {
        this.list = list;
    }
}
