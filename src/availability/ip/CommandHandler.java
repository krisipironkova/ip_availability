package availability.ip;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CommandHandler {
	
	private Socket socket;
	private Map<String, User> users = new HashMap<String, User>();
	
	public CommandHandler(Socket socket) {
		this.socket = socket;
	}
	
	public String[] parseCommand(String string) {
		String[] commands = string.split(":");
		return commands;
	}
	
	public String checkCommand(String[] commands, Server server) throws IOException {
		switch(commands[0]) {
			case "login": 
				return this.login(commands);
			case "logout":
				return this.logout(commands);
			case "info": 
				return this.info(commands);
			case "listabsent":
				return this.listabsent(commands);
			case "listavailable":
				return this.listavailable(commands);
			case "shutdown":
				server.stopServer();
				break;
			default:
				return "error:unknowncommand";
		}
		return null;
	}
	
	private Boolean userExist(String name) {
		return users.containsKey(name);
	}
}