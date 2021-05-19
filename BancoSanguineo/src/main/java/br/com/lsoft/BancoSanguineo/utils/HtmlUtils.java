package br.com.lsoft.BancoSanguineo.utils;

import java.math.BigDecimal;

public class HtmlUtils {

	public static String createLink(String link, String text) {
		String tempText = "";
		if (text == null) {
			tempText = "";
		} else {
			tempText = text;
		}
		String createdLink = "<a href='" + link + "'>" + tempText + "</a>";
		return createdLink;
	}

	public static String createLink(String link, long text) {
		return createLink(link, String.valueOf(text)); 
	}

	public static String createLink(String link, BigDecimal text) {
		return createLink(link, text.toString());
	}

}
