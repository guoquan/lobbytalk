package net.guoquan.network.chat.chatRoom.server;

import java.net.Socket;

import net.guoquan.network.chat.chatRoom.server.context.Context;

public class Handlers {
	public static Handler newSocketRequestHandler(Socket cs, Context context){
		return new SocketRequestHandler(cs, context);
	}
}
