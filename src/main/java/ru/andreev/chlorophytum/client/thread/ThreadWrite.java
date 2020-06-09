package ru.andreev.chlorophytum.client.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ThreadWrite extends Thread {

    private InputStream in;

    public ThreadWrite(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] buf = new byte[32 * 1024];
                int readBytes = in.read(buf);
                if(readBytes != -1){
                    String msg = new String(buf, 0, readBytes);
                    System.out.println("Server>>" + msg);
                }else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
