package net.guoquan.network.chat.chatRoom.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import net.guoquan.network.chat.chatRoom.server.context.Context;
import net.guoquan.network.chat.chatRoom.server.context.ContextFactory;
import net.guoquan.network.chat.chatRoom.server.context.Session;
import net.guoquan.network.chat.chatRoom.server.context.User;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.varia.DenyAllFilter;

public class ServerImplement implements Server{
	private static Server server;
	private final int port;
	private final int poolSize;
	private final Context context;
	private ServerSocket serverSocket;
	private Executor executor;
	private boolean serverRunning;
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
		serverRunning = false;
		return true;
	}

	public boolean daemon(){
		serverRunning = true;
		logger.info("Start daemon.");
		// do daemon
		while (serverRunning) {
			try {
				executor.execute(Handlers.newSocketRequestHandler(serverSocket
						.accept(), context));
			} catch (IOException e) {
				logger.info("Exception occured, check the network.");
				logger.debug(null,e);
			}
		}
		return true;
	}

	public boolean halt() {
		serverRunning = false;
		try {
			serverSocket.close();
		} catch (IOException e) {
			// do nothing
		} finally {
			logger.info("Socket closed. Server halted.");
		}
		return true;
		
	}
	public Context getContext() {
		return context;
	}
	public void finalize(){
		halt();
	}
	
	private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private static final PrintStream out = System.out; 
	public static void main(String args[]) throws IOException {
		// initialise log4j configuration
		PropertyConfigurator.configure("log4j.properties");
		
		// configure
		int port = 12345;
		int poolSize = 10;
		if(args.length > 0){
			port = Integer.parseInt(args[0]);
		}
		if(args.length > 1){
			poolSize = Integer.parseInt(args[0]);
		}
		
		doWelcome();
		
		// get instance
		server = new ServerImplement(port, poolSize);
		// daemon
		new Thread(){
			public void run() {
				try{
					server.daemon();
				} catch (Exception e){
					server.halt();
					Logger.getLogger(ServerImplement.class).fatal("Daemon fialed! Socket closed. Server halted.", e);	
				}
			}
		}.start();
		new Thread(){
			public void run() {
				try{
					serve();
				} catch (Exception e){
					server.halt();
					Logger.getLogger(ServerImplement.class).fatal("Server Query Support failed.", e);	
				}
			}
		}.start();
	}
	
	private static void serve() throws IOException, InterruptedException{
		char serverCommand = 0;
		// q - quit
		// h - help
		// p - pause console log output
		// r - resume console log output
		// u - user list
		// s - session list
		while(serverCommand != 'q'){
			while(!in.ready()){
			}
			serverCommand = (char)in.read();
			switch(serverCommand){
			case 'q':
				doQuit();
				break;
			case 'h':
				doHelp();
				break;
			case 'p':
				doPause();
				break;
			case 'r':
				doResume();
				break;
			case 'u':
				doUserList();
				break;
			case 's':
				doSessionList();
				break;
			default:
				// do nothing
				break;
			}
		}
	}
	private static void doHelp(){
		out.println("======  Server Command  ======");
		out.println("character commands:");
		out.println("\tq - quit");
		out.println("\th - help");
		out.println("\tp - pause console log output");
		out.println("\tu - user list");
		out.println("\ts - session list");
		out.println("==============================");
	}
	private static void doWelcome(){
		out.println("== Welcome LOBBYTALK Server ==");
		out.println("following command may be useful.");
		out.println("use command 'h' to show this again.");		
		out.println();
		doHelp();
	}
	private static void doQuit(){
		server.halt();
		out.println("== Bye Bye LOBBYTALK Server ==");
		out.println("  Thanks for using LOBBYTALK.");	
		out.println();
	}
	private static void doPause(){
		Logger.getRootLogger().getAppender("Console").addFilter(new DenyAllFilter());
	}
	private static void doResume(){
		PropertyConfigurator.configure("log4j.properties");
	}
	private static void doUserList(){
		out.println("================  User  online  ================");
		for(User u : server.getContext().users()){
			out.println(u.getUid() + "\t" + u.getUsername());
		}
		out.println("------------------------------------------------");
		out.println("Total: " + server.getContext().users().size());
		out.println("================================================");
	}
	private static void doSessionList(){
		out.println("================ Seesion  alive ================");
		for(Session s : server.getContext().sessions()){
			out.println(s.getId() + "\t" + s.getUser());
		}
		out.println("------------------------------------------------");
		out.println("Total: " + server.getContext().users().size());
		out.println("================================================");
	}
}
