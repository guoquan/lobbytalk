package net.guoquan.network.chat.chatRoom.client;

import java.io.IOException;
import java.net.UnknownHostException;

import net.guoquan.network.chat.chatRoom.client.UI.ClientGUI;
import net.guoquan.network.chat.chatRoom.client.UI.LoginDialog;
import net.guoquan.network.chat.chatRoom.util.Commands;

public class Client implements Commands{	
	private static ClientSession session;
	
	public static void main(String args[]) throws UnknownHostException, IOException{
		session = new ClientSessionImplement();
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				ClientGUI cg = new ClientGUI(session);
				LoginDialog dialog = new LoginDialog(cg, true, session);
				cg.setLoginDialog(dialog);
				dialog.setVisible(true);
			}
		});
	}
}
