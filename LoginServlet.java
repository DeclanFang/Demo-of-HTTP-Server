package com.declan.server;

public class LoginServlet extends Servlet {
    @Override
    public void doGet(Request req, Response res) throws Exception {
//        res.println("<html><head><title>HTTP Welcome</title>");
//        res.println("</head><body>");
//        res.println("Welcome: ").println(req.getParameter("uname")).println(" back");
//        res.println("</body></html>");
        String name = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        if(login(name,pwd)) {
            res.println("Login success");
        }
        else {
            res.println("Login fail");
        }
    }

    public boolean login(String name, String pwd) {
        return name.equals("Declan") && pwd.equals("123456");
    }

    @Override
    public void doPost(Request req, Response res) throws Exception {

    }
}
