package com.Squid0928;

import java.io.Serializable;

public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5105468294252428486L;
	private String name;
	private int money;
	public Person(String name, int money) {
		this.name = name;
		this.money = money;
	}
	public void setMoney(int input) {
		this.money = input;
	}
	public int getMoney() {
		return money;
	}
	public String getName() {
		return name;
	}
}
