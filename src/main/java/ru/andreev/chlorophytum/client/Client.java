package ru.andreev.chlorophytum.client;


import ru.andreev.chlorophytum.client.thread.ThreadSend;
import ru.andreev.chlorophytum.client.thread.ThreadWrite;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    private final String HOST = "localhost";

    private final int PORT = 18000;

    public Client() {

        Socket socket;
        try {
            socket = new Socket(HOST,PORT);

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            ThreadSend threadSend = new ThreadSend(out);
            ThreadWrite threadWrite = new ThreadWrite(in);

            threadWrite.start();
            threadSend.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


