package com.domain;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaxCalculatorTest {
	
	private List<Item> items = null;
	
	private TaxCalculator taxCalculator;

	@Before
	public void setUp() throws Exception {
		items = new ArrayList<Item>();
		items.add(new Item(1, " chocolate bar", 250, true, false));
		items.add(new Item(1, " nokia n95", 500, false, true));
	}

	@After
	public void tearDown() throws Exception {
		items = null;
	}

	@Test
	public void testCalculateTax() {
		taxCalculator = new TaxCalculator(items);
		taxCalculator.computeTax();
		
		Assert.assertEquals(825, taxCalculator.getTotalAll());
		Assert.assertEquals(75, taxCalculator.getTotalAll());
	}

}
