package com.Squid0928;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Iterator;

public class ClientToClient implements Runnable{ //clientInput upgraded version
	
	private Socket clientConnected;
	private Socket clientMsgTarget = null;
	
	private ClientOutput out1;
	private ClientOutput out2;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BufferedReader br1 = new BufferedReader(
					new InputStreamReader(clientConnected.getInputStream()));
			out1 = (ClientOutput)ServerMain.handlingOutputs.get(clientConnected);
			out2 = null;
			while(true) {
				String input = br1.readLine();
				System.out.println("client: " + input);
				if (input.equals("/clientList")) {
					Iterator<Socket> itr = ServerMain.clientList.iterator();
					out1.sendNewMsg("#############");
					while(itr.hasNext()) {
						Socket target = itr.next();
						out1.sendNewMsg("client: " + target.getInetAddress());
					}
				}
				if (input.contains("/connectClient")) {
					if (clientMsgTarget != null) {
						String target = input.substring(14);
						clientMsgTarget = new Socket(target, 1001);
						initNewClientConnect();
						out2.sendNewMsg("client : " + clientConnected.getInetAddress() + "made connection between u and him");
					}
					else {
						out1.sendNewMsg("you have connected one");
					}
				}
				if (input.equals("/disconnect")) {
					clientMsgTarget.close();
					out2 = null;
					out1.sendNewMsg("deleted link to client2");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void initNewClientConnect() {
		Runnable run = new ClientOutput(clientMsgTarget);
		Runnable run2 = new ClientToClient(clientMsgTarget, clientConnected);
		Thread th = new Thread(run);
		Thread th2 = new Thread(run2);
		th.start();
		th2.start();
		ServerMain.handlingOutputs.put(clientMsgTarget, run);
		out2 = (ClientOutput)run;
	}
	public ClientToClient(Socket clientConnected) {
		this.clientConnected = clientConnected;
	}
	public ClientToClient(Socket clientConnected, Socket clientMsgTarget) { //calls after /connectClient
		this.clientConnected = clientConnected;
		this.clientMsgTarget = clientMsgTarget;
	}

}
