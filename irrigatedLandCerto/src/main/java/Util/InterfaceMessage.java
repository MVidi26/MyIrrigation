package Util;

import javax.ejb.Local;

@Local(InterfaceMessage.class)
public interface InterfaceMessage {

    void sendMessageToMobile(MessageToDevice message);

}
