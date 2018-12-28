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


/**
 * This is a sample class to demonstrate unit tests with AEM; it does not have a real purpose,
 * but should just illustrate some basics. For this reason it does not carry the full set of 
 * properties to act as a servlet (for example it lacks the binding to a specific resource type, 
 * and also the doGet() method is not extended), but that's ok for the purpose of demonstration right
 * now.
 * 
 * It is using purely OSGI r6 annotations and and does not rely on the SCR annotations anymore.
 *
 *
 */


@Component
@Designate(ocd=ReplicationServlet.Config.class)
public class ReplicationServlet extends SlingSafeMethodsServlet {
    
    @ObjectClassDefinition
    public @interface Config {
        
        public String configurationValue() default "/content";
        
    }

    
    /**
     * Just referencing a service from the product.
     */
    
    @Reference
    private Replicator replicator;
    
    public String configValue;
    
    
    @Activate
    public void activate(Config config) {
        configValue  = config.configurationValue();
    }
    
    
    /**
     * This is the "old way" (just using Felix SCR annotations) how to activate method was implemented
     */
    
//    @Activate
//    public void activate (Map<String,Object> parameters) {
//        setting = OsgiUtil.toString(parameters.get("configurationValue"), "/content");
//    }
    
    
}
