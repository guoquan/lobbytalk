/*
 * MessageBox.java
 *
 * Created on __DATE__, __TIME__
 */

package net.guoquan.network.chat.chatRoom.client.UI.component;

import net.guoquan.network.chat.chatRoom.client.information.User;

/**
 *
 * @author  __USER__
 */
public class MessageBox extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Creates new form MessageBox */
	public MessageBox() {
		initComponents();
	}

	public MessageBox(User user) {
		this.user = user;
		this.closable = true;
		initComponents();
	}

	public MessageBox(User user, boolean closable) {
		this.user = user;
		this.closable = closable;
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jTextPane1 = new javax.swing.JTextPane();
		jButton1 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();

		jTextPane1.setEditable(false);
		jScrollPane1.setViewportView(jTextPane1);

		jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/javax/swing/plaf/metal/icons/ocean/paletteClose.gif"))); // NOI18N
		jButton1.setEnabled(closable);
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jLabel1.setText(null == user ? "Lobby" : "Chat with "
				+ user.getUsername());

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								org.jdesktop.layout.GroupLayout.TRAILING,
								layout
										.createSequentialGroup()
										.addContainerGap()
										.add(jLabel1)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED,
												320, Short.MAX_VALUE)
										.add(
												jButton1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												14,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
						.add(jScrollPane1,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								410, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().add(
						layout.createParallelGroup(
								org.jdesktop.layout.GroupLayout.BASELINE).add(
								jButton1,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								13,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.add(jLabel1)).addPreferredGap(
						org.jdesktop.layout.LayoutStyle.RELATED).add(
						jScrollPane1,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 280,
						Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		this.getParent().remove(this);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextPane jTextPane1;
	// End of variables declaration//GEN-END:variables
	private User user;
	private boolean closable = true;

	public User getUser() {
		return user;
	}
}