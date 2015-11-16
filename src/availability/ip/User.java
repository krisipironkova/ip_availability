package availability.ip;

import java.util.List;

public class User {
	private String username;
	private Integer count;
	private Boolean logged;
	private List<Interval> userInfo;
	
	public User(String username){
		this.username = username;
		this.logged = false;
		this.count = 0;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Boolean getLogged() {
		return logged;
	}

	public void setLogged(Boolean logged) {
		this.logged = logged;
	}

	public List<Interval> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(List<Interval> userInfo) {
		this.userInfo = userInfo;
	}
	
	public void incrementCount(){
		count += 1;
	}
	
}
