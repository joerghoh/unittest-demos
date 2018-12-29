package de.joerghoh.cqdump.samples.unittest.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;


/**
 * This is a very simple implementatino of a site navigation model for demo purposes.
 * 
 * <ul>
 *   <li>The root page of the site navigation is determined by the page property "siteRoot = true"</li>
 *   <li>All pages below the root page are added to the navigation</ul>
 * </ul>
 * 
 * 
 *
 */

@Model(adaptables=Resource.class)
public class NavigationModel {

    
    protected final static String PROP_SITEROOT = "siteRoot";
    
    @Self
    Resource resource;
    
    @Inject
    ResourceResolver resolver;
    
    List<Page> navigationPages = null;
    
    
    @PostConstruct
    protected void init() {
        PageManager pm = resolver.adaptTo(PageManager.class);
        Page currentPage = pm.getContainingPage(resource);
        Page siteRoot = detectSiteRootPage(currentPage);
        if (siteRoot != null) {
            navigationPages = getNavigationItems(siteRoot);
        }
    }
    
    
    // determine the root node of the website
    protected Page detectSiteRootPage(Page page) {
        
        ValueMap pageProperties = page.getProperties();
        if (pageProperties.containsKey(PROP_SITEROOT)) {
            String value = pageProperties.get(PROP_SITEROOT, String.class);
            if ("true".equals(value)) {
                return page;
            }
        }
        // check for the parent page
        if (page.getParent() != null) {
           return detectSiteRootPage(page.getParent());
        } 
        
        // we reached the root node
        return null;
    }
    
    // iterate through all childpages and add them to the navigation
    protected List<Page> getNavigationItems(Page page) {
        List<Page> ret = new ArrayList<>();
        page.listChildren().forEachRemaining(p -> ret.add(p));
        return ret;
    }
    
    
    public List<Page> getNavigationItems() {
        return navigationPages;
    }
    
    
    
    
}
