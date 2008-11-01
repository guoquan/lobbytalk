/*
 * ClientGUI.java
 *
 * Created on __DATE__, __TIME__
 */

package net.guoquan.network.chat.chatRoom.client.UI;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.login.LoginException;
import javax.swing.DefaultListModel;

import net.guoquan.network.chat.chatRoom.client.ClientSession;
import net.guoquan.network.chat.chatRoom.client.UI.component.MessageBox;
import net.guoquan.network.chat.chatRoom.client.information.User;
import net.guoquan.network.chat.chatRoom.client.information.UserList;

/**
 *
 * @author  __USER__
 */
public class ClientGUI extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClientSession session;
	private Timer timer;

	private UserList list;
	private boolean filterOpen;
	private LoginDialog loginDialog;

	/** Creates new form ClientGUI */
	public ClientGUI(ClientSession session) {
		this.session = session;
		initAheadComponents();
		initComponents();
		initOtherComponents();
	}

	private void initAheadComponents() {
		timer = new Timer();
		list = new UserList(session);
		filterOpen = false;
	}

	private void initOtherComponents() {
		jTabbedPane1.addTab("Main", null, new MessageBox(null, false), null);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				freshUserList();
			}
		}, 0, 5000);
	}

	private void freshUserList() {
		list.sync();
		if (filterOpen) {
			list.applyFilter(jTextField1.getText() + ".*");
		} else {
			list.applyFilter(".*");
		}
		jList1.setListData(list.getUserArray());
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jScrollPane2 = new javax.swing.JScrollPane();
		jEditorPane1 = new javax.swing.JEditorPane();
		jTextField1 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		openMenuItem = new javax.swing.JMenuItem();
		exitMenuItem = new javax.swing.JMenuItem();
		helpMenu = new javax.swing.JMenu();
		contentsMenuItem = new javax.swing.JMenuItem();
		aboutMenuItem = new javax.swing.JMenuItem();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		menuBar1 = new javax.swing.JMenuBar();
		fileMenu1 = new javax.swing.JMenu();
		openMenuItem1 = new javax.swing.JMenuItem();
		exitMenuItem1 = new javax.swing.JMenuItem();
		helpMenu1 = new javax.swing.JMenu();
		contentsMenuItem1 = new javax.swing.JMenuItem();
		aboutMenuItem1 = new javax.swing.JMenuItem();

		setTitle("LobbyTalk Internet Chatting room");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setDefaultCloseOperation(3);
		setResizable(false);

		jTabbedPane1.setTabPlacement(3);

		jScrollPane1.setMaximumSize(new java.awt.Dimension(37, 32767));
		jScrollPane1.setMinimumSize(new java.awt.Dimension(37, 24));

		jList1.setModel(new DefaultListModel());
		jList1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jList1MouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jList1);

		jScrollPane2.setViewportView(jEditorPane1);

		jTextField1.setText("Find out a friend!");
		jTextField1.setForeground(java.awt.Color.gray);
		jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				jTextField1FocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				jTextField1FocusLost(evt);
			}
		});

		jButton1.setText("Send");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		fileMenu.setText("File");

		openMenuItem.setText("Logout");
		fileMenu.add(openMenuItem);

		exitMenuItem.setText("Exit");
		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		helpMenu.setText("Help");

		contentsMenuItem.setText("Contents");
		helpMenu.add(contentsMenuItem);

		aboutMenuItem.setText("About");
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);

		jButton2.setMargin(new java.awt.Insets(2, 4, 2, 4));
		jButton2.setText("#");
		jButton2.setToolTipText("Clear filter");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setMargin(new java.awt.Insets(2, 4, 2, 4));
		jButton3.setText("!");
		jButton3.setToolTipText("Apply filter");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		fileMenu1.setText("File");

		openMenuItem1.setText("Logout");
		openMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				openMenuItem1ActionPerformed(evt);
			}
		});
		fileMenu1.add(openMenuItem1);

		exitMenuItem1.setText("Exit");
		exitMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}
		});
		fileMenu1.add(exitMenuItem1);

		menuBar1.add(fileMenu1);

		helpMenu1.setText("Help");

		contentsMenuItem1.setText("Contents");
		helpMenu1.add(contentsMenuItem1);

		aboutMenuItem1.setText("About");
		helpMenu1.add(aboutMenuItem1);

		menuBar1.add(helpMenu1);

		setJMenuBar(menuBar1);

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				jScrollPane2,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				611,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jButton1))
														.add(
																jTabbedPane1,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																676,
																Short.MAX_VALUE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				jTextField1,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				219,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jButton3)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jButton2))
														.add(
																jScrollPane1,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																273,
																Short.MAX_VALUE))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								org.jdesktop.layout.GroupLayout.TRAILING,
								layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																layout
																		.createSequentialGroup()
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								jButton2,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								18,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																						.add(
																								jButton3,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								18,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																						.add(
																								jTextField1,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jScrollPane1,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				445,
																				Short.MAX_VALUE))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				jTabbedPane1,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				397,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.TRAILING,
																								false)
																						.add(
																								jScrollPane2)
																						.add(
																								jButton1,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								68,
																								Short.MAX_VALUE))))
										.addContainerGap()));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {
		jTextField1.setSelectionStart(0);
		jTextField1.setSelectionEnd(jTextField1.getText().length());
		if (filterOpen == false) {
			jTextField1.setText("");
			filterOpen = true;
		}
		jTextField1.setForeground(Color.black);
	}

	private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {
		if (jTextField1.getText().trim().equalsIgnoreCase("")) {
			filterOpen = false;
			jTextField1.setForeground(Color.gray);
			jTextField1.setText("Find out a friend!");
		} else {
			filterOpen = true;
			jTextField1.setForeground(Color.black);
		}
		freshUserList();
	}

	private void openMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			session.bye();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.reset();
			this.setVisible(false);
			loginDialog.setVisible(true);
		}

	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jTextField1.getText().trim().equalsIgnoreCase("")) {
			filterOpen = false;
			jTextField1.setForeground(Color.gray);
			jTextField1.setText("Find out a friend!");
		} else {
			filterOpen = true;
			jTextField1.setForeground(Color.black);
		}
		freshUserList();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			session.post(((MessageBox) jTabbedPane1.getSelectedComponent())
					.getUser(), jEditorPane1.getText());
			jEditorPane1.setText("");
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		filterOpen = false;
		jTextField1.setForeground(Color.gray);
		jTextField1.setText("Find out a friend!");
		freshUserList();
	}

	private void jList1MouseClicked(java.awt.event.MouseEvent evt) {
		User selectedUser = (User) jList1.getSelectedValue();
		if (null == selectedUser)
			return;
		boolean isDupl = false;
		MessageBox box = null;
		for (Component c : jTabbedPane1.getComponents()) {
			if (null != ((MessageBox) c).getUser()
					&& ((MessageBox) c).getUser().equals(selectedUser)) {
				box = (MessageBox) c;
				isDupl = true;
				break;
			}
		}
		if (!isDupl) {
			box = new MessageBox(selectedUser);
			jTabbedPane1.addTab(selectedUser.getUsername(), null, box, null);
		}
		jTabbedPane1.setSelectedComponent(box);
	}

	private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
		System.exit(0);
	}//GEN-LAST:event_exitMenuItemActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ClientGUI(null).setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.JMenuItem aboutMenuItem1;
	private javax.swing.JMenuItem contentsMenuItem;
	private javax.swing.JMenuItem contentsMenuItem1;
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JMenuItem exitMenuItem1;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenu fileMenu1;
	private javax.swing.JMenu helpMenu;
	private javax.swing.JMenu helpMenu1;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JEditorPane jEditorPane1;
	private javax.swing.JList jList1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuBar menuBar1;
	private javax.swing.JMenuItem openMenuItem;
	private javax.swing.JMenuItem openMenuItem1;

	// End of variables declaration//GEN-END:variables

	public void setLoginDialog(LoginDialog loginDialog) {
		this.loginDialog = loginDialog;
	}

}
