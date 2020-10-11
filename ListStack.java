package prog05;

import java.util.EmptyStackException;
import java.util.List;
import java.util.ArrayList;

/** Implementation of the interface StackInt<E> using a List.
*   @author hle
*/

public class ListStack<E> implements StackInt<E> {
  // Data Fields
  /** Storage for stack. */
  List<E> theData;

  /** Initialize theData to an empty List. */
  public ListStack() {
    theData = new ArrayList<E>();
  }
  
  //Array Lists have methods: add(item), size(), get(index), remove()

  /** Pushes an item onto the top of the stack and returns the item
      pushed.
      @param obj The object to be inserted.
      @return The object inserted.
   */
  public E push (E obj) {
    theData.add(obj);
    return obj;
  }

  /**** EXERCISE ****/
  /** Returns the object at the top of the stack without removing it
  or changing the stack.
  @return The object at the top of the stack.
  @throws EmptyStackException if stack is empty.
   */
  public E peek() {
	  if (empty())
		  throw new EmptyStackException();
	  
	  return theData.get(theData.size()-1);
  }

  /** Returns the object at the top of the stack and removes it.
  post: The stack has one less item.
  @return The object at the top of the stack.
  @throws EmptyStackException if stack is empty.
   */
  public E pop() {
	  if (empty())
		  throw new EmptyStackException();
	  
	  E top = theData.get(theData.size()-1);
	  theData.remove(theData.size()-1);
	  return top;
  }

  /** Returns true if the stack is empty; otherwise it returns false.
  @return true if the stack is empty; false if not
   */
  public boolean empty() {
	  if ((theData.size()-1) == -1)
		  return true;
	  return false;
  }

}
