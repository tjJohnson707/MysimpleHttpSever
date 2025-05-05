package com.coderfromscratch.httpserver.core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                logger.info(" * Connection accepted: {}", socket.getInetAddress());

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

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
            }
        } catch (IOException e) {
            throw new RuntimeException("Server error", e);
        }
    }
}

