package net.guoquan.network.chat.chatRoom.client.information;

import java.util.ArrayList;
import java.util.List;

import net.guoquan.network.chat.chatRoom.client.ClientSession;

public class UserList {
	private ClientSession session;
	private List<User> users;
	private boolean autoSync;
	private String regex;

	public UserList(ClientSession session) {
		super();
		this.session = session;
		autoSync = false;
		regex = ".*";
		sync();
	}

	public void autoSync() {
		if (autoSync) {
			sync();
		}
	}

	public void sync() {
		users = session.users();
	}

	public void applyFilter(String regex) {
		this.regex = regex;
	}

	public List<User> getUsers() {
		autoSync();
		return users;
	}

	public Object[] getUserArray() {
		List<User> us = new ArrayList<User>(0);
		for (User u : users) {
			if (u.getUsername().matches(regex)) {
				us.add(u);
			}
		}
		return us.toArray();
	}

	public boolean isAutoSync() {
		return autoSync;
	}

	public void setAutoSync(boolean autoSync) {
		this.autoSync = autoSync;
	}

}
