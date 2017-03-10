package org.profilematch.pmcore.config;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.File;

/**
 * Created by antoine on 3/3/17.
 */
@Startup
@Singleton
public class IO {
    private String status;
    private static boolean prod = false;

    @PostConstruct
    void init() {

        File f = new File("images");

        if (!f.exists()) {
            f.mkdir();
        }
    }
    
    public static boolean getProd(){
        return prod;
    }
}

