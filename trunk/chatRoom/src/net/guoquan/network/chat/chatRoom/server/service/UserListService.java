package net.guoquan.network.chat.chatRoom.server.service;

import java.io.IOException;
import java.util.List;

import net.guoquan.network.chat.chatRoom.server.context.Context;
import net.guoquan.network.chat.chatRoom.server.context.Session;
import net.guoquan.network.chat.chatRoom.server.context.User;

import org.apache.log4j.Logger;

public class UserListService implements Service {
	private Logger logger = Logger.getLogger(getClass());

	public boolean serve(Session session, Context context) throws IOException {
		session.getOut().writeCommand(DETAIL);
		logger.debug("Start to send user list detail.");
		List<User> list = context.users();
		synchronized (list) {
			session.getOut().writeInt(list.size());
			logger.debug(list.size() + " users to send.");
			for (User u : list) {
				session.getOut().writeLong(u.getUid());
				session.getOut().writeUTFLine(u.getUsername());
				logger.debug("\t" + u.getUid() + " : " + u.getUsername());
			}
			logger.info(list.size() + " user(s) have been sent to ["
					+ session.getUser() + "].");
		}
		return true;
	}

	private static Service instance;

	public static Service getInstance() {
		if (null == instance) {
			instance = new UserListService();
		}
		return instance;
	}

}
