package com.whaim.j2eetest;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;

public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        // Hello
        try {
            InitialContext icx=new InitialContext();

//            String jndi_common = String.format("ejblocal:%sCommonService#cn.cncc.cbsp.bp.common.service.CommonService",
//                    SysConfig.getProperty("cbsp.bp.ejblocal.path", "cbspbp/cbspbp.war/"));


            JndiDemo obj=(JndiDemo) icx.lookup("java:global/j2eetest-1.0/JndiDemo!com.whaim.j2eetest.JndiDemo");
            if(obj==null){
                out.println("not found object : jndidemo");
                return ;
            }
//            Method method = obj.getClass().getMethod("sayHello");

            out.println("<h1>we success find the object</h1>");

//            out.println("<h1>" + method.invoke(obj) + "</h1>");
            out.println("<h1>" + obj.sayHello() + "</h1>");
            out.println("</body></html>");

        } catch (NamingException  e) {
            throw new RuntimeException(e);
        }


    }

    public void destroy() {
    }
}