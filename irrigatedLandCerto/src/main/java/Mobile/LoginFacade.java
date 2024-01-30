package Mobile;

import Devices.Model.DeviceMobile;
import Mobile.Request.LoginRequest;
import Mobile.Service.DeviceService;
import Mobile.Service.LoginServiceBean;
import User.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LoginFacade {
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    @PersistenceContext(name = "irrigatedLand")
    private EntityManager manager;
    @EJB
    private LoginServiceBean loginService;
    @EJB
    private DeviceService devService;
    public String doLogin(LoginRequest request) {
        String response = "";

        User user = loginService.login(request);
        DeviceMobile device = devService.getByDeviceName(request.getDeviceName());

        if (device == null) {
            return response = "DEVICE_NOT_FOUND";
        } else if (device.getUser() != null){
            return response = "DEVICE_ALREADY_USED";
        }
        if (user == null) return response = "USER_NOT_FOUND";

        device.setUser(user);
        manager.merge(device);

        return gson.toJson(user);
    }

    public boolean logout(User user) {
        try {
            DeviceMobile device = devService.getByUser(user);
            device.setUser(null);
            manager.merge(device);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
