package ru.andreev.chlorophytum.client.thread;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class ThreadRead extends Thread {

    private OutputStream out;
    private Scanner scanner = new Scanner(System.in);

    public ThreadRead(OutputStream out) {
        this.out = out;
    }

    @Override
    public synchronized void start() {
        while (true){
            String msg = scanner.nextLine();
            try {
                out.write(msg.getBytes());
                out.flush();
                if(msg.equals("/exit")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
