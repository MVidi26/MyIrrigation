package Farming.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "farming")
public class Farming implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column
    private double temperature;
    @Column
    private double moisture;

    public Farming() {
    }

    public Farming(Long productId, String name, double temperature, double moisture) {
        this.id = productId;
        this.name = name;
        this.temperature = temperature;
        this.moisture = moisture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getMoisture() {
        return moisture;
    }

    public void setMoisture(double moisture) {
        this.moisture = moisture;
    }
}
