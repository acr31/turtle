package uk.ac.cam.acr31.turtle;

import java.util.HashMap;

public class Evaluator {

	private LogoViewer viewer;
	private Turtle turtle;
	private HashMap<String, Double> env;

	public Evaluator(LogoViewer viewer) {
		this.viewer = viewer;
		this.turtle = new Turtle(LogoViewer.SIZE / 2, LogoViewer.SIZE / 2,0);
		this.env = new HashMap<String, Double>();
	}
	
	@SuppressWarnings("unchecked")
	public Evaluator(Evaluator e) {
		this.viewer = e.viewer;
		this.turtle = new Turtle(e.turtle);
		this.env = (HashMap<String,Double>)e.env.clone();
	}

	public Term eval(final Term term) {
		String cmd = term.getCommand();
		if (cmd.equals(Commands.FORWARD)) {
			int x1 = (int)turtle.getX();
			int y1 = (int)turtle.getY();
			Term a = eval(term.first());
			turtle.forward(a.getDouble());
			int x2 = (int)turtle.getX();
			int y2 = (int)turtle.getY();
			viewer.drawLine(x1, y1, x2, y2,turtle.getColor());
			return a;
		} else if (cmd.equals(Commands.TURN)) {
			Term a = eval(term.first());
			turtle.turn(a.getDouble());
		} else if (cmd.equals(Commands.SEQ)) {
			for (Term s : term.getArguments()) {
				eval(s);
			}
		} else if (cmd.equals(Commands.MULT)) {
			double first = eval(term.first()).getDouble();
			double second = eval(term.second()).getDouble();
			return new Term(first*second);
		} else if (cmd.equals(Commands.PLUS)) {
			double first = eval(term.first()).getDouble();
			double second = eval(term.second()).getDouble();
			return new Term(first+second);
		} else if (cmd.equals(Commands.SET)) {
			String varName = term.first().getCommand();
			double value = eval(term.second()).getDouble();
			env.put(varName, value);
		} else if (cmd.equals(Commands.REPEAT)) {
			double numRepeats = eval(term.first()).getDouble();
			while(numRepeats-- > 0.f) {
				eval(term.second());
			}
		} else if (cmd.equals(Commands.EVENONES)) {
			int val = (int)(eval(term.first()).getDouble());
			int evenones = Integer.bitCount(val) % 2;
			return new Term(evenones);
		} else if (cmd.equals(Commands.IF)) {
			if (eval(term.first()).getDouble() > 0.f) {
				return eval(term.second());
			}
			else {
				return eval(term.third());
			}
		} else if (cmd.equals(Commands.SPAWN)) {
			final Evaluator e = new Evaluator(this);
			new Thread() {
				public void run() {
					 e.eval(term.first());
				}
			}.start();
		}
		else { 
			try {
				term.getDouble();
				return term;
			}
			catch (NumberFormatException e) {
				return new Term(env.get(term.getCommand()));
			}
		}
		return null;
	}


}