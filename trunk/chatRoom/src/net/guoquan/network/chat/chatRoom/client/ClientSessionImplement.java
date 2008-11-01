package net.guoquan.network.chat.chatRoom.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import javax.security.auth.login.LoginException;

import net.guoquan.network.chat.chatRoom.client.information.Message;
import net.guoquan.network.chat.chatRoom.client.information.User;
import net.guoquan.network.chat.chatRoom.util.Commands;
import net.guoquan.network.chat.chatRoom.util.SocketReader;
import net.guoquan.network.chat.chatRoom.util.SocketWriter;

public class ClientSessionImplement implements ClientSession,Commands {
	private Socket cs;
	private SocketReader nin;
	private SocketWriter nout;
	private List<User> users;
	private List<Message> messages;
	private long sessionId;
	public ClientSessionImplement() throws UnknownHostException, IOException {
		super();
		new Timer();
		users = Collections.synchronizedList(new ArrayList<User>(0));
		messages = new ArrayList<Message>(0);
	}

	public synchronized boolean bye() throws IOException {
		getNout().writeCommand(BYE);
		return getNin().readCommand() == SBYE;
	}

	public synchronized boolean hello() throws IOException {
		getNout().writeCommand(HELLO);
		return getNin().readCommand() == SHELLO;
	}

	public synchronized long login(String username, String password) throws IOException {
		if(username.trim().equalsIgnoreCase("") || password.trim().equalsIgnoreCase("")){
			return 0;
		}
		getNout().writeCommand(LOGIN);
		if(SENDDETAIL == getNin().readCommand()){
			getNout().writeUTFLine(username);
			getNout().writeUTFLine(password);
			sessionId = getNin().readLong();
			return sessionId;
		}else{
			return 0;
		}
	}

	public synchronized boolean recover(long sessionId) {
		// TODO Auto-generated method stub
		return false;
	}

	public synchronized boolean post(User user, String message) throws IOException, LoginException {
		if(message.trim().equalsIgnoreCase("")){
			return false;
		}
		getNout().writeCommand(POST);
		if(SENDDETAIL == getNin().readCommand()){
			getNout().writeLong(null == user ? 0 : user.getUId());
			getNout().writeUTFLine(message);
			return OK == getNin().readCommand();
		}else{
			throw new LoginException("Need login.");
		}
	}

	public synchronized List<Message> news() {
		return messages;
	}

	public synchronized List<User> users() {
		try {
			getNout().writeCommand(USERS);
			if(DETAIL == getNin().readCommand()){
				int n = getNin().readInt();
				users.clear();
				for(int i = 0; i < n; i++){
					long id = getNin().readLong();
					String name = getNin().readUTFLine();
					users.add(new User(id, name));
					System.out.println(name);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}

	public synchronized Socket getCs() throws UnknownHostException, IOException {
		if(null == cs){
			cs = new Socket("127.0.0.1", 12345);
			cs.setSoTimeout(60000);
		}
		return cs;
	}

	public synchronized SocketReader getNin() throws UnknownHostException, IOException {
		if(null == nin){
			nin = new SocketReader(getCs().getInputStream());	
		}
		return nin;
	}

	public synchronized SocketWriter getNout() throws IOException {
		if(null == nout){
			nout = new SocketWriter(getCs().getOutputStream());
		}
		return nout;
	}

	public long getSessionId() {
		return sessionId;
	}

	public synchronized void reset() {
		sessionId = 0;
		users.clear();
		messages.clear();
		try {
			nin.close();
			nout.close();
			cs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cs = null;
			nin = null;
			nout = null;
		}
	}

}
