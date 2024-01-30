package Strategy.Service;

import Strategy.Interfaces.StrategyRemoteService;
import Strategy.Interfaces.StrategyService;
import Strategy.Model.Strategy;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless(mappedName = "StrategyServiceBean")
@EJB(name = "Strategy.Interfaces.StrategyRemoteService", beanInterface = StrategyRemoteService.class)
public class StrategyServiceBean implements StrategyService, StrategyRemoteService {

    @PersistenceContext(name = "irrigatedLand")
    private EntityManager manager;

    @Override
    public List<Strategy> getStrat() {

        List<Strategy> obj = new ArrayList<>();

        String hql = "SELECT s " +
                "FROM strategy s " +
                "JOIN s.farm farm ";

        Query query = manager.createQuery(hql);

        List<Strategy> resultList = query.getResultList();

        if (resultList.isEmpty()) return new ArrayList<>();
        obj = resultList;
        return obj;
    }
}
