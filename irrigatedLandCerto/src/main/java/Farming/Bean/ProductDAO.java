package Farming.Bean;

import Farming.Model.Farming;
import Util.DAO;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ProductDAO implements Serializable {

    @PersistenceContext(name = "irrigatedLand")
    private EntityManager manager;
    private DAO<Farming> far;

    @PostConstruct
    void init(){
        this.far = new DAO<>(manager, Farming.class);
    }

    public void add(Farming far) {
        this.far.add(far);
    }

    public List<Farming> getAll() {
        return this.far.getAll();
    }

    public void update(Farming far) {
        this.far.update(far);
    }

    public void remove(Farming far) {
        this.far.remove(far);
    }

    public Farming getForId(Long clientId) {
        return this.far.getForId(clientId);
    }


}
