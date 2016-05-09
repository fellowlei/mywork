package com.mark.frame.http.core;

import java.util.Map;

/**
 * Created by lulei on 2016/4/28.
 */
public interface UserHttpMethod {
    public Map<String, String> getHeaderMap();

    public Map<String, String> getFormMap();

    public String getURL();
}
