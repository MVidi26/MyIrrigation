package Job.EJB;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBServices {

    public static String topicService = "java:app/irrigatedLand/CommunicationMQTT/TopicServiceBean!irrigatedLand.CommunicationMQTT.TopicService";
    public static String umidityService = "java:module/irrigatedLand/GetUmidityRemoteService";
    public static String strategyService = "java:module/irrigatedLand/StrategyRemoteService";

    public static Object getEJB(String service){
        try{
            Context ctx = new InitialContext();
            ctx.lookup(service);
        } catch (NamingException e){
            throw new EJBException(e);
        }
        return null;
    }
}
