package com.mark.pt;

import java.io.File;
import java.util.Properties;

/**
 * Created by lulei on 2016/4/18.
 */
public class ConfigManager {

    private File file = null;
    private String filePath = "abc.txt";

    private  long lastModify = 0;

    private Properties properties = null;

    private static  ConfigManager configManager = new ConfigManager();

    private ConfigManager(){
        file = new File(filePath);
    }
}
