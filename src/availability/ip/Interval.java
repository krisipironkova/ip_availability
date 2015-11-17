package availability.ip;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Interval {
	private Date from, to;
	
	public Interval(Date from) {
		this.from = from;
	}
	
	public void setTo(Date to) {
		this.to = to;
	}

	private String formatDate(Date date) {
		return new SimpleDateFormat("yyyy-­MM-dd'T'HH'_'mm'_'ss.SSSZ").format(date);
	}
	
	public String from() {
		return this.formatDate(this.from);
	}
	
	public String to() {
		return this.to != null ? this.formatDate(this.to) : null;
	}
	
}
