package Dashboard.Service;

import javax.ejb.Remote;

@Remote(GetWaterFlowRemoteService.class)
public interface GetWaterFlowRemoteService {
    double getWaterFlow();
}
