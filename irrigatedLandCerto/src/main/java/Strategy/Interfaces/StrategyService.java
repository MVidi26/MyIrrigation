package Strategy.Interfaces;

import Strategy.Model.Strategy;

import javax.ejb.Local;
import java.util.List;

@Local
public interface StrategyService {

    List<Strategy> getStrat();

}
