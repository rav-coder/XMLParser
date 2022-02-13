/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.MyArrayList;
import utilities.Iterator;

/**
 *
 */
class MyArrayListTest {
	MyArrayList<String> list1;
	MyArrayList<String> list2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		list1 = new MyArrayList<>();
		list2 = new MyArrayList<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		list1 = null;
		list2 = null;
	}

	/**
	 * Test method for {@link utilities.MyArrayList#size()}.
	 */
	@Test
	void testSize() {
		assertEquals(0, list1.size());
		
		list1.add("a");
		list1.add("b");
		
		assertEquals(2, list1.size());
	}

	/**
	 * Test method for {@link utilities.MyArrayList#clear()}.
	 */
	@Test
	void testClear() {
		list1.add("a");
		list1.add("b");
		
		assertEquals(2, list1.size());
		
		list1.clear();
		
		assertEquals(0, list1.size());
	}

	/**
	 * Test method for {@link utilities.MyArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		assertTrue(list1.add("a"));
		assertTrue(list1.add("b"));
		assertTrue(list1.add(1, "c"));
		assertEquals("c", list1.get(1));
		assertEquals(3, list1.size());
		
		assertTrue(list1.add(2, "d"));
		
		// insert to position out of bounds
		try {
			list1.add(-1, "d");
			fail("Should throw IndexOutOfBoundsException");
		}
		catch (IndexOutOfBoundsException e) {
			// Succeed
		}
		
		try {
			list1.add(5, "d");
			fail("Should throw IndexOutOfBoundsException");
		}
		catch (IndexOutOfBoundsException e) {
			// Succeed
		}
		
		try {
			list1.add(0,null);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			// Succeed
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	void testAddE() {
		assertTrue(list1.add("a"));
		assertTrue(list1.add("b"));
		assertEquals(2, list1.size());
		
		try {
			list1.add(null);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			// Succeed
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#addAll(adts.ListADT)}.
	 */
	@Test
	void testAddAll() {
		list1.add("a");
		list1.add("b");
		
		list2.add("c");
		list2.add("d");
		
		assertTrue(list1.addAll(list2));
		
		assertEquals(4, list1.size());
		
		assertEquals("c", list1.get(2));
		assertEquals("d", list1.get(3));
		
		list2 = null;
		
		try {
			list1.addAll(list2);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			// Succeed
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#get(int)}.
	 */
	@Test
	void testGetInt() {
		list1.add("a");
		list1.add("b");
		
		assertEquals("b", list1.get(1));
		
		// get element out of bounds
		try {
			list1.get(2);
			fail("Should throw IndexOutOfBoundsException");
		}
		catch (IndexOutOfBoundsException e) {
			// Succeed
		}
		
		try {
			list1.get(-1);
			fail("Should throw IndexOutOfBoundsException");
		}
		catch (IndexOutOfBoundsException e) {
			// Succeed
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#get(java.lang.Object)}.
	 */
	@Test
	void testGetE() {
		list1.add("a");
		list1.add("b");
		assertEquals(1, list1.get("b"));
		
		try {
			list1.get(null);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			// Succeed
		}
		
	}

	/**
	 * Test method for {@link utilities.MyArrayList#remove(int)}.
	 */
	@Test
	void testRemoveInt() {
		for (int i = 0; i < 20; i++) {
			list1.add(i + "");
		}
		assertEquals("11", list1.remove(11));
		assertEquals(19, list1.size());
		
		try {
			list1.remove(-1);
			fail("Should throw IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Succeed;
		}
		
		try {
			list1.remove(19);
			fail("Should throw IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Succeed
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveE() {
		for (int i = 0; i < 20; i++) {
			list1.add(i + "");
		}
		
		assertEquals("12", list1.remove("12"));
		assertEquals(19, list1.size());
		
		// remove a non-existing element
		assertEquals(null, list1.remove("300"));
		assertEquals(19, list1.size());
		
		// Test removing null value
		try {
			list1.remove(null);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			// Succeed
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#set(int, java.lang.Object)}.
	 */
	@Test
	void testSet() {
		list1.add("a");
		list1.add("b");
		list1.set(0, "c");
		
		assertEquals("c", list1.get(0));
		// set null 
		try {
			list1.set(1, null);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			// Succeed
		}
		
		// set index that out of boundary
		try {
			list1.set(-1, "d");
			fail("Should throw IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Succeed
		}
		
		try {
			list1.set(2, "d");
			fail("Should throw IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Succeed
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		assertTrue(list1.isEmpty());
		list1.add("ab");
		assertFalse(list1.isEmpty());
		
	}

	/**
	 * Test method for {@link utilities.MyArrayList#contains(java.lang.Object)}.
	 */
	@Test
	void testContains() {
		list1.add("a");
		assertTrue(list1.contains("a"));
		
		assertFalse(list1.contains("c"));
		
		try {
			list1.contains(null);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			// Succeed
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#toArray(E[])}.
	 */
	@Test
	void testToArrayEArray() {
		list1.add("a");
		list1.add("b");
		// add to smaller array
		String[] s = new String[] {"c"};
		String[] output = list1.toArray(s);
		String[] expected = new String[] {"a", "b"};
		
		assertEquals(Arrays.toString(expected), Arrays.toString(output));
		
		// add to equal-size array
		s = new String[] {"c", "d"};
		output = list1.toArray(s);
		expected = new String[] {"a", "b"};
		
		assertEquals(Arrays.toString(expected), Arrays.toString(output));
		
		// add to larger array
		s = new String[] {"c", "d", "e", "f"};
		output = list1.toArray(s);
		expected = new String[] {"a", "b", "e", "f"};
		
		assertEquals(Arrays.toString(expected), Arrays.toString(output));
		
		// add to null
		s = null;
		
		try {
			output = list1.toArray(s);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			// Succeed
		}
	}

	/**
	 * Test method for {@link utilities.MyArrayList#toArray()}.
	 */
	@Test
	void testToArray() {
		list1.add("a");
		list1.add("b");
		Object[] s =  list1.toArray();
		String[] expected = new String[] {"a", "b"};
		
		String ss = Arrays.toString(s);
		String expectedss = Arrays.toString(expected);
		
		assertEquals(expectedss, ss);
		
	}

	/**
	 * Test method for {@link utilities.MyArrayList#iterator()}.
	 */
	@Test
	void testIterator() {
		list1.add("a");
		list1.add("b");
		
		@SuppressWarnings("unchecked")
		Iterator<String> it = (Iterator<String>) list1.iterator();
		assertTrue (it.hasNext());
				
		assertEquals("a", it.next());
		
		assertEquals("b", it.next());
		
		assertFalse(it.hasNext());
		
		try {
			it.next();
			fail ("should throw NoSuchElementException");
		} catch (NoSuchElementException e) {
			// succeed
		}
	}

}
