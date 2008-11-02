package net.guoquan.network.chat.chatRoom.server.context;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import net.guoquan.network.chat.chatRoom.util.SocketReader;
import net.guoquan.network.chat.chatRoom.util.SocketWriter;

public class Session {
	private long id;
	private User user;
	private Queue<Message> messages;
	private boolean isOpened;

	private Socket cs;
	private SocketReader in;
	private SocketWriter out;
	private int timeout;

	public User getUser() {
		return user;
	}

	public long getId() {
		return id;
	}

	public void open(long id, User user) {
		this.id = id;
		this.user = user;
		isOpened = true;
	}

	public void recover(Socket cs) throws IOException {
		this.cs = cs;

		cs.setSoTimeout(timeout);
		in = new SocketReader(cs.getInputStream());
		out = new SocketWriter(cs.getOutputStream());

		isOpened = true;
	}

	public void close() {

		while(!cs.isClosed())
		{
			try {
				cs.close();
				in.close();
				out.close();
			} catch (IOException e) {
				// do nothing
			} finally {
				in = null;
				out = null;
				cs = null;

				isOpened = false;
			}
		}
	}

	public Session(Socket cs, int timeout) throws IOException {
		super();
		this.cs = cs;
		this.timeout = timeout;
		messages = new LinkedList<Message>();

		cs.setSoTimeout(timeout);
		in = new SocketReader(cs.getInputStream());
		out = new SocketWriter(cs.getOutputStream());

		isOpened = true;
	}

	public boolean isOpened() {
		return isOpened;
	}

	public Queue<Message> getMessages() {
		return messages;
	}

	public boolean equals(Object o) {
		return getClass().equals(o.getClass()) && id == ((Session) o).getId();
	}

	public String toString(){
		return Long.toString(id);
	}
	
	public SocketReader getIn() {
		return in;
	}

	public SocketWriter getOut() {
		return out;
	}
}
