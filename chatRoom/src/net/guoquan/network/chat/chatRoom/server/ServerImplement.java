package net.guoquan.network.chat.chatRoom.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import net.guoquan.network.chat.chatRoom.server.context.Context;
import net.guoquan.network.chat.chatRoom.server.context.ContextFactory;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ServerImplement implements Server{
	private final int port;
	private final int poolSize;
	private final Context context;
	private ServerSocket serverSocket;
	private Executor executor;
	
	private Logger logger = Logger.getLogger(getClass());

	public ServerImplement(int port, int poolSize) throws IOException {
		// final fields
		this.port = port;
		this.poolSize = poolSize;
		context = ContextFactory.newSessionContext();
		// initialise
		initialise();
		logger.info("Server started.");
	}

	private boolean initialise() throws IOException {
		serverSocket = new ServerSocket(port);
		executor = Executors.newFixedThreadPool(poolSize);
		return true;
	}

	public boolean daemon() throws IOException {
		// do daemon
		while (true) {
			executor.execute(Handlers.newSocketRequestHandler(serverSocket
					.accept(), context));
		}
	}

	public static void main(String args[]) throws IOException {
		// initialise log4j configuration
		PropertyConfigurator.configure("log4j.properties");
		
		// configure
		int port = 12345;
		int poolSize = 10;
		// get instance
		Server server = new ServerImplement(port, poolSize);
		// daemon 
		server.daemon();
	}
}
