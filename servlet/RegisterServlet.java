package com.declan.server;

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
