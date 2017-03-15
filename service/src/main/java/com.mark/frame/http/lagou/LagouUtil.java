package com.mark.frame.http.lagou;

import com.jayway.jsonpath.JsonPath;
import com.mark.frame.http.core.v2.DefaultCookieUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by lulei on 2017/3/15.
 */
public class LagouUtil {


    public static void parseJson(String json) {
        List<String> list = JsonPath.read(json, "$.content.positionResult.result[*].companyFullName");
        for (String str : list) {
            System.out.println(str);
        }
        try {
            FileUtils.writeLines(new File("company.txt"), list, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dumpResult(String json) {
        try {
            FileUtils.write(new File("lagou.txt"), json + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        DefaultCookieUtil.setCookie("user_trace_token=20170113101855-37d95de2f5a341e89bdbd45e8fcf08c4; LGUID=20170113101856-a2d2d5ae-d936-11e6-8bef-5254005c3644; JSESSIONID=105F5676D33F3E11AE4DB6A514B304BD; index_location_city=%E5%8C%97%E4%BA%AC; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1489580932; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1489580948; _ga=GA1.2.593802149.1484273936; LGSID=20170315202914-002a9404-097b-11e7-9452-5254005c3644; PRE_UTM=; PRE_HOST=; PRE_SITE=https%3A%2F%2Fwww.lagou.com%2F; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2Fjobs%2Flist_java%3FlabelWords%3D%26fromSearch%3Dtrue%26suginput%3D; LGRID=20170315202914-002a9647-097b-11e7-9452-5254005c3644; TG-TRACK-CODE=search_code; SEARCH_ID=c657c36748bb460ca3d23ed561b8286f");
        String result = DefaultCookieUtil.post("https://www.lagou.com/jobs/positionAjax.json?city=%E5%8C%97%E4%BA%AC&needAddtionalResult=false", "first=true&pn=1&kd=java");
        dumpResult(result);


    }
}
