package ru.andreev.chlorophytum.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private final int PORT = 18000;

    private ArrayList<ClientHandler> clients = new ArrayList<>();

    public Server() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started...");

            while (true){

                Socket socket = serverSocket.accept();

                System.out.println("Connected " + socket.getInetAddress());

                clients.add(new ClientHandler(socket, this));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sentAllClients(String message, ClientHandler clientHandler){
        for (ClientHandler client : clients){
            if(!client.equals(clientHandler)) {
                client.sendMessage(message);
            }
        }
    }

    public void sentAllClients(String message){
        for (ClientHandler client : clients){
                client.sendMessage(message);
        }
    }

    public void remove(ClientHandler client){
        clients.remove(client);
        sentAllClients(client.getClientName() + "disconnected ...");
    }

}
