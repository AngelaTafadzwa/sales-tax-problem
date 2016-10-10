package com.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.domain.Classifier;
import com.domain.Item;
import com.domain.TaxCalculator;
import com.util.Printer;
import com.util.Reader;

public class TaxCalculatorServiceImpl implements TaxCalculatorService{

	private static class TaxCalculatorServiceImplLoader {
        private static final TaxCalculatorServiceImpl INSTANCE = new TaxCalculatorServiceImpl();
    }
	
    private TaxCalculatorServiceImpl() {
        if (TaxCalculatorServiceImplLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }
    
    public static TaxCalculatorServiceImpl getInstance() {
        return TaxCalculatorServiceImplLoader.INSTANCE;
    }
    
	@Override
	public void calculateTax(String filePath) throws FileNotFoundException {
		Reader reader = read(filePath);
		List<Item> items = classifyItems(reader.readInput(), reader.readExclusions());
		TaxCalculator taxCalculator = calculate(items);
		taxCalculator.computeTax();
		print(items, taxCalculator.getSalesTaxes(), taxCalculator.getTotalAll());
	}

	private Reader read(String filePath){
		Reader reader = new Reader(filePath);
		return reader;
	}
	
	private List<Item> classifyItems(List<String> inputText, List<String> exclusions){
		return new Classifier(inputText, exclusions).classifyText();
	}
	
	private TaxCalculator calculate(List<Item> items){
		return new TaxCalculator(items);
	}
	
	private void print(List<Item> items, double salesTaxes, double total){
		Printer consolePrinter = new Printer(items, salesTaxes, total);
		consolePrinter.print();
	}
}
