package de.joerghoh.cqdump.samples.unittest.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.ModelFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit.AemContext;

@RunWith(MockitoJUnitRunner.class)
public class NavigationModelTest {

    
    @Rule
    public AemContext context = new AemContext();
    
    
    @Before
    public void setup() {
        context.load().json(NavigationModelTest.class.getResourceAsStream("NavigationModelTest.json"), "/content");
    }
    
    
    @Test
    public void test_site1_ok() {
        
        Resource r = context.resourceResolver().getResource("/content/site1/page1/jcr:content");
        assertNotNull(r);
        NavigationModel navigationModel = r.adaptTo(NavigationModel.class);
        List<Page> navigationItems = navigationModel.getNavigationItems();
        assertEquals(3,navigationItems.size());
        
    }
    
    @Test
    public void test_site2_SiteRootSetToFalse() {
        Resource r = context.resourceResolver().getResource("/content/site2/page1/jcr:content");
        assertNotNull(r);
        NavigationModel navigationModel = r.adaptTo(NavigationModel.class);
        List<Page> navigationItems = navigationModel.getNavigationItems();
        assertNull (navigationItems);
    }
    
}
