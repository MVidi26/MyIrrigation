import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ManagedBean
public class Browser {

    public String dashboard(){
        return "dashboard?faces-redirect=true";
    }

    public String products(){
        return "main?faces-redirect=true";
    }
    public String mainStrategy(){return "strategy?faces-redirect=true";}
    public String mainUser(){return "users?faces-redirect=true";}
    public String pieChart() {return "pieChart?faces-redirect=true";}
}
