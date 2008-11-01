package net.guoquan.network.chat.chatRoom.client.context;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import net.guoquan.network.chat.chatRoom.util.SocketReader;
import net.guoquan.network.chat.chatRoom.util.SocketWriter;

public class ClientSession implements Session {
	private long id;
	private User user;
	private boolean isOpen;
	private Socket socket;
	private InetAddress host;
	private int port;
	private SocketReader in;
	private SocketWriter out;
	private int timeout;
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

	private Socket getSocket() throws IOException{
		if(null == socket){
			socket = new Socket(host, port);
			socket.setSoTimeout(timeout);
		}
		return socket;
	}
	
	public SocketReader getIn() throws IOException {
		if(null == in){
			in = new SocketReader(getSocket().getInputStream());
		}
		return in;
	}

	public SocketWriter getOut() throws IOException {
		if(null == out){
			out = new SocketWriter(getSocket().getOutputStream());
		}
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
		host = socket.getInetAddress();
		port = socket.getPort();
		timeout = socket.getSoTimeout();
	}

}
