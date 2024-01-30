package Devices.Model;

import javax.persistence.*;
import java.io.Serializable;
import User.Model.User;
@Entity
@Table(name = "devices")
public class DeviceMobile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String deviceName;
    @OneToOne
    @JoinColumn(nullable = true)
    private User user;

    public DeviceMobile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
