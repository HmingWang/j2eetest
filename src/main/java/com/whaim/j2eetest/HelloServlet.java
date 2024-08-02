package com.whaim.j2eetest;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;

@Stateless
public class HelloServlet extends HttpServlet {
    private String message;

    @EJB
    private JndiDemo demo;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

//        Hashtable env = new Hashtable();
//        env.put(Context.INITIAL_CONTEXT_FACTORY,
//                "com.ibm.websphere.naming.WsnInitialContextFactory");
//        env.put(Context.PROVIDER_URL, "iiop://localhost:2809");


        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        try {
            InitialContext icx=new InitialContext();

            JndiDemo obj= (JndiDemo) icx.lookup("ejblocal:j2eeapp/j2eetest-1.0.war/JndiDemo#com.whaim.j2eetest.JndiDemo");
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