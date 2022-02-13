package utilities;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * 
 * Class Description: This is the class for the doubly-linked-list (DLL). It is
 * an implementation of the ListADT class for a DLL.
 *
 * @author Saurav Adhikari (831561)
 *
 */
public class MyDLL<E> implements ListADT<E> {

	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;

	/**
	 * The size method will return the current element count contained in the list.
	 * 
	 * @return The current element count.
	 */
	@Override
	public int size() {

		int size = 0;
		MyDLLNode<E> current = head;

		// increment size until the position of null element is reached which is the end
		// of the list
		while (current != null) {
			size++;
			current = current.getNext();
		}
		return size;
	}

	/**
	 * Removes all of the elements from this list. This list will be empty after
	 * this call returns.
	 */
	@Override
	public void clear() {
		head = tail = null;

	}

	/**
	 * Inserts the specified element at the specified position in this list. Shifts
	 * the element currently at that position (if any) and any subsequent elements
	 * to the right (adds one to their indices).
	 * 
	 * @param index
	 *            The index at which the specified element is to be inserted. The
	 *            element is inserted before the existing element at [index], or at
	 *            the end if index is equal to the size (<code>size()</code>).
	 * @param toAdd
	 *            The element to be inserted.
	 * @return <code>true</code> if the element is added successfully.
	 * @throws NullPointerException
	 *             If the specified element is <code>null</code> and the list
	 *             implementation does not support having <code>null</code>
	 *             elements.
	 * @throws IndexOutOfBoundsException
	 *             If the index is out of range: i.e.
	 *             (<code>index < 0 || index > size()</code>).
	 */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (toAdd == null)
			throw new NullPointerException("Can not have a null element in the list");

