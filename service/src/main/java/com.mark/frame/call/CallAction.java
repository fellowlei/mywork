package com.mark.frame.call;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by lulei on 2018/3/8.
 */
public class CallAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        String beanName = req.getParameter("name");
        String methodParam = req.getParameter("method");
        String argParam = req.getParameter("args");
        try {
            Object obj = context.getBean(beanName);
            Method[] methods = obj.getClass().getMethods();
            for(Method method: methods){
                if(method.getName().equalsIgnoreCase(methodParam)){
                    Class<?> clazz = Class.forName("[Ljava.lang.Object;");
                    Object[] params = (Object[]) KryoTool.decode(argParam,clazz);
                    Object result = method.invoke(obj, params);
                    JSONArray array = new JSONArray();
                    array.add(result.getClass().getName());
                    array.add(KryoTool.encode(result));
                    String content = array.toJSONString();
                    ServletOutputStream out = resp.getOutputStream();
                    out.print(content);
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
