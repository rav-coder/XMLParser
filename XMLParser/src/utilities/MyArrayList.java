package utilities;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;



/**
 * @author 802302 Yisong Wang
 *
 * @param <E> ArrayList class implementing by ListADT made by instructor
 */
public class MyArrayList <E> implements ListADT<E>{
	private static final long serialVersionUID = -3578858266903529815L;
	
	private int size;
	private Object[] data;
	private final Object[] DEFAULT_DATA = {};
	private final int DEFAULT_CAPACITY = 10;
	
	public MyArrayList () {
		this.data = DEFAULT_DATA;
	}
	
	/**
	 * 
	 * This is the constructor of MyArrayList
	 * 
	 * precondition: This method will check the array and it will return false if the array is empty
	 * 
	 * @param i the initial capacity of this 
	 * @throws IllegalArgumentException initial capacity if i less than 0
	 */
	public MyArrayList(int i) {
		if (i > DEFAULT_CAPACITY) {
			this.data = new Object[i];
		}
		else if (i > 0) {
			this.data = new Object[DEFAULT_CAPACITY];
		}
		else if (i==0) {
			this.data = DEFAULT_DATA;
		}
		else {
			throw new IllegalArgumentException("Illegal initial capacity: " + i);
		}
	}
	
	/**
	 * 
	 * This method will return the size of MyArrayList
	 * 
	 * @return the size of MyArrayList
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * 
	 * This method will clear all the elements and reset size to 0
	 * 
	 */
	@Override
	public void clear() {
		this.data = DEFAULT_DATA;
		this.size = 0;
	}

	/**
	 * 
	 * This method will add an element to the specific position
	 * 
	 * precondition: The array list is not null and the size will be expand after adding elements
	 * 
	 * @throws IndexOutOfBoundsException if the position is not in the range of list size
	 * @throws NullPointerException if toAdd is null
	 * @return true if the element is added successfully
	 */
	@Override
	public boolean add(int index, E toAdd) throws IndexOutOfBoundsException, NullPointerException {
		checkIndexInRangeForAdd(index); 
		if (toAdd == null) throw new NullPointerException();
		
		// need a new array to contain added element
		Object[] newData = new Object[2 * size + 1];
		
		// add elements before index
		for (int i = 0; i < index; i++) {
			newData[i] = data[i];
		}
		
		// insert element to the index
		newData[index] = toAdd;
		
		// add reset elements from old data
		for (int i = index + 1; i < size + 1; i++) {
			newData[i] = data[i-1];
		}
		data = newData;
		size ++;
		return true;
	}

	/**
	 * 
	 * This method will add an element to the end of the list
	 * 
	 * precondition: the data array will be replaced if the length is too small
	 * 
	 * @return true if the procedure is done
	 * @throws NullPointerException if toAdd is null
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException{
		if (toAdd == null) throw new NullPointerException();
		
		// if the data length is not enough to contain an extra element, create a larger array
		if (size + 1 >= data.length) {
			Object[] newData = new Object[2 * size + 1];
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			newData[size] = toAdd;
			
			// replace the old data with new array
			data = newData;
			size ++;
			return true;
		}
		else {
			data[size] = toAdd;
			size ++;
			return true;
		}
	}
	
	/**
	 * This method will add a list of elements to MyArrayList
	 * 
	 * precondition: The data array will be replaced if the length is too small
	 * 
	 * @return true if the procedure is done
	 * @throws NullPointerException if toAdd is null
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException{
		if (toAdd == null) {
			throw new NullPointerException();
		}
		
		int s = toAdd.size();
		
		// if data is too small to contain toAdd, create a bigger array
		if (size + s >= data.length) {
			Object[] newData = new Object[(size+s) * 2 + 1];
			
			// add old data
			for (int i = 0; i < size; i ++) {
				newData[i] = data[i];
			}
			
			// add toAdd after old data
			for (int i = size; i < size + s; i++) {
				newData[i] = toAdd.get(i-size);
			}
			
			data = newData;
			size += s;
			return true;
		}
		
		// else, update data directly
		else {
			for (int i = size; i < size + s; i++) {
				data [i] = toAdd.get(i-size);
			}

			size += s;
			return true;
		}
	}
	
	/**
	 * This method will retrieve element at index
	 * 
	 * precondition: element should not null, empty or invalid 
	 * 
	 * @throws IndexOutOfBoundsException if index is out of range of size
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		return (E)data[index];
		
	}
	
	/**
	 * 
	 * This method will get the location of given element
	 * 
	 * precondition: This element should exist in the array list
	 * 
	 * @param e the element to be found
	 * @return idx the location of this element, -1 means not found
	 */
	public int get(E e) throws NullPointerException{
		
		if (e == null) {
			throw new NullPointerException();
		}
		else {
			for (int idx = 0; idx < size; idx ++) {
				if (e.equals(data[idx])) return idx;
			}
		}
		return -1;
	}
	
