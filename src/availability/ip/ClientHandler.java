package availability.ip;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
	
	private final Socket socket;
	private final Server Server;
	private final CommandHandler commandHandler;
	
	public ClientHandler(Socket socket, Server Server) {
		this.socket = socket;
		this.Server = Server;
		this.commandHandler = new CommandHandler(socket);
	}
	
	@Override
	public void run() {
		try {
			final PrintStream out = new PrintStream(socket.getOutputStream());
			final Scanner scanner = new Scanner(socket.getInputStream());
			
			while(scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				out.println(commandHandler.checkCommand(commandHandler.parseCommand(line), Server));				
			}
			scanner.close();
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			Server.onClientStopped(this);
		}
	}
	
	public void stopClient() throws IOException {
		socket.close();
	}
}