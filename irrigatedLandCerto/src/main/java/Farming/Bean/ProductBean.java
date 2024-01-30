package Farming.Bean;

import Farming.Interfaces.ProductService;
import Farming.Model.Farming;
import Util.Transaction;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductBean implements Serializable {

    private long prodId;
    private List<Farming> list;
    @EJB
    private ProductService pServ;
    @Inject
    private ProductDAO dao;
    private Farming farming;
    private Farming f;

    @PostConstruct
    void init(){
        createDataTable();
        farming = new Farming();
    }

    void createDataTable(){
        this.list = pServ.getAllProducts();
    }

    public List<Farming> getList() {
        return list;
    }

    public void setList(List<Farming> list) {
        this.list = list;
    }

    public void setFarming(Farming farming) {
        this.farming = farming;
    }

    public Farming getFarming() {
        return farming;
    }

    public Farming getF() {
        return f;
    }

    public void setF(Farming f) {
        this.f = f;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public String formApp() {
        return "formularioProd?faces-redirect=true";
    }

    @Transaction
    public String saveProduct() {
        if (this.farming.getId() == null) {
            this.dao.add(this.farming);
            this.list = pServ.getAllProducts();
            return "main?faces-redirect=true";
        } else {
            this.dao.update(this.farming);
        }
        this.farming = new Farming();
        return "main?faces-redirect=true";
    }

    public void getFarmingForId(){
        this.farming = this.dao.getForId(prodId);
    }

    @Transaction
    public String altera(Farming farm) {
        farming = farm;
        return "formularioProd?faces-redirect=true";
    }

    @Transaction
    public void remover(Farming farm) {
        this.dao.remove(farm);
        this.list = pServ.getAllProducts();
    }

    public String newFarming(){
        this.farming = new Farming();
        return "formularioProd?faces-redirect=true";
    }

}
