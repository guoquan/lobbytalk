package net.guoquan.network.chat.chatRoom.server.context;

import java.util.List;
import java.util.Set;

public interface Context {
	public boolean post(Message message);
	public boolean login(Session session);
	public boolean logout(Session session);
	public boolean drop(Session session);
	public boolean recover(Session session);
	public List<User> users();
	public Set<Session> sessions();
	public Session getSession(long id);
	public Session getSessionByUser(User user);
	public Session getSessionByUser(long uId);
}
