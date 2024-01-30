package Strategy;

import Farming.Model.Farming;

import java.io.Serializable;

public class StrategyObj implements Serializable {

    private String nameStrat;
    private Farming far;

    public StrategyObj() {
    }

    public String getNameStrat() {
        return nameStrat;
    }

    public void setNameStrat(String nameStrat) {
        this.nameStrat = nameStrat;
    }

    public Farming getFar() {
        return far;
    }

    public void setFar(Farming far) {
        this.far = far;
    }
}
