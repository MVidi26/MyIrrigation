package User.Bean;

import Job.JobScheduler;
import User.Model.User;
import User.Service.ListUserDAO;
import User.Service.ListUserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;

    @SessionScoped
    private User usuario;

    @Inject
    private ListUserDAO dao;
    @EJB
    private ListUserService service;
    @EJB
    private JobScheduler qrtzConfig;

    @PostConstruct
    void init(){
        usuario = new User();
    }

    public String logar(){
        boolean exists = service.exists(username, password);
        FacesContext context = FacesContext.getCurrentInstance();
        if (exists){
            usuario = service.getUser(username, password);
            context.getExternalContext().getSessionMap().put("User", this.usuario);
            return "dashboard?faces-redirect=true";
        }

        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage("Usuário Incorreto"));
        context.addMessage(null , new FacesMessage("Senha Incorreta"));

        return "login?faces-redirect=true";
    }

    public String logout(){
        qrtzConfig.pause();
        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().remove("User");
        return "login?faces-redirect=true";
    }

    public boolean isLoged() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("User");
    }

    public String createUser(){
        return "formularioUser?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUsuario() {
        return usuario;
    }
}
