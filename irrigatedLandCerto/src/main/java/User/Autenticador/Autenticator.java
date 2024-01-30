package User.Autenticador;
import User.Model.User;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.application.NavigationHandler;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;

public class Autenticator implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent phaseEvent) {

        FacesContext context = phaseEvent.getFacesContext();
        String pag = context.getViewRoot().getViewId();
        System.out.println(pag);
        boolean contains = pag.contains("/login.xhtml");
        if(contains) {
            return;
        }

        User userLoged = (User) context.getExternalContext().getSessionMap().get("User");
        if(userLoged != null) {
            return;
        }

        NavigationHandler handler = context.getApplication().getNavigationHandler();
        handler.handleNavigation(context, null, "/login?faces-redirect=true");
        context.renderResponse();
    }


    @Override
    public void beforePhase(PhaseEvent phaseEvent) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
