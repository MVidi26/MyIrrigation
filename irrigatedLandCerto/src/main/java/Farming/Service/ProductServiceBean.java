package Farming.Service;

import Farming.Interfaces.ProductService;
import Farming.FarmingObj;
import Farming.Interfaces.ProductServiceRemote;
import Farming.Model.Farming;
import Strategy.Interfaces.StrategyRemoteService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless(mappedName = "ProductServiceBean")
@EJB(name = "Farming.Interfaces.ProductServiceRemote", beanInterface = ProductServiceRemote.class)
public class ProductServiceBean implements ProductService, ProductServiceRemote {

    @PersistenceContext(name = "irrigatedLand")
    private EntityManager manager;

    @Override
    public List<Farming> getAllProducts() {

        List<Farming> list = new ArrayList<>();

        String hql = "SELECT far " +
                "FROM Farming far";

        list = manager.createQuery(hql, Farming.class).getResultList();

        if (!list.isEmpty()) return list;

        return new ArrayList<>();
    }

    @Override
    public Farming getForId(Long id) {
        return manager.find(Farming.class, id);
    }
}
