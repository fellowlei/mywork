package com.mark.framework.httputil.core;

import java.util.Map;

/**
 * Created by Administrator on 2016/5/6.
 */
public interface UserHttpMethod {
    public Map<String, String> getHeaderMap();

    public Map<String, String> getFormMap();

    public String getURL();
}

