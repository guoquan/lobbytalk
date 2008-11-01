package net.guoquan.network.chat.chatRoom.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SocketReader extends DataInputStream {
	private BufferedReader br;

	public SocketReader(InputStream in) {
		super(in);
		br = new BufferedReader(new InputStreamReader(this));
	}
	@Deprecated
	public String readerReadLine() throws IOException{
		return br.readLine();
	}
	public String readUTFLine() throws IOException{
		return this.readUTF();
	}
	public byte readCommand() throws IOException{
		return readByte();
	}
}
