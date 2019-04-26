package com.declan.HTTPServer.server;

import java.util.HashMap;
import java.util.Map;

public class ServletContext {

    /**
     * ===============================
     * Name each servlet
     * like: login --> com.declan.HTTPServer.servlet.LoginServlet
     *       utl --> login
     *          /log --> login
     *          /login --> login
     * One resource has multiple paths
     * ===============================
     */
    private Map<String,String> servlet;
    private Map<String,String> mapping;

    ServletContext() {
        servlet = new HashMap<String,String>();
        mapping = new HashMap<String,String>();
    }

    public Map<String, String> getServlet() {
        return servlet;
    }

    public void setServlet(Map<String, String> servlet) {
        this.servlet = servlet;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }
}
