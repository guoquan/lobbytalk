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

	public boolean daemon(){
		logger.info("Start daemon.");
		// do daemon
		while (true) {
			try {
				executor.execute(Handlers.newSocketRequestHandler(serverSocket
						.accept(), context));
			} catch (IOException e) {
				logger.info("Exception occured, check the network.");
				logger.debug(null,e);
			}
		}
	}

	public static void main(String args[]) throws IOException {
		// initialise log4j configuration
		PropertyConfigurator.configure("log4j.properties");
		
		// configure
		int port = 12345;
		int poolSize = 10;
		// get instance
		final Server server = new ServerImplement(port, poolSize);
		// daemon
		new Thread(){
			public void run() {
				server.daemon();
			}
		}.start();
		while(true){
			// TODO do some server runtime query here
			break;
		}
	}

	public Context getContext() {
		return context;
	}
}
