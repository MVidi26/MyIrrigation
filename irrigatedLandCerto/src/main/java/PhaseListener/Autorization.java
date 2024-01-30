package PhaseListener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
public class Autorization implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        String pag = context.getViewRoot().getViewId();
        System.out.println(pag);
        if("/login.xhtml".equals(pag)) {
            return;
        }

        boolean contains = event.getFacesContext().getExternalContext().getSessionMap().containsKey("User");

        if(contains) {
            return;
        }
//
//        NavigationHandler handler = context.getApplication().getNavigationHandler();
//        handler.handleNavigation(context, null, "/login?faces-redirect=true");

        context.getApplication().getNavigationHandler()
                .handleNavigation(context, null, "/login?faces-redirect=true");
        context.renderResponse();
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
