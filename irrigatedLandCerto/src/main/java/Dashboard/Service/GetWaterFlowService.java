package Dashboard.Service;

import javax.ejb.Local;

@Local
public interface GetWaterFlowService {

    double getWaterFlow();

}
