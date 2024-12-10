 Multi-Threaded Server Project

Overview
This project demonstrates a multi-threaded server that manages multiple clients using Java. It highlights threading, synchronization, and scheduling concepts.

## How to Run
1. Clone the Repository**:
 
   git clone <repository_url>
   cd <repository_name>
 

2. **Compile the Code**:
   Ensure you have Java installed. Compile the server and client code:
     javac -d bin src/com/example/multithreadedserver/*.java


3. Run the Server:
   Start the server first:
    java -cp bin com.example.multithreadedserver.MultiThreadedServer


4. Run the Clients
   Start the clients to connect to the server:
   java -cp bin com.example.multithreadedserver.SimpleClient
   

Dependencies
Version 8 or higher.

Features
Multi-threading: Each client is managed on a separate thread.
Synchronization: Shared resources are accessed safely using locks.
Scheduling: Clients are processed in a First-Come, First-Served (FCFS) order.

Notes
- Ensure the server is running before starting the clients.
- The server listens on port `12343`. Update the client code if needed.

Example Output
- Without Synchronization: Queue corruption may occur when multiple clients connect.
- With Synchronization: Clients are processed in order and safely.


Feel free to modify the code for testing or further learning!



