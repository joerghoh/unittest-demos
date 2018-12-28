package de.joerghoh.cqdump.samples.unittest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.day.cq.replication.Replicator;


/**
 * This test class illustrates how to unit-test services using modern unittest methods
 * and frameworks.
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class ReplicationServletTest {
    
    

    @Rule
    public SlingContext  context = new SlingContext();
    
    
    @Mock
    Replicator replicatorMock;
    
    Map<String,Object> parameters = new HashMap<>();
    
    // The servlet we want to test
    ReplicationServlet servlet = new ReplicationServlet();
    
    
    /**
     * We need to register all servieces which are referenced by the ReplicationServlet,
     * otherwise it won't get activated.
     * It's sufficient to register Mocks here.
     */
    @Before
    public void setup() {
        context.registerService(Replicator.class, replicatorMock); 
    }
    
    
    
    @Test
    public void testActivate_configuredParams() {
        parameters.put("configurationValue", "/somepath");
        context.registerInjectActivateService(servlet,parameters);
        assertEquals("did not receive configured value '/somepath'",servlet.configValue, "/somepath");
    }
    
    @Test
    public void testActivate_unconfiguredParams() {
        parameters.put("configurationValue_wrongName", "/somepath");
        context.registerInjectActivateService(servlet,parameters);
        assertEquals("did not receive default value",servlet.configValue, "/content"); 
    }
    
    
    /**
     * This is a typical way how unit tests would have looked like without using SlingContext.
     */
//    @Test
//    public void testActivate_configuredParams_oldWay() {
//        parameters.put("configurationValue", "/somepath");
//        servlet.replicator = replicatorMock;
//        servlet.activate(parameters);
//        assertEquals("did not receive configured value '/somepath'",servlet.configValue, "/somepath");
//    }
    
    
}
