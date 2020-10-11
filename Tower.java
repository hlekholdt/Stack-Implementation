package prog05;

import java.util.Stack;
import prog02.UserInterface;
import prog02.GUI;

public class Tower {
  static UserInterface ui = new GUI("Towers of Hanoi");
	//static UserInterface ui = new TestUI2();

  static public void main (String[] args) {
    int n = getInt("How many disks?");
    if (n <= 0)
      return;
    Tower tower = new Tower(n);

    String[] commands = { "Human plays.", "Computer plays." };
    int c = ui.getCommand(commands);
    if (c == -1)
      return;
    if (c == 0)
      tower.play();
    else
      tower.solve();
  }

  /** Get an integer from the user using prompt as the request.
   *  Return 0 if user cancels.  */
  static int getInt (String prompt) {
    while (true) {
      String number = ui.getInfo(prompt);
      if (number == null)
        return 0;
      try {
        return Integer.parseInt(number);
      } catch (Exception e) {
        ui.sendMessage(number + " is not a number.  Try again.");
      }
    }
  }

  int nDisks;
  StackInt<Integer>[] pegs = (StackInt<Integer>[]) new ArrayStack[3];

  //constructor
  Tower (int nDisks) {
    this.nDisks = nDisks;
    for (int i = 0; i < pegs.length; i++)
      pegs[i] = new ArrayStack<Integer>();

    // EXERCISE: Initialize game with pile of nDisks disks on peg 'a' (pegs[0]).
    for(int i = nDisks; i > 0; i--) {
    	pegs[0].push(i);
    }
  }

  void play () {
    String[] moves = { "ab", "ac", "ba", "bc", "ca", "cb" };

    while (pegs[0] != null && pegs[1] != null) {
      displayPegs();
      int imove = ui.getCommand(moves);
      if (imove == -1)
        return;
      String move = moves[imove];
      int from = move.charAt(0) - 'a';
      int to = move.charAt(1) - 'a';
      move(from, to);
    }

    ui.sendMessage("You win!");
  }

  String stackToString (StackInt<Integer> peg) {
    StackInt<Integer> helper = new ArrayStack<Integer>();

    // String to append items to.
    String s = "";

    // EXERCISE:  append the items in peg to s from bottom to top.
    while (!peg.empty()) {
    	//take off of peg and put on to helper == reverse order now
    	helper.push(peg.pop());
    }
    while (!helper.empty()) {
    	//take off of helper and put back on peg == back to original to be printed!
    	s += " " + peg.push(helper.pop());
    }
    return s;
  }

  void displayPegs () {
    String s = "";
    for (int i = 0; i < pegs.length; i++) {
      char abc = (char) ('a' + i);
      s = s + abc + stackToString(pegs[i]);
      if (i < pegs.length-1)
        s = s + "\n";
    }
    ui.sendMessage(s);
  }

  void move (int from, int to) {
	
	if (!pegs[to].empty() && (pegs[from].peek() > pegs[to].peek()))
		ui.sendMessage("Cannot place disk 2 on top of disk 1.");
	else if (pegs[from].empty())
		ui.sendMessage("There is nothing at original disk");
	else
		pegs[to].push(pegs[from].pop());
  }

  // EXERCISE:  create Goal class.
  class Goal {
	  int num; //number of disks you want to move
	  int fromPeg; //0 for a, 1 for b, 2 for c
	  int toPeg; //0 for a, 1 for b, 2 for c
	  
	  
	  public Goal(int num, int fromPeg, int toPeg) {
		this.num = num;
		this.fromPeg = fromPeg;
		this.toPeg = toPeg;
	  }


	  public String toString () {
		  String[] pegNames = { "a", "b", "c" };
		  String s = "";
		  
		  s = "Move " + num + " disks from peg " + pegNames[fromPeg] + " to peg " + pegNames[toPeg] + "\n";
            
		  return s;
	  }
  }
  


  // EXERCISE:  display contents of a stack of goals
  public String displayGoals(StackInt<Goal> goal) {
	  
	  StackInt<Goal> helper = new ArrayStack<Goal>();
	  //helper stack to put them back in order!
	  
	  String s = "";
	  Goal realGoal;
	  
	  while (!goal.empty()) {
		  realGoal = goal.pop();
		  helper.push(realGoal);
		  s = s + realGoal.toString();
	  }
	  while (!helper.empty()) {
		  goal.push(helper.pop());
	  }
	  
	  return s;
	  
  }


  //so you push the last of the subgoals onto the goal stack to make it work???
  void solve () {
    // EXERCISE
	  StackInt<Goal> goalsStack = new ArrayStack<Goal>();
	  goalsStack.push(new Goal(nDisks,0,2));
	  
	  displayPegs();
	  
	  while (!(pegs[0].empty() && pegs[1].empty())){
		  ui.sendMessage(displayGoals(goalsStack));
		  if (goalsStack.peek().num == 1) {
			  //just move it there (no need to add break down goals)
			  move(goalsStack.peek().fromPeg,goalsStack.peek().toPeg);
			  goalsStack.pop();
			  displayPegs();
		  }
		  else {
			  //adding break down goals (moving more than 1 disk)
			  Goal realGoal = goalsStack.pop();
			  //real goal is removed because we are adding its subgoals
			  goalsStack.push(new Goal((realGoal.num - 1), (3 - realGoal.fromPeg - realGoal.toPeg), realGoal.toPeg));
			  //num = nDisks-1 &  fromPeg = 3 - from & toPeg = to)
			  goalsStack.push(new Goal(1, realGoal.fromPeg, realGoal.toPeg));
			  //num = 1 & fromPeg = from & toPeg = to
			  goalsStack.push(new Goal((realGoal.num-1),realGoal.fromPeg,3-realGoal.fromPeg-realGoal.toPeg));
			  //num = nDisks - 1 & toPeg = from & toPeg = 3 - from - to
		  }
	  }
	  ui.sendMessage("You won!");
  }        
}
