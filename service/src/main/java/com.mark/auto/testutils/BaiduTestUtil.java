package com.mark.auto.testutils;

import com.mark.auto.testutils.core.AbstractURLTestUtil;

/**
 * Created by lulei on 2016/5/24.
 */
public class BaiduTestUtil {
    /**
     * 促销标签前端
     *
     * @throws Exception
     */
    public static void testBaiduURL() throws Exception {
        AbstractURLTestUtil.switchHostTestURL("www.baidu.com", "http://www.baidu.com");
    }
}
