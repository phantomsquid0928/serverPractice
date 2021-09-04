package com.Squid0928;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientOutput implements Runnable{
	private Socket client;
	private String msg;
	private boolean isNewMsgHere;
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		
		// TODO Auto-generated method stub
		try {
			//PrintStream ps = new PrintStream(client.getOutputStream());
			PrintWriter pw = new PrintWriter(client.getOutputStream());
			while(true) {
				if (msg.equals("")) {break;}
				if (isNewMsgHere == true) {
					pw.write(msg);
					pw.flush();
				}
			}
			pw.close();
			client.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	public synchronized void sendNewMsg(String msg) {
		this.msg = msg;
		this.isNewMsgHere = true;
	}
	public ClientOutput(Socket client) {
		this.client = client;
	}

}
