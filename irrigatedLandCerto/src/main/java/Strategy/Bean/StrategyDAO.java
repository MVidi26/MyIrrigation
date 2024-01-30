package Strategy.Bean;

import Farming.Model.Farming;
import Strategy.Model.Strategy;
import Util.DAO;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class StrategyDAO implements Serializable {

    @PersistenceContext(name = "irrigatedLand")
    private EntityManager manager;
    private DAO<Strategy> str;

    @PostConstruct
    void init(){
        this.str = new DAO<>(manager, Strategy.class);
    }

    public void add(Strategy strat) {
        this.str.add(strat);
    }

    public List<Strategy> getAll() {
        return this.str.getAll();
    }

    public void update(Strategy strat) {
        this.str.update(strat);
    }

    public void remove(Strategy strat) {
        this.str.remove(strat);
    }

    public Strategy getForId(long id) {
        return this.str.getForId(id);
    }

}
