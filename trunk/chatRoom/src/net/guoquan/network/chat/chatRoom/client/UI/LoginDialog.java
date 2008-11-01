/*
 * LoginDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package net.guoquan.network.chat.chatRoom.client.UI;

import java.io.IOException;

import net.guoquan.network.chat.chatRoom.client.ClientSession;

/**
 *
 * @author  __USER__
 */
public class LoginDialog extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3040554067533465730L;

	/** Creates new form LoginDialog */
	public LoginDialog(java.awt.Frame parent, boolean modal,
			ClientSession session) {
		super(parent, modal);
		this.session = session;
		initComponents();
	}

	public String getUsername() {
		return jTextField1.getText();
	}

	public String getPassword() {
		return new String(jPasswordField1.getPassword());
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jTextField1 = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jPasswordField1 = new javax.swing.JPasswordField();
		jButton1 = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		jProgressBar1 = new javax.swing.JProgressBar();

		setTitle("LobbyTalk Internet Chatting room");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);

		jLabel1.setText("Username: ");

		jLabel2.setText("Password: ");

		jButton1.setText("Login");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/javax/swing/plaf/metal/icons/ocean/question.png"))); // NOI18N

		jProgressBar1.setString("");
		jProgressBar1.setStringPainted(true);

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
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
																				jLabel1)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jTextField1,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				252,
																				Short.MAX_VALUE))
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																layout
																		.createSequentialGroup()
																		.add(
																				jLabel2)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jPasswordField1,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				254,
																				Short.MAX_VALUE))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				jProgressBar1,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				246,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jButton1)))
										.addContainerGap()).add(jLabel3,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								331, Short.MAX_VALUE));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								org.jdesktop.layout.GroupLayout.TRAILING,
								layout
										.createSequentialGroup()
										.add(
												jLabel3,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												68, Short.MAX_VALUE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel1)
														.add(
																jTextField1,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel2)
														.add(
																jPasswordField1,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(
																jProgressBar1,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																23,
																Short.MAX_VALUE)
														.add(jButton1))
										.addContainerGap()));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		jProgressBar1.setIndeterminate(true);
		jTextField1.setEnabled(false);
		jProgressBar1.setEnabled(false);
		jButton1.setEnabled(false);
		new Thread() {
			public void run() {
				try {
					if (0 != session.login(jTextField1.getText(), new String(
							jPasswordField1.getPassword()))) {
						getParent().setVisible(true);
						setVisible(false);
					} else {
						jProgressBar1.setString("Wrong name or bad password.");
					}
				} catch (IOException e) {
					jProgressBar1.setString("Network Error.");
					e.printStackTrace();
				} finally {
					jProgressBar1.setIndeterminate(false);
					jTextField1.setEnabled(true);
					jProgressBar1.setEnabled(true);
					jButton1.setEnabled(true);
				}
			}
		}.start();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				LoginDialog dialog = new LoginDialog(new javax.swing.JFrame(),
						true, null);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPasswordField jPasswordField1;
	private javax.swing.JProgressBar jProgressBar1;
	private javax.swing.JTextField jTextField1;
	// End of variables declaration//GEN-END:variables
	private ClientSession session;
}