package Util;

import Farming.Model.Farming;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class DAO <T> implements Serializable {

    private EntityManager manager;
    private final Class<T> classe;

    public DAO(EntityManager manager, Class<T> classe) {
        this.manager = manager;
        this.classe = classe;
    }

    public void add(T t) {
        manager.persist(t);
    }

    public void remove(T t) {
        manager.remove(manager.merge(t));
    }

    public void update(T t) {
        manager.merge(t);
    }

    public T getProducts(Long id) {
        return manager.find(classe, id);
    }

    public T getForId(Long id) {
        return manager.find(classe, id);
    }

    public List<T> getAll(){
        CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        return manager.createQuery(query).getResultList();
    }

}
