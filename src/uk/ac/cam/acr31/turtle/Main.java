package uk.ac.cam.acr31.turtle;


public class Main {

	public static void main(String[] args) {
		
		/* For the koch curve we draw values from the Thue-Morse Sequence to work out
		 * whether to turn or go forward.  To directly work out the nth item of this 
		 * sequence we need to count the number of 1 bits set in n....
		 * 
		 * http://en.wikipedia.org/wiki/Koch_snowflake
		 * http://en.wikipedia.org/wiki/Thue%E2%80%93Morse_sequence
		 * http://en.wikipedia.org/wiki/Thue%E2%80%93Morse_sequence#Direct_definition
		 */
		
		String[] examples = {
				"(SEQ (SET D 2) (REPEAT 184 (SEQ (FORWARD D) (TURN 89.5) (SET D (PLUS D 2)))))", // spiral
				"(REPEAT 36 (SEQ (TURN 10) (REPEAT 36 (SEQ (TURN 10) (FORWARD 20)))))", // flower
				"(REPEAT 3 (SEQ (SET I 0) (REPEAT 4004 (SEQ (IF (EVENONES I) (TURN 60) (FORWARD 1)) (SET I (PLUS I 1)))) (TURN -120)))", // koch curve
				"(REPEAT 5 " // parallel koch curve
				+ "       (SEQ (TURN 72) "
				+ "            (SPAWN (SEQ (SET I 0) "
				+ "                     (REPEAT 4004 "
				+ "                             (SEQ (IF (EVENONES I) "
				+ "                                      (TURN 60) "
				+ "                                      (FORWARD 1)) "
				+ "                             (SET I (PLUS I 1)))))))) ",
				"(REPEAT 5" // parallel flower
				+ "                (SEQ (TURN 72)"
				+ "                     (SPAWN (SEQ (FORWARD 100)"
				+ "                              (REPEAT 36 (SEQ (TURN 10) (REPEAT 36 (SEQ (TURN 10) (FORWARD 5))))))))))",
				"(SEQ " // crazy combination of both
				+ "   (REPEAT 36 (SEQ (TURN 10) (REPEAT 36 (SEQ (TURN 10) (FORWARD 10)))))"
				+ "   (REPEAT 8 "
				+ "       (SEQ (TURN 45) "
				+ "            (SPAWN (SEQ (SET I 0) "
				+ "                     (REPEAT 4004 "
				+ "                             (SEQ (IF (EVENONES I) "
				+ "                                      (TURN 60) "
				+ "                                      (FORWARD 1)) "
				+ "                             (SET I (PLUS I 1))))"
				+ "                     (REPEAT 36 (SEQ (TURN 10) (REPEAT 36 (SEQ (TURN 10) (FORWARD 5))))))))))" };
		
		int i = 0;
		if (args.length > 0) {
			i = Integer.parseInt(args[0]);
		}
		
		LogoViewer viewer = new LogoViewer();
		Term p = Parser.parse(examples[i]);
		new Evaluator(viewer).eval(p);
	}

}
