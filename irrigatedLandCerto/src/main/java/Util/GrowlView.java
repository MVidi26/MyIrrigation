package Util;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class GrowlView implements Serializable {

    private String openAlert = "Pivô de Irrigação Ligado";
    private String warnAlert = "";
    private String closeAlert = "Pivô de Irrigação Desligado";

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showInfo() {
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", openAlert);
    }

    public void showWarn(String detail) {
        addMessage(FacesMessage.SEVERITY_WARN, "Warn Message", warnAlert.isEmpty() ? detail : warnAlert);
    }

    public void showError() {
        addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", closeAlert);
    }

    public String getOpenAlert() {
        return openAlert;
    }

    public void setOpenAlert(String openAlert) {
        this.openAlert = openAlert;
    }

    public String getWarnAlert() {
        return warnAlert;
    }

    public void setWarnAlert(String warnAlert) {
        this.warnAlert = warnAlert;
    }

    public String getCloseAlert() {
        return closeAlert;
    }

    public void setCloseAlert(String closeAlert) {
        this.closeAlert = closeAlert;
    }
}
