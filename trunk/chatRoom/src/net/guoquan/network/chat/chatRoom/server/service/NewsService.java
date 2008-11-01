package net.guoquan.network.chat.chatRoom.server.service;

import java.io.IOException;
import java.util.Queue;

import net.guoquan.network.chat.chatRoom.server.context.Context;
import net.guoquan.network.chat.chatRoom.server.context.Message;
import net.guoquan.network.chat.chatRoom.server.context.Session;

import org.apache.log4j.Logger;

public class NewsService implements Service {
	private Logger logger = Logger.getLogger(getClass());

	public boolean serve(Session session, Context context) throws IOException {
		session.getOut().writeCommand(DETAIL);
		logger.debug("Start to send message list detail.");
		Queue<Message> queue = session.getMessages();
		session.getOut().writeInt(queue.size());
		logger.debug(queue.size() + " message(s) to send.");
		while(!queue.isEmpty()){
			Message m = queue.poll();
			session.getOut().writeLong(m.getFrom().getUid());
			session.getOut().writeBoolean(null != m.getTo());
			session.getOut().writeUTFLine(m.getMessage());
			logger.debug("[" + m.getFrom().getUid() + "] to ["
					+ m.getTo().getUid() + "] : {" + m.getMessage() + "}");
		}
		logger.info(queue.size() + " message(s) left in [" + session.getUser() + "]'s box.");
		return true;
	}

	private static Service instance;

	public static Service getInstance() {
		if (null == instance) {
			instance = new NewsService();
		}
		return instance;
	}

}
