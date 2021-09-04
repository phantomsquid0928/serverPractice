package com.Squid0928;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	public static Queue<Person> q = new LinkedList<>();
	public static void save() {
		File file = new File("input.txt");
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			while(!q.isEmpty()) {
				oos.writeObject(q.poll());
			}
			oos.writeObject(null);
			oos.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	public static void load() {
		File file = new File("input.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			while(true) {
				Person p = (Person)ois.readObject();
				
				if (p == null) break;
				
				q.add(p);
			}
			ois.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	public static void main(String[] args) {
		Person p = new Person("squid", 1000);
		Person p2 = new Person("phantom", 1200);
		q.add(p);
		q.add(p2);
		save();
		load();
		System.out.println(q.peek().getName());
	}

}
