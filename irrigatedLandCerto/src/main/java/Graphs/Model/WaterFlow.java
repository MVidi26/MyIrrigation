package Graphs.Model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "waterFlowEntity")
public class WaterFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private Date date = new Date();
    @Column
    private double waterFlow;

    public WaterFlow() {
    }

    public WaterFlow(double waterFlow) {
        this.waterFlow = waterFlow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWaterFlow() {
        return waterFlow;
    }

    public void setWaterFlow(double waterFlow) {
        this.waterFlow = waterFlow;
    }
}
