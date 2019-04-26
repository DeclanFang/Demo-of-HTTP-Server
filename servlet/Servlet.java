package com.declan.HTTPServer.servlet;

import com.declan.HTTPServer.server.Request;
import com.declan.HTTPServer.server.Response;

public abstract class Servlet {
    public void service(Request req, Response res) throws Exception {
        this.doGet(req,res);
        this.doPost(req,res);
    }

    protected abstract void doGet(Request req, Response res) throws Exception;
    protected abstract void doPost(Request req, Response res) throws Exception;
}
