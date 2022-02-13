package utilities;

import Exceptions.EmptyQueueException;
import utilities.MyDLL;

/**
 * Class Description: This is a Queue class that embodies all the standard Queue operations, 
 * and includes several helper methods that will give the data structure more flexibility and use.
 * 
 * @author YunZe (David) Wei
 *
 * @param <E> Generic element this queue will hold upon creation
 */
public class MyQueue<E> implements QueueADT<E> {

	/**
	 * Auto generated UID
	 */
	private static final long serialVersionUID = 8137004225483108111L;

	private MyDLL<E> list;
	private final int qCapacity;

	/**
	 * Constructor for creating queue with pretty much no capacity
	 */
	public MyQueue() {
		this.list = new MyDLL<>();
		qCapacity = Integer.MAX_VALUE;
	}

	/**
	 * Constructor for creating a queue with a user defined capacity
	 */
	public MyQueue(int capacity) {
		this.list = new MyDLL<>();
		this.qCapacity = capacity;
	}
	
	/**
	 * Enqueue will place the added item at the last position in the
	 * queue.  This method will not allow <code>null</code> values
	 * to be added to the Queue.
	 * 
	 * @param toAdd the item to be added to the Queue.
	 * @throws NullPointerException raised when a <code>null</code> object
	 * is placed in the Queue.
	 */
	@Override
	public void enqueue(E toAdd) throws NullPointerException {
		// add to the DLL queue
		list.add(toAdd);
	}
	
	/**
	 * Dequeue will remove the first item that was placed in the Queue.
	 * @return the first item in the Queue.
	 * @throws EmptyQueueException raised when the queue's length is zero (0).
	 */
	@Override
	public E dequeue() throws EmptyQueueException {
		// throw exception if queue is empty
		if (list.size() == 0) {
			throw new EmptyQueueException();
		}

		// return and remove the first item in queue
		E firstItem = peek();
		list.remove(0);
		return firstItem;
	}
	
	/**
	 * Peek provides a reference to the first item in the queue without
	 * removing from the queue.
	 * 
	 * @return the first item in the queue.
	 * @throws EmptyQueueException raised when the queue's length is zero (0).
	 */
	@Override
	public E peek() throws EmptyQueueException {
		// throw exception if queue is empty
		if (list.size() == 0) {
			throw new EmptyQueueException();
		}
		// return the first item in the Queue.
		return list.get(0);
	}
	
	/**
	 * dequeueAll removes all items in the queue.
	 */
	@Override
	public void dequeueAll() {
		// remove all elements
		list.clear();
	}
	
	/**
	 * Returns <code>true</code> when the queue contains no items.
	 * @return <code>true</code> when queue length is zero (0).
	 */
	@Override
	public boolean isEmpty() {
		boolean isEmpty = false;

		if (list.size() == 0) {
			isEmpty = true;
		}

		// returns true if queue is empty
		return isEmpty;
	}
	
	/**
	 * Returns an iterator over the elements in this queue in proper sequence.
	 * 
	 * @return an iterator over the elements in this queue in proper sequence.
	 */
	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}
	
	/**
	 * Used to compare two Queue ADT's. To be equal two queues must contain
	 * equal items appearing in the same order.
	 * 
	 * @param that the Queue ADT to be compared to this queue.
	 * @return <code>true</code> if the queues are equal.
	 */
	@Override
	public boolean equals(QueueADT<E> that) {
		boolean equals = false;

		// checks the queues are the same size and elements in each position is the same
		if (list.size() == that.size()) {
			Object[] compareWith = that.toArray();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(compareWith[i])) {
					equals = true;
				} else {
					equals = false;
				}
			}
		} else {
			equals = false;
		}

		// returns true if the queues being compared are equal to each other
		return equals;
	}
	
	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence. Obeys the general contract of the Collection.toArray method.
	 * 
	 * @return an array containing all of the elements in this list in proper
	 *         sequence.
	 */
	@Override
	public Object[] toArray() {
		return list.toArray();
	}
	
	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence; the runtime type of the returned array is that of the specified
	 * array. Obeys the general contract of the Collection.toArray(Object[])
	 * method.
	 * 
	 * @param toHold
	 *            the array into which the elements of this queue are to be
	 *            stored, if it is big enough; otherwise, a new array of the
	 *            same runtime type is allocated for this purpose.
	 * @return an array containing the elements of this queue.
	 * @throws NullPointerException
	 *          if the specified array is null.
	 *//**
	 * (Optional Method) Returns true if the number of items in the queue
	 * equals the length.  This operation is only implement when a fixed length
	 * queue is required.
	 * @return <code>true</code> if queue is at capacity.
	 */
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return list.toArray(holder);
	}
	
	/**
	 * (Optional Method) Returns true if the number of items in the queue
	 * equals the length.  This operation is only implement when a fixed length
	 * queue is required.
	 * @return <code>true</code> if queue is at capacity.
	 */
	@Override
	public boolean isFull() {
		boolean isFull = false;
		if (list.size() == qCapacity) {
			isFull = true;
		}
		return isFull;
	}
	
	/**
	 * Returns the length of the current queue as an integer value.
	 * @return the current size to the queue as an integer.
	 */
	@Override
	public int size() {
		return list.size();
	}

}
