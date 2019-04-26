package com.declan.HTTPServer.server;

import com.declan.HTTPServer.servlet.Servlet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;
import java.util.Map;

public class WebApp {
    private static ServletContext context;
    static {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser sax = factory.newSAXParser();

            WebHandler webHandler = new WebHandler();
            sax.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/declan/WEB_INFO/web.xml"),webHandler);

            // List --> Map
            context = new ServletContext();

            // servlet-name, servlet-class
            Map<String,String> servlet = context.getServlet();
            for(Entity entity : webHandler.getEntityList()) {
                servlet.put(entity.getName(), entity.getClz());
            }

            // url-pattern, servlet-name
            Map<String,String> mapping = context.getMapping();
            for(Mapping mapping1 : webHandler.getMappingList()) {
                List<String> urls = mapping1.getUrlPattern();
                for(String url : urls) {
                    mapping.put(url, mapping1.getName());
                }
            }
        } catch (Exception e) {

        }


    }

    public static Servlet getServlet(String url) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if((url = url.trim()).equals("") || url == null) {
            return null;
        }

        // Use the whole path to create object
        String name = context.getServlet().get(context.getMapping().get(url));
        return (Servlet)Class.forName(name).newInstance();
    }
}
