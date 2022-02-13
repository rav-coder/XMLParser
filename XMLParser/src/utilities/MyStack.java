package utilities;

import java.util.EmptyStackException;

import utilities.Iterator;
import utilities.StackADT;

/**
 * 
 * @author Nevyn D'Souza
 *
 * @param <E> Generic element this stack will hold upon creation
 */
public class MyStack<E> {

	private MyArrayList<E> data;
	private int size;

	public MyStack() {
		data = new MyArrayList<E>(10);
		size = 0;
	}

	/**
	 * This method will add an element to the top of the stack
	 * 
	 * precondition: The stack exists postcondition: Element is put into the top of
	 * the stack
	 * 
	 * @param add element to be added to the stack
	 * @throws NullPointerException when trying to add a null element to the stack
	 */
	public void push(E toAdd) throws NullPointerException {

		data.add(toAdd);
		size++;
	}

	/**
	 * This method will pop an element from the stack
	 * 
	 * precondition: The stack is not empty postcondition: Top element is returned
	 * and removed
	 * 
	 * @return the top element that is removed
	 * @throws IllegalStateException trying to pop off a empty stack
	 */
	public E pop() throws EmptyStackException {
		if (size == 0)
			throw new EmptyStackException();

		E e = data.remove(size - 1);
		size--;
		return e;
	}

	/**
	 * This method will get the top element of the stack without deleting it
	 * 
	 * precondition: The stack is not empty postcondition: The top element is
	 * returned
	 * 
	 * @return the top element
	 * @throws IllegalStateException trying to peek a empty stack
	 */
	public E peek() throws EmptyStackException {
		if (size == 0)
			throw new EmptyStackException();

		return data.get(size - 1);
	}

	/**
	 * This method will convert the stack into an array
	 * 
	 * precondition: No condition postcondition: returns an array containing all the
	 * elements
	 * 
	 * @return an array with all the elements from the stack
	 */
	public Object[] toArray() {
		return data.toArray();
	}

	/**
	 * This method will search an element and get the distance between the element
	 * and the top element
	 * 
	 * precondition: Array elements are sorted in increasing order postcondition:
	 * Returns element or -1 if not in the array
	 * 
	 * @return the distance between the element and top element & -1 if the element
	 *         id not found
	 * @param object object to search for to get the distance between it and the top
	 *               element
	 */
	public int search(E Object) {
		for (int i = size - 1; i >= 0; i--) {
			if (data.get(i).equals(Object)) {
				return size - i;
			}
		}
		return -1;
	}

	/**
	 * This method will check if the stack has an element or not
	 * 
	 * precondition: Is the element an object postcondition: Returns true if stack
	 * has the element, false if it is not there
	 * 
	 * @return true if the stack has the element
	 * @param object object to check if it is in the stack
	 */
	public boolean contains(E Object) throws NullPointerException {
		return data.contains(Object);
	}

	/**
	 * Clears all the elements from the Stack.
	 * 
	 * Precondition: A stack must exist. Postcondition: Stack with depth 0 and
	 * control returns to the caller automatically at the end of the function,
	 * unless there is an error.
	 */
	public void clear() {
		data.clear();
		size = 0;
	}

	/**
	 * Returns the number of elements in a stack (depth) as an integer value.
	 * 
	 * Precondition: A stack must exist. Postcondition: A integer value representing
	 * the depth of the stack.
	 * 
	 * @return integer representing stack depth
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns true if the stack contains no elements.
	 * 
	 * Precondition: A stack must exist. Postcondition: true or false boolean
	 * 
	 * @return true if the stack contains no elements
	 */
	public boolean isEmpty() {
		return size == 0 && data.isEmpty();
	}

	/**
	 * Compares two stacks and checks if they are equal. To be equal, the two stacks
	 * must contain equal items appearing in the same order.
	 * 
	 * Precondition: Both the stacks to be compared must exist Postcondition: true
	 * or false boolean
	 * 
	 * @param compare the stack to be compared with
	 * @return true if stacks are equal
	 */
	public boolean equals(StackADT<E> compare) {
		if (compare == null)
			return false;
		else if (data.size() != compare.size())
			return false;
		else {
			Object[] arr = compare.toArray();
			for (int i = 0; i < size; i++) {
				if (!data.get(i).equals(arr[i])) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * returns an iterator over the items contained in the stack
	 * 
	 * Precondition: Stack must exist. Postcondition: Iterator is returned
	 * 
	 * @return an iterator over the items contained in the stack
	 */
	public Iterator<E> iterator() {
		return data.iterator();
	}

	/**
	 * Copies all of the elements in the stack into an array and returns the array.
	 * Top of the stack correspond to the first element of the array
	 * 
	 * Precondition: Stack must exist, array to be copied into must exist.
	 * Postcondition: array of copied elements
	 * 
	 * @param copy the array the copied stack elements will be placed into
	 * @return the copied stack in array form
	 * @throws NullPointerException if the array copy is null
	 */
	public E[] toArray(E[] copy) throws NullPointerException {
		return data.toArray(copy);
	}
}
