package net.guoquan.network.chat.chatRoom.server.context;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.guoquan.network.chat.chatRoom.util.SocketReader;

public class SessionContext implements Context{
	public SessionContext(){
		sessions = new HashSet<Session>(0);
		users = new ArrayList<User>(0);
	}
	
	private Set<Session> sessions;
	private List<User> users;
	
	private Socket cs;
	private SocketReader in;
	private DataOutputStream out;
	
	public boolean post(Message message) {
		User from = message.getFrom();
		User to = message.getTo();
		// if error message
		if(null == message || null == from){
			return false;
		}
		// if broadcast
		if(null == to){
			for(Session s : sessions){
				s.getMessages().add(message);
			}
			return true;
		}else{
			for(Session s : sessions){
				if(from.equals(s.getUser()) || to.equals(s.getUser()))
					s.getMessages().add(message);
			}
			return true;
		}
	}
	public boolean login(Session session) {
		boolean done = sessions.add(session);
		if(done){
			synchronized (users) {
				users.add(session.getUser());
			}
		}
		return done;
	}
	public boolean logout(Session session) {
		boolean done = sessions.remove(session);
		if(done){
			synchronized (users) {
				users.remove(session.getUser());
			}
		}
		return done;
	}
	public boolean drop(Session session) {
		if(sessions.contains(session)){
			synchronized (users) {
				return users.remove(session.getUser());
			}
		}else{
			return false;
		}
	}
	public boolean recover(Session session) {
		if(sessions.contains(session)){
			synchronized (users) {
				if(users.contains(session.getUser())){
					return true;
				}else{
					return users.add(session.getUser());
				}
			}
		}else{
			return false;
		}
	}
	public List<User> users() {
		return users;
	}
	public Socket getCs() {
		return cs;
	}
	public SocketReader getIn() {
		return in;
	}
	public DataOutputStream getOut() {
		return out;
	}
	public Session getSession(long id) {
		for(Session s : sessions){
			if(s.getId() == id){
				return s;
			}
		}
		return null;
	}
	public Session getSessionByUser(User user) {
		for(Session s : sessions){
			if(s.getUser().equals(user)){
				return s;
			}
		}
		return null;
	}
	public Session getSessionByUser(long uId) {
		for(Session s : sessions){
			if(s.getUser().getUid() == uId){
				return s;
			}
		}
		return null;
	}
}
