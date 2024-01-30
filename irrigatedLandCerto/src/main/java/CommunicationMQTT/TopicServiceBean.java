package CommunicationMQTT;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "CommunicationMQTT.TopicServiceBean")
@EJB(name = "CommunicationMQTT.TopicService", beanInterface = TopicRemoteService.class)
public class TopicServiceBean implements TopicService, TopicRemoteService {

    @PersistenceContext(name = "irrigatedLand")
    private EntityManager manager;

    @PostConstruct
    void TopicServiceBean() {
    }

    @Override
    public String getTopicStartStop() {
        return "/startStop";
    }

    @Override
    public String getTopicMessageMobile() {
        return "/mobileMessage";
    }

}