		if (index < 0 || index > this.size())
			throw new IndexOutOfBoundsException("The specified index is out of bounds in the DLL");
		else {

			// add to the beginning of the list
			if (index == 0) {
				MyDLLNode<E> newNode = new MyDLLNode<E>(null, toAdd, head);
				head = newNode;
			}

			// add to the end of list
			if (index == this.size()) {
				add(toAdd);
			}

			// add to any other position
			MyDLLNode<E> prev = this.head;
			for (int i = 0; i < index; i++) {
				prev = prev.getNext();
			}

			MyDLLNode<E> next = prev.getNext();
			new MyDLLNode<E>(prev, toAdd, next);

			return true;
		}

	}

	/**
	 * Appends the specified element to the end of this list. Implementations that
	 * support this operation may place limitations on what elements may be added to
	 * this list. In particular, some implementations will refuse to add
	 * <code>null</code> elements. List classes should clearly specify in their
	 * documentation any restrictions on what elements may be added.
	 * 
	 * @param toAdd
	 *            Element to be appended to this list.
	 * @return true if element is appended successfully.
	 * @throws NullPointerException
	 *             If the specified element is <code>null</code> and the list
	 *             implementation does not support having <code>null</code>
	 *             elements.
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd == null)
			throw new NullPointerException("Can not have a null element in the list");

		MyDLLNode<E> newNode = new MyDLLNode<E>(tail, toAdd, null);

		// add to the head if list is empty else add to tail
		if (head == null)
			head = newNode;
		else
			tail.setNext(newNode);

		tail = newNode;

		return true;
	}

	/**
	 * Appends all of the elements in the specified <code>java.utilCollection</code>
	 * to the end of this list, in the order that they are returned by the specified
	 * collection's <code>Iterator</code>. The behaviour of this operation is
	 * unspecified if the specified collection is modified while the operation is in
	 * progress. (Note that this will occur if the specified collection is this
	 * list, and it's nonempty.)
	 * 
	 * @param toAdd
	 *            The new sub list to be added.
	 * @return true If the operation is successful.
	 * @throws NullPointerException
	 *             If the specified element is <code>null</code> and the list
	 *             implementation does not support having <code>null</code>
	 *             elements.
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if (toAdd == null)
			throw new NullPointerException("Can not have a null element in the list");

		Object[] array = toAdd.toArray();

		// Add the array elements to the end of the list
		for (int i = 0; i < array.length; i++) {
			@SuppressWarnings("unchecked")
			E item = (E) array[i];
			add(item);
		}

		return true;
	}

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index
	 *            Index of element to return.
	 * @return The element at the specified position in this list.
	 * @throws IndexOutOfBoundsException
	 *             If the index is out of range: i.e.
	 *             (<code>index < 0 || index >= size()</code>).
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.size())
			throw new IndexOutOfBoundsException("The specified index is out of bounds in the DLL");
		else {
			MyDLLNode<E> current = this.head;

			// traverse the DLL and get data from a specified index
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			return current.getData();
		}

	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices). Returns
	 * the element that was removed from the list.
	 * 
	 * @param index
	 *            The index of the element to remove.
	 * @return The removed element.
	 * @throws IndexOutOfBoundsException
	 *             If the index is out of range: i.e.
	 *             (<code>index < 0 || index >= size()</code>).
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.size())
			throw new IndexOutOfBoundsException("The specified index is out of bounds in the DLL");
		else {
			E removedE;

			// if DLL has one node clear the list
			if (this.size() == 1) {
				removedE = this.head.getData();
				this.clear();
			}

			// if the index points to head
			else if (index == 0) {
				removedE = this.head.getData();
				this.head = this.head.getNext();
			}

			// if the index points to tail
			else if (index == (this.size() - 1)) {
				removedE = this.tail.getData();
				tail = tail.getPrev();
				tail.setNext(null);
			}

			// remove nodes in all other indexes
			else {
				MyDLLNode<E> prev = this.head;

				for (int i = 1; i < index; i++) {
					prev = prev.getNext();
				}

				MyDLLNode<E> remove = prev.getNext();
				removedE = remove.getData();

				MyDLLNode<E> next = remove.getNext();
				prev.setNext(next);
				next.setPrev(prev);
			}
			return removedE;
		}
	}

	/**
	 * Removes the first occurrence in this list of the specified element. If this
	 * list does not contain the element, it is unchanged. More formally, removes
	 * the element with the lowest index <code>i</code> such that
	 * <code>o.equals(get(i))</code> (if such an element exists).
	 * 
	 * @param toRemove
	 *            The element to be removed from this list.
	 * @return The element which is being removed, or null if the list does not
	 *         contain the element.
	 * @throws NullPointerException
	 *             If the specified element is <code>null</code> and the list
	 *             implementation does not support having <code>null</code>
	 *             elements.
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (toRemove == null)
			throw new NullPointerException("Can not have a null element in the list");
		MyDLLNode<E> current = this.head;
		E removeData = null;

		// find the index of the element to be removed and remove if found
		for (int i = 0; i < this.size(); i++) {
			if (current.getData().equals(toRemove)) {
				removeData = current.getData();
				this.remove(i);
				break;
			}
			current = current.getNext();
		}
		return removeData;
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 * 
	 * @param index
	 *            The index of the element to replace.
	 * @param toChange
	 *            Element to be stored at the specified position.
	 * @return The element previously at the specified position.
	 * @throws NullPointerException
	 *             If the specified element is <code>null</code> and the list
	 *             implementation does not support having <code>null</code>
	 *             elements.
	 * @throws IndexOutOfBoundsException
	 *             If the index is out of range: i.e.
	 *             (<code>index < 0 || index >= size()</code>).
	 */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (toChange == null)
			throw new NullPointerException("Can not have a null element in the list");
		if (index < 0 || index >= this.size())
			throw new IndexOutOfBoundsException("The specified index is out of bounds in the DLL");

		// find the node in the specified index and change it to the specified value
		MyDLLNode<E> current = head;
		for (int i = 1; i <= index; i++) {
			current = current.getNext();
		}

		current.setData(toChange);
		return current.getData();

	}

	/**
	 * Returns <code>true</code> if this list contains no elements.
	 * 
	 * @return <code>true</code> if this list contains no elements.
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element
	 * <code>e</code> such that <code>toFind.equals(e)</code>.
	 * 
	 * @param toFind
	 *            The element whose presence in this list is to be tested.
	 * @return <code>true</code> if this list contains the specified element.
	 * @throws NullPointerException
	 *             If the specified element is <code>null</code> and the list
	 *             implementation does not support having <code>null</code>
	 *             elements.
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null)
			throw new NullPointerException("Can not have a null element in the list");
		MyDLLNode<E> current = this.head;
		boolean found = false;

		// if the element is found in the list return true else false
		for (int i = 0; i < this.size(); i++) {
			if (current.getData().equals(toFind)) {
				found = true;
				break;
			}
			current = current.getNext();
		}
		return found;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence; the runtime type of the returned array is that of the specified
	 * array. Obeys the general contract of the
	 * <code>java.util.Collection.toArray(Object [])</code> method.
	 * 
	 * @param toHold
	 *            The array into which the elements of this list are to be stored,
	 *            if it is big enough; otherwise, a new array of the same runtime
	 *            type is allocated for this purpose.
	 * @return An array containing the elements of this list.
	 * @throws NullPointerException
	 *             If the specified array is <code>null</code>.
	 */
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		MyDLLNode<E> node = head;

		// if the size of toHold does not fit the DLL
		if (this.size() > toHold.length) {
			@SuppressWarnings("unchecked")
			E[] toHoldSize = (E[]) Array.newInstance(toHold.getClass().getComponentType(), this.size());
			toHold = toHoldSize;
		}

		// traverse the node and copy the data in the node into the array
		for (int i = 0; i < this.size(); i++) {
			toHold[i] = node.getData();
			node = node.getNext();
		}

		return toHold;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence. Obeys the general contract of the
	 * <code>java.util.Collection.toArray()</code> method.
	 * 
	 * @return An array containing all of the elements in this list in proper
	 *         sequence.
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[this.size()];
		MyDLLNode<E> node = this.head;

		// traverse the node and copy the data in the node into the array
		for (int i = 0; i < this.size(); i++) {
			array[i] = node.getData();
			node = node.getNext();
		}

		return array;
	}

	/**
	 * Returns an iterator over the elements in this list, in proper sequence.
	 * 
	 * @return An iterator over the elements in this list, in proper sequence. NB:
	 *         The return is of type <code>linearUtilities.Iterator<E></code>, not
	 *         <code>java.util.Iterator</code>.
	 */
	@Override
	public Iterator<E> iterator() {
		return new DLLIterator(this);
	}

	/**
	 * 
	 * Class Description: Iterator class for the doubly-linked-list.
	 *
	 * @author Saurav Adhikari (831561)
	 *
	 */
	private class DLLIterator implements Iterator<E> {

		private MyDLLNode<E> current;

		/**
		 * 
		 * Initializes the newly createdDLLIterator
		 * 
		 * @param myDLL
		 *            the list to be iterated.
		 */
		public DLLIterator(MyDLL<E> myDLL) {
			current = myDLL.head;
		}

		/**
		 * Returns <code>true</code> if the iteration has more elements. (In other
		 * words, returns <code>true</code> if <code>next()</code> would return an
		 * element rather than throwing an exception.)
		 * 
		 * @return <code>true</code> if the iterator has more elements.
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * Returns the next element in the iteration.
		 * 
		 * @return The next element in the iteration.
		 * @throws NoSuchElementException
		 *             If the iteration has no more elements.
		 */
		@Override
		public E next() throws NoSuchElementException {

			// move the iterator one node at a time
			if (current == null)
				throw new NoSuchElementException("This iteration has no more elements.");
			else {
				E data = current.getData();
				current = current.getNext();
				return data;
			}

		}
	}

}
