package Mobile;

import Mobile.Request.LoginRequest;
import Mobile.Request.LogoutRequest;
import User.Service.ListUserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import User.Model.User;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;


@Path("acess")
public class Login {

    @EJB
    private LoginFacade facadeLogin;
    @EJB
    private ListUserService userService;
    private Logger logger = Logger.getLogger(Login.class.getSimpleName());
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public synchronized String login(String req) {
        LoginRequest request = gson.fromJson(req, LoginRequest.class);
        String logRequest = "Login/login() - Request: " + req;
        logger.log(Level.INFO, logRequest);

        String user = facadeLogin.doLogin(request);
        if (!user.isEmpty()) {
            System.out.println("Login/login() - Response: " + user);
            return user;
        } else {
            return gson.toJson("USER_NOT_FOUND");
        }
    }

    @GET
    @Path("teste")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public synchronized String teste() {
        String teste = "testando";
        String response = toJson(teste);
        logger.log(Level.INFO, response);
        return response;
    }

    @POST
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public synchronized String logout(String req){
        System.out.println("Login/logout() - Request: " + req);
        LogoutRequest request = gson.fromJson(req, LogoutRequest.class);
        User user = userService.getByName(request.getName());
        boolean response = facadeLogin.logout(user);
        System.out.println("Login/logout() - Response: " + response);
        return gson.toJson(response);
    }

    public String toJson(Object request) {
        return gson.toJson(request);
    }

}
