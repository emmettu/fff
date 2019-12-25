package com.emmett.game.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Random;

public class ListingGetter {

	private static final String LISTING_URL_TEMPLATE = "https://www.trademe.co.nz/browse/rentals/regionlistings.aspx?134=1&rptpath=2975-&key=1246845347&page=%s&sort_order=default";

	private final Random random;

	private static final int MAX_PAGE = 30;

	public ListingGetter() {
		random = new Random();
	}

	public String randomListing() {
		int page = random.nextInt(MAX_PAGE);
		String url = String.format(LISTING_URL_TEMPLATE, page);
		try {
			Document doc = Jsoup.connect(url).get();
			Elements listings = doc.select("a[href*=/flatmates-wanted/auction]");
			int randomIndex = random.nextInt(listings.size());
			return listings.get(randomIndex).attr("abs:href");
		} catch (IOException e) {
			return null;
		}

	}
}
