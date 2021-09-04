package com.Squid0928;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerMain {
	public static List<Socket> clientList = new ArrayList<>();
	public static HashMap<Socket, Runnable> handlingOutputs = new HashMap<>();
	
	public static void main(String[] args) {
		try {
			ServerSocket sc = new ServerSocket(1001);
			while(true) {
				Socket client = sc.accept();
				System.out.println("client: " + client.getInetAddress() + "has connected");
				Runnable temp = new ClientToClient(client);
				Runnable temp2 = new ClientOutput(client);
				Thread th = new Thread(temp);
				Thread th2 = new Thread(temp2);
				th.start();
				th2.start();
				clientList.add(client);
				handlingOutputs.put(client, temp2);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
