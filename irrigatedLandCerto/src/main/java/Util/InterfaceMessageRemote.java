package Util;

import javax.ejb.Remote;

@Remote(InterfaceMessageRemote.class)
public interface InterfaceMessageRemote {
    void sendMessageToMobile(MessageToDevice message);
}
