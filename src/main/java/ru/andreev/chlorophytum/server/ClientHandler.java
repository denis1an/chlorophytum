package ru.andreev.chlorophytum.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final Server server;
    private final Socket socket;
    private final InputStream in;
    private final OutputStream out;
    private String name;
    public ClientHandler(Socket socket, Server server) throws IOException {
        this.server = server;
        this.socket = socket;
        in = socket.getInputStream();
        out = socket.getOutputStream();
        start();
    }

    @Override
    public void run() {
        try {
            name = convertByteToString();
            name = "[" + name + "]";
            server.sentAllClients(name + " connected to server ...");
            while (true){
                String message = convertByteToString();
                if(message.equalsIgnoreCase("/exit")){
                    break;
                }
                server.sentAllClients(name+message, this);
                System.out.println(message);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        this.close();
    }

    public void sendMessage(String line){
        try {
            out.write(line.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.remove(this);
        }
    }

    private String convertByteToString() throws IOException {
        byte[] data = new byte[32 * 1024];
        int readBytes = in.read(data);
        return new String(data,0,readBytes);
    }
}
