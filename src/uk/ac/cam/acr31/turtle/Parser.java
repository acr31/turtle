package uk.ac.cam.acr31.turtle;

import java.util.Scanner;

public class Parser {

	public static Term parse(String program) {
		Scanner s = new Scanner(program);
		// this regexp splits on space or the gap after an opening bracket or
		// before a closing bracket
		s.useDelimiter(" +|(?<=\\()|(?=\\))");
		return parseScanner(s);
	}

	public static Term parseScanner(Scanner input) {
		input.next("\\(");
		String head = input.next("\\w+");
		Term result = new Term(head);
		while (true) {
			if (input.hasNext("\\(")) {
				result.add(parseScanner(input));
			} else if (input.hasNext("\\)")) {
				input.next("\\)");
				return result;
			} else {
				result.add(new Term(input.next("[A-Z0-9\\-\\.]+")));
			}
		}
	}
}
