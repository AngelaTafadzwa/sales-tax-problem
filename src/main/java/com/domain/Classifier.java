package com.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Joiner;

public class Classifier {

	private final List<String> inputText;
	
	private final List<String> exclusions;
	
	private final String REGEX_CLASSIFIER = "(\\d+)([a-zA-Z0-9_\\s]+?)( at )(\\d+.\\d+)";
	
	private final String IMPORTED_CLASSIFIER = "IMPORTED";
	
	public Classifier(List<String> inputText, List<String> exclusions){
		this.inputText = inputText;
		this.exclusions = exclusions;
	}
	
	public List<Item> classifyText(){
		List<Item> items = new ArrayList<Item>();
		for (String item : inputText) {
			items.add(parser(item));
		}
		return items;
	}
	
	private Item parser(String itemText){
		Pattern textPattern = Pattern.compile(REGEX_CLASSIFIER, Pattern.CASE_INSENSITIVE);
		Matcher textMatcher = textPattern.matcher(itemText);
		Integer qty = null;
		Double price = null;
		String desc = null;
		if (textMatcher.find()){
			qty = Integer.parseInt(textMatcher.group(1));
			price = Double.parseDouble(textMatcher.group(4));
			desc = textMatcher.group(2);
		}
		return new Item(qty, desc, price, classifyExcluded(itemText), classifyImported(itemText));
	}
	
	private boolean classifyExcluded(String itemText){
		Joiner joiner = Joiner.on("|");
		Pattern exclusionPattern = Pattern.compile(joiner.join(exclusions), Pattern.CASE_INSENSITIVE);
		Matcher exclusionMatcher = exclusionPattern.matcher(itemText);
		return exclusionMatcher.find();
	}
	
	private boolean classifyImported(String itemText){
		Pattern importPattern = Pattern.compile(IMPORTED_CLASSIFIER, Pattern.CASE_INSENSITIVE);
		Matcher importMatcher = importPattern.matcher(itemText);
		return importMatcher.find();
	}
	
}
