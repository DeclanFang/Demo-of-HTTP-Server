package com.declan.server;

import java.io.IOException;
import java.net.Socket;

/**
 * Build thread so that we can have multiple request and response
 * @author Declan
 */

public class Dispatcher implements Runnable{
    private Socket client;
    private Request req;
    private Response res;
    private int code = 200;

    Dispatcher(Socket client) {
        this.client = client;
        try {
            req = new Request(client.getInputStream());
            res = new Response(client.getOutputStream());
        } catch (IOException e) {
            //e.printStackTrace();
            code = 500;
            return;
        }
    }
    @Override
    public void run() {
        try {
            Servlet serv = WebApp.getServlet(req.getUrl());
            if(serv == null) {
                this.code = 404;   //Can't find handler
            } else {
                serv.service(req, res);
            }
            res.pushToClient(code);
        } catch (Exception e) {
            //e.printStackTrace();
            this.code = 500;
        }
        try {
            res.pushToClient(500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CloseUtil.closeAll(client);
    }
}
