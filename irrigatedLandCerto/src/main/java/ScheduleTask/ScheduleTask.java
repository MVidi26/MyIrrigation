package ScheduleTask;

import Job.JobSchedulerBean;
import org.quartz.SchedulerException;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class ScheduleTask implements Serializable {

    private Date beginDate;
    private Date endDate;

    @Inject
    private JobSchedulerBean jobBean;

    public ScheduleTask() {
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String newTask(){
        return "formScheduleTask?faces-redirect=true";
    }

    public String save(){
        try {
            jobBean.createNewTask(this.beginDate, this.endDate);
        } catch (SchedulerException e) {
            System.out.println(e.getMessage());
        }
        return "dashboard?faces-redirect=true";
    }
}
