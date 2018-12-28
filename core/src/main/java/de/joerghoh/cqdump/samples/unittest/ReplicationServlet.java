package de.joerghoh.cqdump.samples.unittest;

//import java.util.Map;

import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
//import org.apache.sling.commons.osgi.OsgiUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import com.day.cq.replication.Replicator;

@Component
@Designate(ocd=ReplicationServlet.Config.class)
public class ReplicationServlet extends SlingSafeMethodsServlet {
    
    @ObjectClassDefinition
    public @interface Config {
        
        public String configurationValue() default "/content";
        
    }

    @Reference
    Replicator replicator;
    
    public String configValue;
    
    
    @Activate
    public void activate(Config config) {
        configValue  = config.configurationValue();
    }
    
//    @Activate
//    public void activate (Map<String,Object> parameters) {
//        setting = OsgiUtil.toString(parameters.get("configurationValue"), "/content");
//    }
    
    
}
