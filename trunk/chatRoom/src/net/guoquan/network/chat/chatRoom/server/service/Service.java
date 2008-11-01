package net.guoquan.network.chat.chatRoom.server.service;

import java.io.IOException;

import net.guoquan.network.chat.chatRoom.server.context.Context;
import net.guoquan.network.chat.chatRoom.server.context.Session;
import net.guoquan.network.chat.chatRoom.util.Commands;


public interface Service extends Commands{
	public boolean serve(Session session, Context context) throws IOException;
}
