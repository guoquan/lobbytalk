package net.guoquan.network.chat.chatRoom.client.context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class UserList {
	private ClientSessionHandler handler;
	private List<User> users;
	private boolean autoSync;
	private String regex;

	public UserList(ClientSessionHandler handler) throws LoginException, IOException {
		super();
		this.handler = handler;
		autoSync = false;
		regex = ".*";
		sync();
	}

	public void autoSync() throws LoginException, IOException {
		if (autoSync) {
			sync();
		}
	}

	public void sync() throws LoginException, IOException {
			users = handler.users();
	}

	public void applyFilter(String regex) {
		this.regex = regex;
	}

	public List<User> getUsers() throws LoginException, IOException {
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
