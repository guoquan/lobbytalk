package net.guoquan.network.chat.chatRoom.client.context;

import java.util.List;

public class ClientSessionContext implements Context {
	private List<User> users;
	public User getUser(long uId) {
		for(User u : users){
			if(u.getUId() == uId)
				return u;
		}
		return null;
	}

	public void setUsers(List<User> list) {
		users = list;
	}
	
	public ClientSessionContext(Session session){
		this.session = session;
	}
	
	private Session session;

	public Session getSession() {
		return session;
	}

	public void openSession(long id, User user) {
		session.open(id, user);
	}
	
}
