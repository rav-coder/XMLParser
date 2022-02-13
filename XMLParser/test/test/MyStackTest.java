package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import java.lang.Exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.Iterator;
import utilities.MyStack;

/**
 * 
 * @author Nevyn D'Souza
 *
 */
class MyStackTest {
	MyStack<String> stack1;
	MyStack<String> stack2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		stack1 = new MyStack<>();
		stack2 = new MyStack<>();
	}

	/**
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		stack1 = null;
		stack2 = null;
	}

	/**
	 * Test method for {@link utilities.MyStack#push(java.lang.Object)}.
	 */
	@Test
	void testPush() {
		stack1.push("n");
		stack1.push("d");
		assertEquals(2, stack1.size());

		try {
			stack1.push(null);
			fail("should throw NullPointerException");
		} catch (NullPointerException e) {

		}
	}

	/**
	 * Test method for {@link utilities.MyStack#pop()}.
	 */
	@Test
	void testPop() {
		stack1.push("n");
		stack1.push("d");

		assertEquals("d", stack1.pop());

		assertEquals("n", stack1.pop());

		try {
			stack1.pop();
			fail("Should throw EmptyStackException");
		} catch (EmptyStackException e) {

		}
	}

	/**
	 * Test method for {@link utilities.MyStack#peek()}.
	 */
	@Test
	void testPeek() {

		try {
			stack1.peek();
			fail("Should throw EmptyStackException");
		} catch (EmptyStackException e) {

		}

		stack1.push("n");

		assertEquals("n", stack1.peek());

		stack1.push("d");

		assertEquals("d", stack1.peek());
	}

	/**
	 * Test method for {@link utilities.MyStack#clear()}.
	 */
	@Test
	void testClear() {
		stack1.push("n");
		stack1.push("d");
		assertEquals(2, stack1.size());

		stack1.clear();
		assertEquals(0, stack1.size());

		try {
			stack1.peek();
			fail("Should throw EmptyStackException");
		} catch (EmptyStackException e) {

		}
	}

	/**
	 * Test method for {@link utilities.MyStack#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		assertTrue(stack1.isEmpty());

		stack1.push("n");
		assertFalse(stack1.isEmpty());

		assertEquals("n", stack1.pop());

		assertTrue(stack1.isEmpty());
	}

	/**
	 * Test method for {@link utilities.MyStack#toArray()}.
	 */
	@Test
	void testToArray() {

		stack1.push("n");
		stack1.push("d");
		Object[] output = stack1.toArray();
		String[] expected = { "n", "d" };

		assertEquals(Arrays.toString(output), Arrays.toString(expected));
	}

	/**
	 * Test method for {@link utilities.MyStack#toArray(E[])}.
	 */
	@Test
	void testToArrayEArray() {

		stack1.push("n");
		stack1.push("d");

		String[] arr = new String[] { "1" };
		String[] output = stack1.toArray(arr);
		String[] expected = new String[] { "n", "d" };

		assertEquals(Arrays.toString(output), Arrays.toString(expected));

		arr = new String[] { "1", "2", "3" };
		output = stack1.toArray(arr);
		expected = new String[] { "n", "d", "3" };

		assertEquals(Arrays.toString(output), Arrays.toString(expected));
	}

	/**
	 * Test method for {@link utilities.MyStack#contains(java.lang.Object)}.
	 */
	@Test
	void testContains() {
		stack1.push("n");
		stack1.push("d");

		assertTrue(stack1.contains("n"));
		assertTrue(stack1.contains("d"));
		assertFalse(stack1.contains("j"));

		try {
			stack1.contains(null);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {

		}

	}

	/**
	 * Test method for {@link utilities.MyStack#search(java.lang.Object)}.
	 */
	@Test
	void testSearch() {

		stack1.push("3");
		stack1.push("2");
		stack1.push("1");
		assertEquals(1, stack1.search("1"));
		assertEquals(2, stack1.search("2"));
		assertEquals(3, stack1.search("3"));

		assertEquals(-1, stack1.search("4"));
	}

	/**
	 * Test method for {@link utilities.MyStack#iterator()}.
	 */
	@Test
	void testIterator() {
		stack1.push("n");
		stack1.push("d");
		stack1.push("j");

		Iterator<String> it = stack1.iterator();
		assertTrue(it.hasNext());

		assertEquals("n", it.next());

		assertEquals("d", it.next());

		assertEquals("j", it.next());

		assertFalse(it.hasNext());

		try {
			it.next();
			fail("should throw NoSuchElementException");
		} catch (NoSuchElementException e) {
			// Succeed
		}
	}

	/**
	 * Test method for {@link utilities.MyStack#equals(utilities.StackADT)}.
	 */
	@Test
	void testEqualsStackADTOfE() {
		stack1.push("n");
		stack1.push("d");
		stack2.push("n");

		assertFalse(stack1.equals(stack2));
		stack2.push("d");
		assertTrue(stack1.equals(stack1));
		stack2 = null;
		assertFalse(stack1.equals(stack2));
	}

	/**
	 * Test method for {@link utilities.MyStack#size()}.
	 */
	@Test
	void testSize() {
		stack1.push("n");
		assertEquals(1, stack1.size());

		stack1.push("d");
		assertEquals(2, stack1.size());

		stack1.clear();
		assertEquals(0, stack1.size());
	}

}
