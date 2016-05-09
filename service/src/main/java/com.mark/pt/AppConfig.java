package com.mark.pt;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lulei on 2016/4/18.
 */
public class AppConfig {
    private String name;
    private String name2;

    public String getName() {
        return name;
    }

    public String getName2() {
        return name2;
    }

    public AppConfig() {
        readConfig();
    }

    private void readConfig() {

        Properties p = new Properties();
        InputStream in = null;
        try {
            in = AppConfig.class.getResourceAsStream("AppConfig.properties");
            p.load(in);

            this.name = p.getProperty("name");
            this.name2 = p.getProperty("name2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
