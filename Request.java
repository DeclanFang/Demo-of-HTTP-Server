package com.declan.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Encapsulate Request
 * @author Declan
 */

public class Request {
    // Request method
    private String method;
    // Request resource
    private String url;
    // Request elements
    private Map<String, List<String>> parameterMapValues;

    public static final String CRLF = "\r\n";
    private InputStream is;
    private String requestInfo;

    public Request() {
        method = "";
        url = "";
        parameterMapValues = new HashMap<String, List<String>>();
        requestInfo = "";
    }

    public Request(InputStream is) {
        this();
        this.is = is;
        try {
            byte[] data = new byte[20480];
            int len = is.read(data);
            requestInfo = new String(data,0,len);
        } catch (IOException e) {
            return;
        }
        parseRequestInfo();
    }

    private void parseRequestInfo() {
        if(requestInfo == null || (requestInfo = requestInfo.trim()).equals("")) {
            return;
        }

        /**
         * =================================================================
         * We can parse such information from the head of the RequestInfo:
         * Request Method
         * Request Path
         * Request Parameters(may exist in the GET method)
         *
         * The head looks like：
         * GET /index.html?name=Declan&pwd=123456 HTTP/1.1
         *
         * If it's POST method, the parameters may exist in the last context
         * =================================================================
         */
        String paramString = "";
        String firstLine = requestInfo.substring(0,requestInfo.indexOf(CRLF));
        int idx = requestInfo.indexOf("/");

        this.method = firstLine.substring(0,idx).trim();
        String urlStr = firstLine.substring(idx,firstLine.indexOf("HTTP/")).trim();

        if(this.method.equalsIgnoreCase("post")) {
            this.url = urlStr;
            paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
        }
        else if(this.method.equalsIgnoreCase("get")) {
            if(urlStr.contains("?")) {   // Check if the GET method have the parameters
                String[] urlArray = urlStr.split("\\?");
                this.url = urlArray[0];
                paramString = urlArray[1];
            }
            else {
                this.url = urlStr;
            }
        }

        //Store the parameters into the Map
        if(paramString.equals("")) {
            return;
        }
        parseParams(paramString);
    }

    private void parseParams(String paramString) {
        StringTokenizer token = new StringTokenizer(paramString,"&");
        while(token.hasMoreTokens()) {
            String keyValue = token.nextToken();
            String[] keyValues = keyValue.split("=");
            if(keyValues.length == 1) {
                keyValues = Arrays.copyOf(keyValues, 2);
                keyValues[1] = null;
            }

            String key = keyValues[0].trim();
            String value = keyValues[1] == null ? null : decode(keyValues[1].trim(),"gbk");
            if(!parameterMapValues.containsKey(key)) {
                parameterMapValues.put(key, new ArrayList<String>());
            }

            List<String> values = parameterMapValues.get(key);
            values.add(value);
        }
    }

    private String decode(String value, String code) {
        try {
            return java.net.URLDecoder.decode(value, code);
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public String[] getParameterValues(String name) {
        List<String> values = null;
        if((values = parameterMapValues.get(name)) == null) {
            return null;
        }
        else {
            return values.toArray(new String[0]);
        }
    }

    /**
     * Get certain value
     */
    public String getParameter(String name) {
        String[] values = getParameterValues(name);
        if(values == null) {
            return null;
        }
        return values[0];
    }

    public String getUrl() {
        return url;
    }
}
