package availability.ip;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server {
	private boolean running;
	private final int port;
	private ServerSocket serverSocket;
	private final List<ClientHandler> clients = Collections.synchronizedList(new LinkedList<ClientHandler>());
	
	public Server(int port) {
		this.port = port;
	}
	
	public void startServer() throws IOException {
		setRunning();
		this.serverSocket = new ServerSocket(port);
		while(isRunning()) {
			final Socket socket = serverSocket.accept();
			final ClientHandler client = new ClientHandler(socket, this);
			new Thread(client).start();
		}
		serverSocket.close();
	}
	
	public synchronized void stopServer() throws IOException {
		if (!running)
			throw new IllegalStateException("Not running");

		running = false;
		
		serverSocket.close();
		serverSocket = null;
		
		for (ClientHandler next : clients) {
			next.stopClient();
		}
	}
	
	public synchronized boolean isRunning() {
		return running;
	}
	
	private synchronized void setRunning() {
		if(running) {
			throw new IllegalStateException("Already running");
		}
		running = true;
	}
	
	public synchronized void onClientStopped(ClientHandler clientHandler) {
		clients.remove(clientHandler);
	}
}
