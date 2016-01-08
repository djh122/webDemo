package com.djh.test;

import java.math.BigDecimal;

public class TestBean {

	private String name;
	
	private int age;
	
	private BigDecimal account;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}
}
