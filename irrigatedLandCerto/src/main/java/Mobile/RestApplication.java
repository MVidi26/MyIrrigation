package Mobile;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("ws")
public class RestApplication extends Application {

    public RestApplication() {
    }

    @Override
    public Set<Class<?>> getClasses(){
        Set<Class<?>>resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        addFilterAndInterceptors(resources);
        return resources;
    }

    private void addFilterAndInterceptors(Set<Class<?>> resources) {
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {

        resources.add(Login.class);
        resources.add(JobMobile.class);
    }

}
