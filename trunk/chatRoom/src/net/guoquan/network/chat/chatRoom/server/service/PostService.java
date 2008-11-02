package net.guoquan.network.chat.chatRoom.server.service;

import java.io.IOException;
import java.util.Date;

import net.guoquan.network.chat.chatRoom.server.context.Context;
import net.guoquan.network.chat.chatRoom.server.context.Message;
import net.guoquan.network.chat.chatRoom.server.context.Session;
import net.guoquan.network.chat.chatRoom.server.context.User;

import org.apache.log4j.Logger;

public class PostService implements Service {
	private Logger logger = Logger.getLogger(getClass());

	public boolean serve(Session session, Context context) throws IOException {
		session.getOut().writeCommand(SENDDETAIL);
		logger.debug("Send message detail.");
		long toId = session.getIn().readLong();
		String message = session.getIn().readUTFLine();
		User to = null;
		if(toId != 0){
			Session s = context.getSessionByUser(toId);
			if(null == s){
				session.getOut().writeCommand(NOSUCHUSER);
				logger.info(session.getUser() + " failed to posted a message. Receiver not exist.");
				return false;
			}else{
				to = s.getUser();
			}
		}		
		if(to == null && toId != 0){return false;
		}
		Message m = new Message(session.getUser(), to, new Date(), message);
		if(context.post(m)){
			logger.debug("[" + session.getUser().getUid() + "] to [" + toId + "] : {" + message + "}");
			session.getOut().writeCommand(OK);
			logger.info(session.getUser() + " posted a message.");
			return true;
		}else{
			logger.debug("[" + session.getUser().getUid() + "] to [" + toId + "] : {" + message + "}");
			session.getOut().writeCommand(FAILED);
			logger.info(session.getUser() + " failed to posted a message.");
			return false;
		}
	}

	private static Service instance;

	public static Service getInstance() {
		if (null == instance) {
			instance = new PostService();
		}
		return instance;
	}

}
