package net.guoquan.network.chat.chatRoom.server.service;

import java.io.IOException;

import net.guoquan.network.chat.chatRoom.server.context.Context;
import net.guoquan.network.chat.chatRoom.server.context.Session;

import org.apache.log4j.Logger;

public class ByeService implements Service {
	private Logger logger = Logger.getLogger(getClass());
	public boolean serve(Session session, Context context) throws IOException {
		session.getOut().writeCommand(SBYE);
		context.logout(session);
		session.close();
		logger.info("User [" + session.getId() + "] left.");
		return true;
	}

	private static Service instance;

	public static Service getInstance() {
		if (null == instance) {
			instance = new ByeService();
		}
		return instance;
	}

}
