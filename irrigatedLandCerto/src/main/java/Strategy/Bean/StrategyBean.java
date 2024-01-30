package Strategy.Bean;

import Farming.Interfaces.ProductService;
import Farming.Model.Farming;
import Strategy.Interfaces.StrategyService;
import Strategy.Model.Strategy;
import Util.Transaction;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class StrategyBean implements Serializable {

    private long strategyId;
    private List<Strategy> list;
    private List<Farming> listF;
    private String farmId;
    @EJB
    private StrategyService strat;
    @EJB
    private ProductService prod;

    @Inject
    private StrategyDAO dao;

    private Strategy strategy = new Strategy();

    @PostConstruct
    void init(){
        createDataTable();
        strategy = new Strategy();
    }

    private void createDataTable() {
        this.list = strat.getStrat();
        this.listF = prod.getAllProducts();
    }

    public List<Strategy> getList() {
        return list;
    }

    public void setList(List<Strategy> list) {
        this.list = list;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(long strategyId) {
        this.strategyId = strategyId;
    }

    public List<Farming> getListF() {
        return listF;
    }

    public void setListF(List<Farming> listF) {
        this.listF = listF;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    @Transaction
    public String altera(Strategy str) {
        this.strategy = str;
        return "formStrategy?faces-redirect=true";
    }

    @Transaction
    public void remover(Strategy str) {
        this.dao.remove(str);
        this.list = strat.getStrat();
    }

    @Transaction
    public String save() {

        if (this.strategy.getId() == null) {
            this.dao.add(this.strategy);
            this.list = strat.getStrat();
        } else {

            long id = Long.parseLong(farmId);
            Farming farming = prod.getForId(id);
            this.strategy.setFarm(farming);

            this.dao.update(this.strategy);
            this.list = strat.getStrat();
        }
        this.strategy = new Strategy();
        return "formStrategy?faces-redirect=true";
    }
    @Transaction
    public void getForId() {
        this.dao.getForId(strategy.getId());
    }

    public String newStrategy(){
        this.strategy = new Strategy();
        return "formStrategy?faces-redirect=true";}

}
