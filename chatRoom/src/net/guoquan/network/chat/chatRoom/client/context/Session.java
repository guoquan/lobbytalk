package net.guoquan.network.chat.chatRoom.client.context;

import java.io.IOException;
import java.net.UnknownHostException;

import net.guoquan.network.chat.chatRoom.util.SocketReader;
import net.guoquan.network.chat.chatRoom.util.SocketWriter;


public interface Session {
	public User getUser();
	public long getId();
	public void open(long id, User user);
	public void close();
	public boolean isOpened();
	public SocketReader getIn() throws IOException;
	public SocketWriter getOut() throws IOException;
	public boolean connect(String host, int port) throws UnknownHostException, IOException;
}
