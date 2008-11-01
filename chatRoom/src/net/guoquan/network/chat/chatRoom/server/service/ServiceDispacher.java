package net.guoquan.network.chat.chatRoom.server.service;

import java.io.IOException;

import net.guoquan.network.chat.chatRoom.server.context.Context;
import net.guoquan.network.chat.chatRoom.server.context.Session;
import net.guoquan.network.chat.chatRoom.util.Commands;

public class ServiceDispacher implements Commands {
	
	public boolean dispach(byte commandWord, Session session, Context context) throws IOException{
		Service service = null;
		switch(commandWord){
		case BYE:
			service = Services.getByeService();
			break;
		case HELLO:
			service = Services.getHelloService();
			break;
		case LOGIN:
			service = Services.getLoginService();
			break;
		case USERS:
			service = Services.getGetUsersService();
			break;
		case POST:
			service = Services.getPostService();
			break;
		default:
			session.getOut().writeCommand(INCOGITABLE);
			return false;
		}
		return service.serve(session, context);
	}
	
	private ServiceDispacher(){
		// do nothing
		// use singleton only
	}
	
	private static ServiceDispacher instance;
	
	public static ServiceDispacher getInstance(){
		if(null == instance){
			instance = new ServiceDispacher();
		}
		return instance;
	}
}
