package org.profilematch.pmcore.ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.File;
import java.nio.file.Files;

/**
 * Created by antoine on 3/3/17.
 */
@Startup
@Singleton
public class IO {
    private String status;

    @PostConstruct
    void init() {

        File f = new File("images");

        if (!f.exists()) {
            f.mkdir();
        }
    }
}

