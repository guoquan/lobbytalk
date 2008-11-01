package net.guoquan.network.chat.chatRoom.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SocketWriter extends DataOutputStream {

	public SocketWriter(OutputStream out) {
		super(out);
	}
	public void writeCommand(byte command) throws IOException {
		this.writeByte(command);
	}
	@Deprecated
	public void writeLine(String string) throws IOException {
		this.writeBytes(string + "\n");
	}
	public void writeUTFLine(String string) throws IOException {
		this.writeUTF(string);
	}
}
