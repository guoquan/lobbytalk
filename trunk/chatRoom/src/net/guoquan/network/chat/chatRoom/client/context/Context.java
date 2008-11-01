package net.guoquan.network.chat.chatRoom.client.context;

import java.util.List;

public interface Context {
	public User getUser(long uId);
	public void setUsers(List<User> list);
}
