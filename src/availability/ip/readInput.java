package availability.ip;

public class readInput {
	private String command;

	public readInput(String input) {
		this.command = input;
	}
	
	private String[] split_input() {
		return this.command.split(":");
	}
	
	public String get_name() {
		return this.split_input()[0];
	}
	
	public String get_command() {
		return this.split_input()[1];
	}
	
	public String get_info_name(){
		return this.split_input()[2];
	}
}
