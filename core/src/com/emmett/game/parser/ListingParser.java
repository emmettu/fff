package com.emmett.game.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ListingParser {

	public Listing parseListing(String url) {

		try {
			Map<String, String> detailMap = new HashMap<>();
			Document doc = Jsoup.connect(url).get();

			String name = doc.selectFirst("a[id=MemberLink]").text();

			String price = doc.selectFirst("div[class=title-price]").text();

			Elements details = doc.selectFirst("table[id=ListingAttributes]").select("tr");

			for(Element row: details) {
				String label = row.selectFirst("th").text();
				String value = row.selectFirst("td").text();
				detailMap.put(label, value);
			}
			return new Listing(name, price, detailMap);
		} catch (IOException e) {
			return null;
		}
	}
}
