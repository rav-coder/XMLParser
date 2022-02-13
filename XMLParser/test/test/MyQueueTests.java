/**
 * 
 */
package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import utilities.Iterator;
import utilities.MyQueue;
import Exceptions.EmptyQueueException;
import java.util.NoSuchElementException;



/**
 * @author YunZe (David) Wei
 *
 */
public class MyQueueTests {

	// used for every test
	MyQueue<Integer> q1;

	// used to test testEqualsQueueADTOfE with q1
	MyQueue<Integer> q2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		q1 = new MyQueue<>();
		q2 = new MyQueue<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}

	/**
	 * Test method for {@link utilities.MyQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	public void testEnqueue() {
		// testing for null pointer exception
		try {
			q1.enqueue(null);
		} catch (NullPointerException e) {
			assertTrue(true);
			// null pointer exception thrown
		}

		// testing adding individual values to queue
		q1.enqueue(10);
		assertEquals(1, q1.size());
		q1.enqueue(20);
		assertEquals(2, q1.size());
		q1.enqueue(30);
		assertEquals(3, q1.size());
		q1.enqueue(40);
		assertEquals(4, q1.size());
	}

	/**
	 * Test method for {@link utilities.MyQueue#dequeue()}.
	 */
	@Test
	public void testDequeue() {
		try {
			q1.dequeue();
		} catch (EmptyQueueException e) {
			assertTrue(true);
			// empty queue exception thrown
		}

		// add values first so we can dequeue them to test Dequeue
		q1.enqueue(10);
		assertEquals(1, q1.size());
		q1.enqueue(20);
		assertEquals(2, q1.size());
		q1.enqueue(30);
		assertEquals(3, q1.size());
		q1.enqueue(40);
		assertEquals(4, q1.size());

		// test dequeue
		try {
			q1.dequeue();
			assertEquals(3, q1.size());
			q1.dequeue();
			assertEquals(2, q1.size());
		} catch (EmptyQueueException e) {
			assertTrue(false);
			// empty queue exception should not be thrown
		}
	}

	/**
	 * Test method for {@link utilities.MyQueue#peek()}.
	 */
	@Test
	public void testPeek() {
		try {
			q1.peek();
		} catch (EmptyQueueException e) {
			assertTrue(true);
			// empty queue exception thrown
		}

		// add values first so we can dequeue them to test Dequeue
		q1.enqueue(10);
		assertEquals(1, q1.size());
		q1.enqueue(20);
		assertEquals(2, q1.size());
		q1.enqueue(30);
		assertEquals(3, q1.size());

		// test peeking
		try {
			assertTrue(q1.peek() == 10);
			q1.dequeue();
			assertTrue(q1.peek() == 20);
		} catch (EmptyQueueException e) {
			assertTrue(false);
			// empty queue exception should not be thrown
		}
	}

	/**
	 * Test method for {@link utilities.MyQueue#dequeueAll()}.
	 */
	@Test
	public void testDequeueAll() {
		// add values first so we can test DequeueAll
		q1.enqueue(10);
		assertEquals(1, q1.size());
		q1.enqueue(20);
		assertEquals(2, q1.size());
		q1.enqueue(30);
		assertEquals(3, q1.size());

		// test DequeueAll
		q1.dequeueAll();
		assertTrue(q1.size() == 0);
	}

	/**
	 * Test method for {@link utilities.MyQueue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(q1.size() == 0);

		// add values to test again, should return false
		q1.enqueue(10);
		assertFalse(q1.size() == 0);
	}

	/**
	 * Test method for {@link utilities.MyQueue#iterator()}.
	 */
	@Test
	public void testIterator() {

		// add values first so we can test the iterator again
		q1.enqueue(10);
		q1.enqueue(20);

		Iterator<Integer> q1iterator = q1.iterator();

		assertTrue(q1iterator.hasNext());

		assertTrue(q1iterator.next() == 10);
		assertTrue(q1iterator.next() == 20);
		
		// check that the error is thrown when there is no more elements to iterate
		try {
			q1iterator.next();
		}catch (NoSuchElementException e) {
			assertTrue(true);
		}

	}

	/**
	 * Test method for {@link utilities.MyQueue#equals(utilities.QueueADT)}.
	 */
	@Test
	public void testEqualsQueueADTOfE() {
		q1.enqueue(10);
		q1.enqueue(20);

		q2.enqueue(10);

		// should return false the queues are not equal right now
		assertFalse(q1.equals(q2));

		q2.enqueue(20);

		// should return true the queues are equal now
		assertTrue(q1.equals(q2));

		q1.enqueue(30);

		// should return false the queues are not equal right now
		assertFalse(q1.equals(q2));

	}

	/**
	 * Test method for {@link utilities.MyQueue#toArray()}.
	 */
	@Test
	public void testToArray() {
		// add values first so we can test
		q1.enqueue(10);
		q1.enqueue(20);

		Object[] test = q1.toArray();
		Object[] expectedResult = new Integer[] { 10, 20 };

		assertEquals(Arrays.toString(test), Arrays.toString(expectedResult));
	}

	/**
	 * Test method for {@link utilities.MyQueue#toArray(E[])}.
	 */
	@Test
	public void testToArrayEArray() {
		Integer[] holder = new Integer[1];
		Integer[] expectedResult = new Integer[] { 10, 20 };

		// add values first so we can test
		q1.enqueue(10);
		q1.enqueue(20);
		
		Integer[] test = q1.toArray(holder);
		
		assertEquals(Arrays.toString(test), Arrays.toString(expectedResult));
		
		holder = null;
		
		try {
			 q1.toArray(holder);
		}catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link utilities.MyQueue#isFull()}.
	 */
	@Test
	public void testIsFull() {
		q1 = new MyQueue<>(2);

		// queue is full after adding
		q1.enqueue(10);
		q1.enqueue(20);

		// test
		assertTrue(q1.isFull());

		try {
			q1.dequeue();
		} catch (EmptyQueueException e) {
			assertTrue(false);
			// empty queue exception should not be thrown
		}

		assertFalse(q1.isFull());
	}

	/**
	 * Test method for {@link utilities.MyQueue#size()}.
	 */
	@Test
	public void testSize() {
		assertTrue(q1.size() == 0);

		q1.enqueue(10);
		assertTrue(q1.size() == 1);
		q1.enqueue(20);
		assertTrue(q1.size() == 2);
		q1.enqueue(30);
		assertTrue(q1.size() == 3);
	}

}
