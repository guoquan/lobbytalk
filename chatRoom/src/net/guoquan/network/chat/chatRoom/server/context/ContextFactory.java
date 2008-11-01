package net.guoquan.network.chat.chatRoom.server.context;

public class ContextFactory {
	public static Context newSessionContext(){
		return new SessionContext();
	}
}
