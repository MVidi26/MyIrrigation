package Job;

import Dashboard.Service.GetWaterFlowService;
import Graphs.Model.WaterFlow;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@DisallowConcurrentExecution
public class JobWaterFlow implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Object waterFlowObject = null;

        try{
            Context ctx = new InitialContext();
            waterFlowObject = ctx.lookup("ejb:/irrigatedLand-1.0-SNAPSHOT/GetWaterFlowServiceBean!Dashboard.Service.GetWaterFlowService");
        }catch (NamingException e){
            System.out.println(e.getMessage());
        }

        GetWaterFlowService waterService = (GetWaterFlowService) waterFlowObject;
        WaterFlow wf = new WaterFlow(waterService != null ? waterService.getWaterFlow() : 0);

    }
}
