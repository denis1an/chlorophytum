package ru.andreev.chlorophytum.client;


import ru.andreev.chlorophytum.client.thread.ThreadRead;
import ru.andreev.chlorophytum.client.thread.ThreadWrite;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final String HOST = "localhost";

    private final int PORT = 18000;

    public Client() {
        Scanner scanner = new Scanner(System.in);
        Socket socket;
        try {
            socket = new Socket(HOST,PORT);
            System.out.println("Connected to server...");

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            ThreadRead threadRead = new ThreadRead(out);
            ThreadWrite threadWrite = new ThreadWrite(in);

            threadWrite.start();
            threadRead.start();

            /*Thread readMessage = new Thread(new Runnable() {
                @Override
                    public void run() {
                    while (true) {
                        try {
                            byte[] buf = new byte[32 * 1024];
                            int readBytes = in.read(buf);
                            if(readBytes != -1){
                                String msg = new String(buf, 0, readBytes);
                                System.out.println("Server>" + msg);
                            }else {
                                break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                });


            Thread sentMessage = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        String msg = scanner.nextLine();
                        try {
                            out.write(msg.getBytes());
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(msg.equals("/exit")){
                            break;
                        }
                    }
                }
            });

            readMessage.start();
            sentMessage.start();*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


