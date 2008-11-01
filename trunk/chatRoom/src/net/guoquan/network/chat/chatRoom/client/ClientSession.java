package net.guoquan.network.chat.chatRoom.client;

import java.io.IOException;
import java.util.List;

import javax.security.auth.login.LoginException;

import net.guoquan.network.chat.chatRoom.client.information.Message;
import net.guoquan.network.chat.chatRoom.client.information.User;

public interface ClientSession {
	public boolean bye() throws IOException;
	public boolean hello() throws IOException;
	public long login(String username, String password) throws IOException;
	public boolean recover(long sessionId);
	public List<User> users();
	public List<Message> news();
	public boolean post(User user, String message) throws IOException, LoginException;
	public void reset();
}
