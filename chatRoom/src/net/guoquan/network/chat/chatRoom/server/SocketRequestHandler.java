package net.guoquan.network.chat.chatRoom.server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

import net.guoquan.network.chat.chatRoom.server.context.Context;
import net.guoquan.network.chat.chatRoom.server.context.Session;
import net.guoquan.network.chat.chatRoom.server.service.ServiceDispacher;
import net.guoquan.network.chat.chatRoom.util.Commands;

import org.apache.log4j.Logger;

public class SocketRequestHandler implements Handler, Commands {
	private final Socket cs;
	private final Context context;
	private final int TIMEOUT = 60000;
	
	private ServiceDispacher dispacher;
	private Session session;
	
	private byte commandWord;
	
	private Logger logger = Logger.getLogger(getClass());
	private Timer timer;
	
	public SocketRequestHandler(Socket cs, Context context){
		// final fields
		this.cs = cs;
		this.context = context;
		// initialise
		initialise();
	}
	private boolean initialise() {
		try {
			session = new Session(cs, TIMEOUT);
			logger.info("Socket open. Client ["+ cs.getRemoteSocketAddress() +"] connection initialization finished.");
		} catch (IOException e) {
			logger.info("Failed to establish connection with ["+ cs.getRemoteSocketAddress() +"]");
			logger.debug(null, e);
			return false;
		}
		dispacher = ServiceDispacher.getInstance();
		timer = new Timer(); 
		return true;
	}
	
	public void run() {
		try {
			// loop until BYE
			do{
				commandWord = session.getIn().readCommand();
				// if not logged in
				if(!session.isOpened() && commandWord != LOGIN && commandWord != HELLO){
					session.getOut().writeCommand(AUTHORITYNEEDED);
					continue;
				}
				logger.info(session.getUser() + " request [" + commandWord + "]");
				dispacher.dispach(commandWord, session, context);
			}while(commandWord != BYE);
			session.close();
			logger.info("User "+ session.getUser() +" left.");
		} catch (java.net.SocketTimeoutException e){
			if(session.isOpened()){
				context.drop(session);
				session.close();
			}
			timer.schedule(new TimerTask(){
				@Override
				public void run() {
					context.logout(session);
					logger.info("Session [" + session.getId() + "] is removed.");
				}}, 60000);
			logger.info("Session [" + session.getId() + "] is idle and timeout. Drop client. Session will be removed in 60s.");
		} catch (SocketException e) {
			if(session.isOpened()){
				context.logout(session);
				session.close();
			}
			logger.info("Session [" + session.getId() + "] is reseted. Drop client.");
			logger.debug(null, e);
		} catch (IOException e) {
			if(session.isOpened()){
				context.logout(session);
				session.close();
			}
			logger.info("Un expected excetion occured in sSession [" + session.getId() + "] is reseted. Drop client.");
			logger.debug(null, e);
		}
	}

} 
