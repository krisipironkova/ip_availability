package availability.ip;

import java.util.List;
import java.net.Socket;
import java.util.ArrayList;

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
	}