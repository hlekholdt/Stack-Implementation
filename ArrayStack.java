package prog05;

import java.util.Arrays;
import java.util.EmptyStackException;
//special exception for stacks

import prog02.DirectoryEntry;

/** Implementation of the interface StackInt<E> using an array.
*   @author hle
*/

public class ArrayStack<E> implements StackInt<E> {
  // Data Fields
  /** Storage for stack. */
  E[] theData;
  //an array of type E

  /** Index to top of stack. */
  int top = -1; // initially -1 because there is no top == empty!

  private static final int INITIAL_CAPACITY = 2;

  /** Construct an empty stack with the default initial capacity. */
  public ArrayStack () {
    theData = (E[])new Object[INITIAL_CAPACITY];
    //casting it as type E!
  }

  /** Pushes an item onto the top of the stack and returns the item
      pushed.
      @param obj The object to be inserted.
      @return The object inserted.
   */
  public E push (E obj) {
    top++;
    //open a spot for new object

    if (top == theData.length)
    	//we've gone past the capacity of the array
      reallocate();

    theData[top] = obj;
    //put the new thing at the top of the stack
    return obj;
  }

  /** Returns the object at the top of the stack and removes it.
      post: The stack is one item smaller.
      @return The object at the top of the stack.
      @throws EmptyStackException if stack is empty.
   */
  public E pop () {
    if (empty())
      throw new EmptyStackException();
    
    /**** EXERCISE ****/
    E remove = theData[top];
    top--;
    return remove;

  }

  /** Returns the object at the top of the stack without removing it.
      post: The stack remains unchanged.
      @return The object at the top of the stack.
      @throws EmptyStackException if stack is empty.
   */
  public E peek () {
    /**** EXERCISE ****/
	  //throw exception if it is empty
	  if (empty())
	      throw new EmptyStackException();
	  
	  return theData[top];
  }

  /**** EXERCISE ****/
  /** Returns true if the stack is empty; otherwise it returns false.
  @return true if the stack is empty; false if not
   */
  public boolean empty() {
	  if (top == -1) {
		  return true;
	  }
	  return false;
  }
  
  public void reallocate() {
	  //do this?
	  E[] newData = Arrays.copyOf(theData, theData.length * 2);
	  theData = newData;
  }


}
