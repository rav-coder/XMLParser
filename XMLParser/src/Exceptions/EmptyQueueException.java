package Exceptions;

public class EmptyQueueException extends Exception{

	/**
	 * Auto generated UID for exception
	 */
	private static final long serialVersionUID = -4439194494955567054L;

	public EmptyQueueException() {
		super("Cannot perform action, queue is empty!");
	}
	
	public EmptyQueueException(String userMessage) {
		super(userMessage);
	}
}