	/**
	 * 
	 * This method will remove an element at specific index
	 * 
	 * precondition: the element cannot be invalid
	 * 
	 * @throws IndexOutOfBoundsException if the index is out of boundary
	 * @return the removed element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		E output = (E) data[index];
		for (int i = index; i < size; i++) {
			data[i] = data[i+1];
		}
		size --;
		return output;
	}

	/**
	 * This method will remove an element if the list contains it
	 * 
	 * precondition: the element can be removed, if not, return null
	 * 
	 * @param toRemove the element to be removed
	 * @return element that has been remove. Null if nothing is removed 
	 * @throws NullPointerException if toRemove is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (toRemove == null) throw new NullPointerException();
		
		int idx = get(toRemove);
		
		E removed = null;
		
		// if idx < size, it means the element has been found
		if (idx != -1) {
			
			removed = (E) data[idx];
			for ( ;idx < size; idx ++) {
				data[idx] = data[idx + 1];
			}
			size --;
		}
		return removed;
	}

	/**
	 * 
	 * The method will change element of specific location of MyArrayList
	 * 
	 * precondition: index cannot be out of range, element cannot be null
	 * 
	 * @param index the location to be changed
	 * @param toChange the value to be set.
	 * @throws IndexOutOfBoundsException if index is out of range
	 * @throws NullPointerException if toChange is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E toChange) throws IndexOutOfBoundsException, NullPointerException {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		if (toChange == null) throw new NullPointerException();
		
		data[index] = toChange;
		return (E) data[index];
	}

	/**
	 * 
	 * This method will return 0 if it is true
	 * 
	 * @return true if size is 0
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * The method will Check if the list contains the element
	 * 
	 * precondition: element should exist in array list, the element which is going to find cannot be null
	 * 
	 * @param toFind the element to be checked
	 * @return true if the list contains this element
	 * @throws NullPointerException if toFind is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException{
		if (toFind == null) throw new NullPointerException();
		return get(toFind) != -1;
	}

	/**
	 * 
	 * This method will store all the elements to another Array
	 * 
	 * precondition: the array cannot be null
	 * 
	 * @param toHold Array to store elements 
	 * @return array that holds all the elements of MyArrayList
	 * @throws NullPointerException if toHold is null.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		// if toHold is too small to contain all the element, create a new array to contain them
		if (toHold.length < size) {
			Object[] arr = toArray();
			E[] output = (E[]) Array.newInstance(toHold.getClass().getComponentType(), size);
			for (int i = 0; i < size; i++) {
				output[i] = (E) arr[i];
			}
			return output;
		}
		
		System.arraycopy(data, 0, toHold, 0, size);
		
		return toHold;
	}
	
	/**
	 * 
	 * This method will get array form of MyArrayList
	 * 
	 * @return the size of MyArrayList
	 */
	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
		for (int i = 0; i < size; i++) {
			arr[i] = data[i];
		}
		return arr;
	}

	/**
	 * This method will get the iterator of MyArrayList
	 * 
	 * @return the iterator of MyArrayList
	 */
	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}
	
	/**
	 * 
	 * This method will be used to check if the index is out of bound
	 * 
	 * precondition: index cannot be out of range
	 * 
	 * @param index
	 * @throws IndexOutOfBoundsException if index is out of the range
	 */
	private void checkIndexInRangeForAdd(int index) throws IndexOutOfBoundsException{
		if (index < 0 || index > size) 
			throw new IndexOutOfBoundsException();
	}
	
	/**
	 * 
	 * This method is an inner class to implement iterator
	 *
	 */
	private class Itr implements Iterator<E> {
		int cursor;
		int lastReturn;
		
		Itr () {}
		
		/**
		 * This method will return true if the list had next element
		 * 
		 * @return true if the list has next element
		 */
		@Override
		public boolean hasNext() {
			return cursor < size;
		}
		
		/**
		 * 
		 * This method will show if the list has next element
		 * 
		 * precondition: the list cannot be empty
		 * 
		 * @return next element if the list has
		 * @throws NoSuchElementException if the list is empty
		 */
		@SuppressWarnings("unchecked")
		@Override
		public E next() throws NoSuchElementException {
			if (cursor >= size) {
				throw new NoSuchElementException();
			}
			else {
				lastReturn = cursor;
				cursor ++;
				return (E) data[lastReturn];
			}
		}
		
	}

}
