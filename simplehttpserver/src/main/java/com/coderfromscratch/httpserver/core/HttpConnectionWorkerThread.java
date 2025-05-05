package com.coderfromscratch.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);

    private final Socket socket;

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

           int _byte;
           while ( ( _byte = inputStream.read()) >=0);

            String html = "<html><head><title>Simple Java HTTP Server</title></head>" +
                    "<body><h1>This page was served using my Simple HTTP Server</h1></body></html>";

            final String CRLF = "\r\n";

            String response =
                    "HTTP/1.1 200 OK" + CRLF +
                            "Content-Length: " + html.getBytes().length + CRLF +
                            "Content-Type: text/html" + CRLF +
                            CRLF +
                            html + CRLF;

            outputStream.write(response.getBytes());
            inputStream.close();
            outputStream.close();
            socket.close();

            logger.info(" * Connection Processing Finished");
        } catch (IOException e) {
            throw new RuntimeException("Server error", e);
        }
    }
}
