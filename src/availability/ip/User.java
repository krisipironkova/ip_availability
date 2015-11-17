package availability.ip;

import java.util.List;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

	public class User {
		private String username;
		private boolean logged;
		private Integer loginCount;
		private Socket socket;
		private List<Interval> loginHistory = new ArrayList<Interval>();
		
		public User(String username, Socket socket) {
			this.username = username;
			this.loginCount = 0;
			this.logged = false;
			this.socket = socket;
		}
		public Socket getSocket() {
			return socket;
		}
		
		public String login() {
			if(!isLogged()) {	
				this.logged = true;
				this.loginCount++;
				this.loginHistory.add(new Interval(new Date()));
				return "ok";
			} return "error:alreadyloggedin";
		}
		
		public String logout() {
			if(isLogged()) {
				this.logged = false;
				this.loginHistory.get(this.loginHistory.size() - 1).setTo(new Date());
				return "ok";
			} return "error:alreadyloggedout";
		}
		
		public String getName() {
			return this.username;
		}
		
		public int getLoginCounts() {
			return this.loginCount;
		}
		
		public boolean isLogged() {
			return this.logged;
		}
		
		public String info() {
			String info =  "ok:" + getName() + ":" + isLogged() + ":" + getLoginCounts();
			
			for (Interval temp_list: this.loginHistory) {
				info += ":" + temp_list.from();
				
				if (temp_list.to() != null) info += ":" + temp_list.to();
			}
			
			return info;
		}
	}