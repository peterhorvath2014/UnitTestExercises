package com.epam.torpedo.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CommunicationResources {
	PrintWriter out;
	BufferedReader in;
	
	public PrintWriter getOut() {
		return out;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	public BufferedReader getIn() {
		return in;
	}
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	public void closeAll() throws IOException {
		out.close();
		in.close();
	}
}
