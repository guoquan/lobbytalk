package net.guoquan.network.chat.chatRoom.server;

import net.guoquan.network.chat.chatRoom.server.context.Context;

public interface Server {
	public boolean daemon();
	public Context getContext();
}
