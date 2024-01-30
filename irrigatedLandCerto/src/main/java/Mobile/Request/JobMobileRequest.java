package Mobile.Request;

import com.google.gson.annotations.SerializedName;

import javax.annotation.PostConstruct;
import java.io.Serializable;

public class JobMobileRequest implements Serializable {

    @SerializedName("start")
    private boolean start;

    public JobMobileRequest() {
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
