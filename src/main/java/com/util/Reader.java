package com.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

	private File inputfile;
	
	private File exclusionFile;
	
	private final String EXCLUSION_FILE_PATH = "input/exclusions.txt";
	
	private Scanner s;
	
	public Reader(String pathName) {
		inputfile = new File(pathName);
		exclusionFile = new File(EXCLUSION_FILE_PATH);
	}
	
	public List<String> readExclusions() throws FileNotFoundException{
		return readExclusionsFile();
	}
	
	public List<String> readInput() throws FileNotFoundException{
		return readInputFile();
	}
	
	private List<String> readInputFile() throws FileNotFoundException{
		s = new Scanner(inputfile);
		List<String> inputs = new ArrayList<String>();
		while(s.hasNext()){
			inputs.add(s.nextLine());
		}
		s.close();
		return inputs;
	}
	
	private List<String> readExclusionsFile() throws FileNotFoundException{
		s = new Scanner(exclusionFile);
		List<String> exclusions = new ArrayList<String>();
		while(s.hasNext()){
			exclusions.add(s.nextLine());
		}
		s.close();
		return exclusions;
	}
}
