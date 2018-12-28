package de.joerghoh.cqdump.samples.unittest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.day.cq.replication.Replicator;

@RunWith(MockitoJUnitRunner.class)
public class ReplicationServletTest {
    
    
    

    @Rule
    public SlingContext  context = new SlingContext();
    
    @Mock
    Replicator replicatorMock;
    
    Map<String,Object> parameters = new HashMap<>();
    ReplicationServlet servlet = new ReplicationServlet();
    
    
    @Before
    public void setup() {
        context.registerService(Replicator.class, replicatorMock); 
    }
    
    
    
    @Test
    public void testActivate_configuredParams() {
        parameters.put("configurationValue", "/somepath");
        context.registerInjectActivateService(servlet,parameters);
        assertEquals(servlet.configValue, "/somepath");
    }
    
    @Test
    public void testActivate_unconfiguredParams() {
        parameters.put("configurationValue_wrongName", "/somepath");
        context.registerInjectActivateService(servlet,parameters);
        assertEquals(servlet.configValue, "/content"); // expect the default value
    }
    
    
    @Test
    public void testOldActivationMethod() {
        
    }
    
    
}
