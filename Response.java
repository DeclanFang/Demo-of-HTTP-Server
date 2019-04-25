package com.declan.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Encapsulate Response
 * @author Declan
 */

public class Response {
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";

    private BufferedWriter bw;

    private StringBuilder content;
    private StringBuilder headInfo;

    private int len = 0;

    public Response() {
        headInfo = new StringBuilder();
        content = new StringBuilder();
        len = 0;
    }
    public Response(Socket client) {
        this();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            headInfo = null;
        }
    }
    public Response(OutputStream os) {
        this();
        bw = new BufferedWriter(new OutputStreamWriter(os));
    }

    /**
     * Build the main content
     */
    public Response print(String info) {
        content.append(info);
        len += info.getBytes().length;
        return this;
    }

    /**
     * Build the main content with return
     */
    public Response println(String info) {
        content.append(info).append(CRLF);
        len += (info + CRLF).getBytes().length;
        return this;
    }

    /**
     * Build response head
     */
    private void createHeadInfo(int code) {
        // HTTP Protocol Version, State Code, Description
        headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
        switch(code) {
            case 200:
                headInfo.append("OK");
                break;
            case 404:
                headInfo.append("NOT FOUND");
                break;
            case 500:
                headInfo.append("SERVER ERROR");
                break;
        }
        headInfo.append(CRLF);
        // Response Head
        headInfo.append("Server:Fonzie Server/0.0.1").append(CRLF);
        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
        headInfo.append("Content-Length:").append(len).append(CRLF);
        headInfo.append(CRLF);
    }

    //Push to client
    void pushToClient(int code) throws IOException {
        if(headInfo == null) {
            code = 500;
        }
        createHeadInfo(code);
        bw.append(headInfo.toString());
        bw.append(content.toString());
        bw.flush();
    }

    public void close() {
        CloseUtil.closeAll(bw);
    }
}
