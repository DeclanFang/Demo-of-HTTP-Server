package com.declan.HTTPServer.server;

import com.declan.HTTPServer.util.CloseUtil;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Build a HTTP server
 * @author Declan
 */

public class Server {
    private ServerSocket server = null;
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";
    private boolean isShutDown = false;

    public static void main(String[] args) {

        Server server = new Server();
        server.start();

    }

    public void start() {
        start(8888);
    }

    public void start(int port) {
        try {
            server = new ServerSocket(port);

            this.receive();
        } catch (IOException e) {
            //e.printStackTrace();
            stop();
        }
    }

    /**
     * Receive from client
     */

    private void receive() {
        try {
            while(!isShutDown) {
                new Thread(new Dispatcher(server.accept())).start();
            }
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
            stop();
        }
    }

    public void stop() {
        isShutDown = true;
        CloseUtil.closeAll(server);
    }
}
