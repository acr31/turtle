This is a simple Turtle graphics interpreter.  Its an extended version of an interpreter I live coded during a talk.

It can process the following commands:

 * (TURN <angle>)
 * (FORWARD <distance>)
 * (SEQ <command> <command> ...) 
 * (REPEAT <count> <command>) 
 * (IF <expr> <command_if_gt_0> <command_otherwise>)
 * (SET <variable> <command>)
 * (MULT <command> <command>)
 * (PLUS <command> <command>)
 * (EVENONES <command>)  - count the number of 1-bits mod 2
 * (SPAWN <command>)  - run the command in parallel in a new turtle

EVENONES can be used to draw a Koch curve - see the examples in the Main class.

