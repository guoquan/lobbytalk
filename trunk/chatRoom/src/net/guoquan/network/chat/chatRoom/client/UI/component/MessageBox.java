/*
 * MessageBox.java
 *
 * Created on __DATE__, __TIME__
 */

package net.guoquan.network.chat.chatRoom.client.UI.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import net.guoquan.network.chat.chatRoom.client.context.Message;
import net.guoquan.network.chat.chatRoom.client.context.User;

/**
 *
 * @author  __USER__
 */
public class MessageBox extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private boolean closable = true;
	
	private List<String> lines;

	/** Creates new form MessageBox */

	public MessageBox(User user) {
		this.user = user;
		this.closable = true;
		lines = new ArrayList<String>(0);
		initComponents();
	}

	public MessageBox(User user, boolean closable) {
		this.user = user;
		this.closable = closable;
		lines = new ArrayList<String>(0);
		initComponents();
	}

	public void post(Message message) {
		lines.add(message.toString());
		jList1.setListData(lines.toArray());
	}

	public User getUser() {
		return user;
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jButton1 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();

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

		jList1.setModel(new DefaultListModel());
		jScrollPane1.setViewportView(jList1);

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
	private javax.swing.JList jList1;
	private javax.swing.JScrollPane jScrollPane1;
	// End of variables declaration//GEN-END:variables

}