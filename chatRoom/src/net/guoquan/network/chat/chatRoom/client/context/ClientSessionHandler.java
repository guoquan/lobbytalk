package net.guoquan.network.chat.chatRoom.client.context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import net.guoquan.network.chat.chatRoom.util.Commands;

public class ClientSessionHandler implements Commands {
	private Session session;
	private Context context;
	private byte reply;

	public ClientSessionHandler(Session session, Context context) {
		super();
		this.session = session;
		this.context = context;
	}

	public synchronized boolean connect(String host, int port) throws IOException {
		return session.connect(host, port);
	}
	
	public synchronized boolean bye() throws IOException {
		session.getOut().writeCommand(BYE);
		return SBYE == session.getIn().readCommand();
	}

	public synchronized boolean hello() throws IOException {
		session.getOut().writeCommand(HELLO);
		return SHELLO == session.getIn().readCommand();
	}

	public synchronized long login(String username, String password)
			throws IOException {
		if (username.trim().equalsIgnoreCase("")
				|| password.trim().equalsIgnoreCase("")) {
			return 0;
		}
		session.getOut().writeCommand(LOGIN);
		if (SENDDETAIL == session.getIn().readCommand()) {
			session.getOut().writeUTFLine(username);
			session.getOut().writeUTFLine(password);
			long id = session.getIn().readLong();
			long uid = session.getIn().readLong();
			session.open(id, new User(uid, username));
			return session.getId();
		} else {
			return 0;
		}
	}

	public synchronized boolean recover(long sessionId) throws IOException {
		// TODO recover still not supported
		return false;
	}

	public synchronized boolean post(User user, String message)
			throws IOException, LoginException {
		if (!session.isOpened())
			return false;
		if (message.trim().equalsIgnoreCase("")) {
			return false;
		}
		session.getOut().writeCommand(POST);
		reply = session.getIn().readCommand();
		if (SENDDETAIL == reply) {
			session.getOut().writeLong(null == user ? 0 : user.getUId());
			session.getOut().writeUTFLine(message);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			return OK == session.getIn().readCommand();
		} else if (AUTHORITYNEEDED == reply) {
			throw new LoginException("Need login.");
		} else if (NOSUCHUSER == reply) {
			throw new IOException("No such user!");
		} else {
			throw new IOException("Unknown reply.");
		}
	}

	public synchronized List<Message> news() throws IOException, LoginException {
		if (!session.isOpened())
			return new ArrayList<Message>(0);
		session.getOut().writeCommand(NEWS);
		reply = session.getIn().readCommand();
		if (DETAIL == reply) {
			int n = session.getIn().readInt();
			List<Message> list = new ArrayList<Message>(n);
			for (int i = 0; i < n; i++) {
				long fromId = session.getIn().readLong();
				long toId = session.getIn().readLong();
				String message = session.getIn().readUTFLine();
				list.add(new Message(context.getUser(fromId), context
						.getUser(toId), null, message));
			}
			return list;
		} else if (AUTHORITYNEEDED == reply) {
			throw new LoginException("Need login.");
		} else {
			throw new IOException("Unknown reply.");
		}
	}

	public synchronized List<User> users() throws IOException, LoginException {
		if (!session.isOpened())
			return new ArrayList<User>(0);
		session.getOut().writeCommand(USERS);
		reply = session.getIn().readCommand();
		if (DETAIL == reply) {
			int n = session.getIn().readInt();
			List<User> list = new ArrayList<User>(n);
			for (int i = 0; i < n; i++) {
				long id = session.getIn().readLong();
				String name = session.getIn().readUTFLine();
				list.add(new User(id, name));
			}
			context.setUsers(list);
			return list;
		}else if (AUTHORITYNEEDED == reply) {
			throw new LoginException("Need login.");
		} else {
			throw new IOException("Unknown reply.");
		}
	}
	
	public User user(){
		return session.getUser();
	}

	public void close() {
		session.close();
	}
}
