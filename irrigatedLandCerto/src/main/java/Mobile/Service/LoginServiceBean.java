package Mobile.Service;

import Mobile.Request.LoginRequest;
import User.Service.ListUserService;
import User.Model.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class LoginServiceBean implements Serializable {

    @EJB
    private ListUserService service;

    public User login(LoginRequest request) {
        User user = null;
        try {
            user = service.getUser(request.getUser(), request.getPassword());
            if (user != null) {
                return user;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
