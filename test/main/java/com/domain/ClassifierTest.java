package com.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassifierTest {

	private Classifier classifier;
	
	private List<String> inputItems = null;
	
	private List<String> exclusions = null;
	
	private String[] input,exclusion,inputWithImportedItem;
	
	@Before
	public void setUp() throws Exception {
		input = new String[]{"1 chocolate bar at 0.85",  "1 nokia n95 at 599.99"};
		exclusion = new String[]{"chocolate"};
		inputWithImportedItem = new String[]{"1 imported nokia n95 at 599.99"};
	}

	@After
	public void tearDown() throws Exception {
		inputItems = null;
		exclusions = null;
	}

	@Test
	public void testParserShouldParseItems() {
		Set<Item> expectedItems = new HashSet<Item>();
		expectedItems.add(new Item(1, " chocolate bar", 0.85, true, false));
		expectedItems.add(new Item(1, " nokia n95", 599.99, false, false));

		inputItems = Arrays.asList(input);
		exclusions = Arrays.asList(exclusion);
		classifier = new Classifier(inputItems, exclusions);
		List<Item> actualItems = classifier.classifyText();
		
		Assert.assertTrue(expectedItems.containsAll(actualItems));
	}
	
	@Test 
	public void testParserShouldDetectImportedItems(){
		Set<Item> expectedItems = new HashSet<Item>();
		expectedItems.add(new Item(1, " imported nokia n95", 599.99, false, true));
		inputItems = Arrays.asList(inputWithImportedItem);
		exclusions = Arrays.asList(exclusion);
		classifier = new Classifier(inputItems, exclusions);
		List<Item> actualItems = classifier.classifyText();
		
		Assert.assertTrue(expectedItems.containsAll(actualItems));
	}

}
