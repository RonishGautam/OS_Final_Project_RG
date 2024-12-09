package com.example.multithreadedserver;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

public class MultiThreadedServer {
    private static final int PORT = 12343; // Server port
    private static final Queue<String> clientQueue = new LinkedList<>(); // Queue for client names
    private static final Object lock = new Object(); // Lock for synchronization

    public static void main(String[] args) {
        System.out.println("Server is running on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept new client connection
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Create and start a new thread to handle the client
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                // Get the client's name
                String clientName = in.readLine();
                System.out.println("Client name received: " + clientName);

                // Add client to the queue (Critical Section)
                synchronized (lock) {
                    clientQueue.add(clientName);
                    System.out.println(clientName + " added to the queue.");
                }

                // Notify client
                out.println("Hello " + clientName + ", you are in the queue.");

                // Simulate some processing time
                Thread.sleep(1000);

                // Process client's turn
                synchronized (lock) {
                    if (!clientQueue.isEmpty() && clientQueue.peek().equals(clientName)) {
                        clientQueue.poll();
                        out.println("It's your turn, " + clientName + "!");
                        System.out.println(clientName + " is being served.");
                    }
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
