package User.Service;

import User.Model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ListUserService {
    boolean exists(String user, String password);

    User getUser(String user, String password);

    List<User> getAllUser();

    User getByName(String name);

}
