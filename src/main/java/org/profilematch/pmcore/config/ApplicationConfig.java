package org.profilematch.pmcore.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.profilematch.pmcore.config.CorsFeature.class);
        resources.add(org.profilematch.pmcore.rest.CandidatRest.class);
        resources.add(org.profilematch.pmcore.rest.CompetenceRest.class);
        resources.add(org.profilematch.pmcore.rest.LinkedinRest.class);
        resources.add(org.profilematch.pmcore.rest.MailRest.class);
        resources.add(org.profilematch.pmcore.rest.MatchingRest.class);
        resources.add(org.profilematch.pmcore.rest.RecruteurRest.class);
        resources.add(org.profilematch.pmcore.rest.UtilisateurRest.class);
    }
    
}
