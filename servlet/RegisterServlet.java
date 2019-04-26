package com.declan.HTTPServer.servlet;

import com.declan.HTTPServer.server.Request;
import com.declan.HTTPServer.server.Response;
import com.declan.HTTPServer.servlet.Servlet;

public class RegisterServlet extends Servlet {
    @Override
    public void doGet(Request req, Response res) throws Exception {

    }

    @Override
    public void doPost(Request req, Response res) throws Exception {
        res.println("<html><head><title>Return Register</title>");
        res.println("</head><body>");
        res.println("Your user name is: ").println(req.getParameter("uname"));
        res.println("</body></html>");
    }
}
