package ru.andreev.chlorophytum.client.thread;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class ThreadSend extends Thread {

    private OutputStream out;
    private Scanner scanner = new Scanner(System.in);

    public ThreadSend(OutputStream out) {
        this.out = out;
    }

    @Override
    public synchronized void start() {
        try {

            out.write(setName().getBytes());
            out.flush();

            while (true) {
                String msg = scanner.nextLine();
                out.write(msg.getBytes());
                out.flush();
                if (msg.equals("/exit")) {
                    break;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    private String setName(){
        System.out.println("Enter name to be connected to chat (this name will be seen other users)");
        System.out.print("Name : ");
        String name = scanner.nextLine();

       return name;
    }
}
