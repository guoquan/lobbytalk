package net.guoquan.network.chat.chatRoom.server.context;

import java.util.UUID;

public class User {
	private String username;
	private String password;
	private long uid;
	private Session session;
	public User(String username, String password, Session session) {
		super();
		this.username = username;
		this.password = password;
		UUID uuid = UUID.randomUUID();
		this.session = session;
		this.session.open(uuid.getMostSignificantBits(), this);
		uid = uuid.getLeastSignificantBits();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public boolean equals(Object o){
		return getClass().equals(o.getClass()) && uid == ((User)o).getUid();
	}
	public String toString(){
		return username;
	}
}
