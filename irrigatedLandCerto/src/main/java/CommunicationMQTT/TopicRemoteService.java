package CommunicationMQTT;

import javax.ejb.Remote;

@Remote(TopicRemoteService.class)
public interface TopicRemoteService {
    String getTopicStartStop();
    String getTopicMessageMobile();
}
