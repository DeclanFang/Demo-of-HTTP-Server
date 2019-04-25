package com.declan.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Build a HTTP server
 * @author Declan
 */

public class Server {
    private ServerSocket server = null;
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";
    public static void main(String[] args) {

        Server server = new Server();
        server.start();

    }

    public void start() {
        try {
            server = new ServerSocket(8888);

            this.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Receive from client
     */

    private void receive() {
        try {
            Socket client = server.accept();

            //Request
            Request req = new Request(client.getInputStream());


            //Response
            Response res = new Response(client.getOutputStream());
            res.println("<html><head><title>HTTP Example</title>");
            res.println("</head><body>Hello Tomcat! </body></html>");
            res.pushToClient(500);

            /*
            StringBuilder response = new StringBuilder();
            // HTTP Protocol Version、State Code、Description
            response.append("HTTP/1.1").append(BLANK).append("200").append(BLANK).append("OK").append(CRLF);
            // Response Head
            response.append("Server:Fonzie Server/0.0.1").append(CRLF);
            response.append("Date:").append(new Date()).append(CRLF);
            response.append("Content-type:text/html;charset=GBK").append(CRLF);
            response.append("Content-Length:").append(responseContext.toString().getBytes().length).append(CRLF);   //字节长度
            // Context
            response.append(CRLF);
            response.append(responseContext);

            // Output stream
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(response.toString());
            bw.flush();
            bw.close();
            */

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public void stop() {

    }
}
