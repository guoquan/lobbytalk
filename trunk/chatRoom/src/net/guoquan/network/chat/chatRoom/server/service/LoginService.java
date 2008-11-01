package net.guoquan.network.chat.chatRoom.server.service;

import java.io.IOException;

import net.guoquan.network.chat.chatRoom.server.context.Context;
import net.guoquan.network.chat.chatRoom.server.context.Session;
import net.guoquan.network.chat.chatRoom.server.context.User;

import org.apache.log4j.Logger;

public class LoginService implements Service {
	private Logger logger = Logger.getLogger(getClass());
	
	public boolean serve(Session session, Context context) throws IOException {
		session.getOut().writeCommand(SENDDETAIL);
		logger.debug("Login detail needed.");
		String username = session.getIn().readUTFLine();
		String password = session.getIn().readUTFLine();
		logger.debug("Login using name " + username);
		// if is re login
		if(session.isOpened()){
			context.logout(session);
		}
		User user = new User(username, password, session);
		if(context.login(session)){
			// if success, send session id
			session.getOut().writeLong(session.getId());
			logger.info("[" + user.getUid() + "] " + user.getUsername() + " logged in.");
			return true;
		}else{
			session.getOut().writeCommand(WRONGUSERNAME);
			logger.info("Wrong username.");
			return false;
		}
	}

	private static Service instance;

	public static Service getInstance() {
		if (null == instance) {
			instance = new LoginService();
		}
		return instance;
	}

}
