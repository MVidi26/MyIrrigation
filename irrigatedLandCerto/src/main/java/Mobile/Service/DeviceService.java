package Mobile.Service;

import Devices.Model.DeviceMobile;
import User.Model.User;
import javax.ejb.Local;

@Local
public interface DeviceService {
    DeviceMobile getByDeviceName(String deviceName);
    DeviceMobile getByUser(User user);
}
