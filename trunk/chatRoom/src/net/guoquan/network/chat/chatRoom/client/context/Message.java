package net.guoquan.network.chat.chatRoom.client.context;

import java.util.Date;


public class Message {
	private User from;
	private User to;
	private Date time;
	private String message;

	public Message(User from, User to, Date time, String message) {
		super();
		this.from = from;
		this.to = to;
		this.time = time;
		this.message = message;
	}
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String toString(){
		return "[" + from + "]:    " + message;
	}
}
