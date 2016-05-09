package com.mark.frame.db;

import java.util.List;
import java.util.Map;

/**
 * Created by lulei on 2016/3/3.
 */
public interface DBExecutor {
    public int executeUpdate(String sql, List<Object> params, String baseName, Integer id);

    public List<Map<String, Object>> executeQuery(String sql, List<Object> params, String baseName, Integer id);

    public int execute(String sql, List<Object> params, String baseName, Integer id);

}
