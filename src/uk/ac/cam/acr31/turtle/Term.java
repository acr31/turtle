package uk.ac.cam.acr31.turtle;

import java.util.LinkedList;
import java.util.List;

public class Term {

	private String command;
	private List<Term> arguments;

	public Term(String head) {
		arguments = new LinkedList<Term>();
		this.command = head;
	}

	public Term(double value) {
		this(value+"");
	}
	
	public List<Term> getArguments() {
		return arguments;
	}

	public String getCommand() {
		return command;
	}

	public double getDouble() {
		return Double.parseDouble(command);
	}

	public Term first() {
		return arguments.get(0);
	}

	public Term second() {
		return arguments.get(1);
	}

	public Term third() {
		return arguments.get(2);
	}

	public void add(Term term) {
		arguments.add(term);
	}

	public String toString() {
		return command + " " + arguments;
	}
}
