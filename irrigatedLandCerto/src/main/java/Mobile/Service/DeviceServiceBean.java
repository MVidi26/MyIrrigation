package Mobile.Service;

import Devices.Model.DeviceMobile;
import Mobile.Service.DeviceService;
import User.Model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DeviceServiceBean implements DeviceService {

    @PersistenceContext(name = "irrigatedLand")
    private EntityManager manager;

    @Override
    public DeviceMobile getByDeviceName(String deviceName) {
        String hql = "SELECT dev FROM DeviceMobile dev " +
                "WHERE dev.deviceName = :deviceName ";
        DeviceMobile device = manager.createQuery(hql, DeviceMobile.class)
                .setParameter("deviceName", deviceName)
                .getSingleResult();
        if (device != null) {
            return device;
        } else {
            return null;
        }
    }

    @Override
    public DeviceMobile getByUser(User user) {

        String hql = "SELECT dev FROM DeviceMobile dev " +
                "WHERE dev.user = :user ";

        return manager.createQuery(hql, DeviceMobile.class)
                .setParameter("user", user)
                .getSingleResult();
    }

}
