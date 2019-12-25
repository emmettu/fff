package com.emmett.game.parser;

import java.util.Map;

public class Listing {

	private String name;
	private String price;
	private Map<String, String> details;

	public Listing(String name, String price, Map<String, String> details) {
		this.name = name;
		this.details = details;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}

	public String getDetail(String detail) {
		return details.get(detail + ":");
	}
}
