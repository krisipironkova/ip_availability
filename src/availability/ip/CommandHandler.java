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
	
	private Boolean userExist(String name) {
		return users.containsKey(name);
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
			case "listavailable":
				return this.listavailable(commands);
			case "listabsent":
				return this.listabsent(commands);
			case "shutdown":
				server.stopServer();
				break;
			default:
				return "error:unknowncommand";
		}
		return null;
	}
	
	private String login(String[] commands) {
		if(!this.userExist(commands[1])) {
			users.put(commands[1], new User(commands[1], socket));
		} return users.get(commands[1]).login();
	}
	
	private String logout(String[] commands) {
		return this.userExist(commands[1]) ? users.get(commands[1]).logout() 
				: "error:alreadyloggedout";
	}
	
	private String info(String[] commands) {
		return this.userExist(commands[1]) ? users.get(commands[1]).info() 
				: "error:noinfo";
	}
	
	private String listavailable(String[] commands) {
		if(this.userExist(commands[1])) {
			String string = "ok";
			for (Entry<String, User> entry : users.entrySet()) {
			    if(entry.getValue().isLogged()) 
			    	string += ":" + entry.getKey();
			}
			return string;
		} return "error:notlogged";
	}
	
	private String listabsent(String[] commands) {
		if(this.userExist(commands[1])) {
			String string = "ok";
			
			for (Entry<String, User> entry : users.entrySet())
			    if(!entry.getValue().isLogged()) string += ":" + entry.getKey();
			
			return string;
		} return "error:notlogged";
	}
}