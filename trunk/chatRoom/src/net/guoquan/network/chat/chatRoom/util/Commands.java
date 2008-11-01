package net.guoquan.network.chat.chatRoom.util;

public interface Commands {
	// Client
	public static final byte BYE = 0x00;
	public static final byte HELLO = 0x01;
	public static final byte LOGIN = 0x02;
	public static final byte RECOVER = 0x03;
	public static final byte USERS = 0x04;
	public static final byte NEWS = 0x05;
	public static final byte POST = 0x06;

	// Server
	public static final byte SBYE = 0x00;
	public static final byte SHELLO = 0x01;
	public static final byte OK = 0x02;
	public static final byte SENDDETAIL = 0x03;
	public static final byte DETAIL = 0x04;
	
	// Error
	public static final byte INCOGITABLE = -0x01;
	public static final byte UNKNOWN = -0x02;
	public static final byte FAILED = -0x03;
	public static final byte AUTHORITYNEEDED = -0x04;
	public static final byte WRONGUSERNAME = -0x05;
	public static final byte WRONGPASSWORD = -0x06;
	public static final byte NOSUCHUSER = -0x07;
}
