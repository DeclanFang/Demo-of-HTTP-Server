package com.declan.server;

import java.util.HashMap;
import java.util.Map;

public class ServletContext {

    /**
     * ============================
     * Name each servlet
     * like: login --> LoginServlet
     *       utl --> login
     *          /log --> login
     *          /login --> login
     * 一个资源有多个路径
     * ============================
     */
    private Map<String,Servlet> servlet;
    private Map<String,String> mapping;

    ServletContext() {
        servlet = new HashMap<String,Servlet>();
        mapping = new HashMap<String,String>();
    }

    public Map<String, Servlet> getServlet() {
        return servlet;
    }

    public void setServlet(Map<String, Servlet> servlet) {
        this.servlet = servlet;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }
}
