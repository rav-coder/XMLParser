package utilities;

/**
 * 
 * Class Description: This is the node class for the doubly-linked-list. It contains the data for a node and the references to
 * the previous and next node.
 *
 * @author Saurav Adhikari (831561)
 *
 */
public class MyDLLNode<E> {
	E data; //actual data in the node
	
	MyDLLNode<E> next; //reference to the next node
	
	MyDLLNode<E> prev; //reference to the previous node
	
	/**
	 * 
	Initializes the newly createdMyDLLNode
	@param data the data in a node
	 */
	MyDLLNode(E data){
		this.data = data;
		this.prev = null;
		this.next= null;
	}
	
	/**
	 * 
	Initializes the newly createdMyDLLNode
	@param prev reference to previous node
	@param data the data in a node
	@param next reference to the next node
	 */
	MyDLLNode(MyDLLNode<E> prev, E data, MyDLLNode<E> next){
		this.data = data;
		this.prev = prev;
		this.next = next;
	}

	/**
	 * @return the data in the current node
	 */
	public E getData() {
		return data;
	}

	/**
	 * @param data the data to set in the current node
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * @return the next node
	 */
	public MyDLLNode<E> getNext() {
		return next;
	}

	/**
	 * @param next set the next node
	 */
	public void setNext(MyDLLNode<E> next) {
		this.next = next;
	}

	/**
	 * @return the previous node
	 */
	public MyDLLNode<E> getPrev() {
		return prev;
	}

	/**
	 * @param prev set the previous node
	 */
	public void setPrev(MyDLLNode<E> prev) {
		this.prev = prev;
	}
	
	
}
