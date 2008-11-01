package net.guoquan.network.chat.chatRoom.client.information;


public class User {
	private long uId;
	private String username;
	public User(long id, String username) {
		super();
		uId = id;
		this.username = username;
	}
	public long getUId() {
		return uId;
	}
	public void setUId(long id) {
		uId = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String toString(){
		return username;
	}
	public boolean equals(Object o){
		if(o.getClass() == getClass()){
			return ((User)o).getUId() == getUId();
		}
		return false;
	}
}
