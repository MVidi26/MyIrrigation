package Dashboard.Service;

import javax.ejb.Remote;

@Remote(GetUmidityRemoteService.class)
public interface GetUmidityRemoteService {
    String getUmidity();
}
