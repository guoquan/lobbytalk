package net.guoquan.network.chat.chatRoom.client.context;

import java.io.IOException;
import java.net.Socket;

import net.guoquan.network.chat.chatRoom.util.SocketReader;
import net.guoquan.network.chat.chatRoom.util.SocketWriter;

public class ClientSession implements Session {
	private long id;
	private User user;
	private boolean isOpen;
	private Socket socket;
	private SocketReader in;
	private SocketWriter out;
	public void close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// do nothing
		} finally{
			in = null;
			out = null;
			socket = null;

			isOpen = false;	
		}
	}

	public long getId() {
		return id;
	}

	public SocketReader getIn() {
		return in;
	}

	public SocketWriter getOut() {
		return out;
	}

	public User getUser() {
		return user;
	}

	public boolean isOpened() {
		return isOpen;
	}

	public void open(long id, User user) {
		this.id = id;
		this.user = user;
		this.isOpen = true;
	}

	public ClientSession(Socket socket) throws IOException {
		super();
		this.socket = socket;
		in = new SocketReader(this.socket.getInputStream());
		out = new SocketWriter(this.socket.getOutputStream());
	}

}
