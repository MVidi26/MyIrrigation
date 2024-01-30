package Strategy.Model;

import Farming.Model.Farming;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "strategy")
public class Strategy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String strategyName;
    @ManyToOne
    @JoinColumn
    private Farming farm;

    public Strategy() {
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Farming getFarm() {
        return farm;
    }

    public void setFarm(Farming farm) {
        this.farm = farm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
