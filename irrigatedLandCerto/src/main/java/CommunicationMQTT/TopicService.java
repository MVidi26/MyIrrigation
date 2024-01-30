package CommunicationMQTT;

import javax.ejb.Local;

@Local
public interface TopicService {

    String getTopicStartStop();

    String getTopicMessageMobile();
}
