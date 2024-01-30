package Strategy.Interfaces;

import Strategy.Model.Strategy;
import Strategy.StrategyObj;

import javax.ejb.Remote;
import java.util.List;

@Remote(StrategyRemoteService.class)
public interface StrategyRemoteService {
    List<Strategy> getStrat();
}
