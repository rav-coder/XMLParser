package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.*;

import utilities.Iterator;
import utilities.MyDLL;

/**
 * Class Description: This is the test class for the MyDLL class which contains the implementation of a doubly-linked-list.
 *
 * @author Saurav Adhikari (831561)
 * 
 */
class MyDLLTests {
	private MyDLL<Double> dll;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		dll = new MyDLL<Double>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		dll = null;
	}

	/**
	 * Test method for {@link utilities.MyDLL#size()}.
	 */
	@Test
	void testSize() {
		assertEquals(0, dll.size());
		
		dll.add(1.1);
		dll.add(2.2);
		dll.add(3.3);
		
		assertEquals(3, dll.size());
		
		dll.remove(3.3);
		assertEquals(2, dll.size());
	}

	/**
	 * Test method for {@link utilities.MyDLL#clear()}.
	 */
	@Test
	void testClear() {
		dll.clear();
		assertEquals(0, dll.size());
		dll.add(1.1);
		dll.add(2.2);
		dll.clear();
		assertEquals(0, dll.size());
	}

	/**
	 * Test method for {@link utilities.MyDLL#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		try{
			dll.add(-1, 1.1);
		}
		catch(IndexOutOfBoundsException ex) {
			assertTrue(true);
		}
		
		dll.add(1.1);
		assertTrue(dll.add(1, 2.2));
		
		try{
			dll.add(2, null);
		}
		catch(NullPointerException ex) {
			assertTrue(true);
		}
		
		try{
			assertTrue(dll.add(3, 2.2));
		}
		catch(IndexOutOfBoundsException ex) {
			assertTrue(true);
		}
		
	}

	/**
	 * Test method for {@link utilities.MyDLL#add(java.lang.Object)}.
	 */
	@Test
	void testAddE() {

		assertEquals(0, dll.size());
		
		dll.add(1.1);
		assertEquals(1, dll.size());

		try{
			dll.add(null);
		}
		catch(NullPointerException ex) {
			assertTrue(true);
		}
		assertEquals(1, dll.size());
		
		dll.add(3.3);
		assertEquals(2, dll.size());
		
	}

	/**
	 * Test method for {@link utilities.MyDLL#addAll(utilities.ListADT)}.
	 */
	@Test
	void testAddAll() {
		dll.add(1.1);
		dll.add(2.2);
		
		MyDLL<Double> dll2 = new MyDLL<Double>();
		dll2.add(3.3);
		dll2.add(4.4);

		assertTrue(dll.addAll(dll2));
		assertEquals(4, dll.size());
		assertTrue(dll.get(3) == 4.4);
		
	}

	/**
	 * Test method for {@link utilities.MyDLL#get(int)}.
	 */
	@Test
	void testGet() {
		dll.add(1.1);
		
		try{
			dll.get(-1);
		}
		catch(IndexOutOfBoundsException ex) {
			assertTrue(true);
		}
		
		dll.add(2.2);
		assertEquals(Double.valueOf(1.1), dll.get(0));
		
		assertEquals(Double.valueOf(2.2), dll.get(1));
		
		try{
			dll.get(2);
		}
		catch(IndexOutOfBoundsException ex) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link utilities.MyDLL#remove(int)}.
	 */
	@Test
	void testRemoveInt() {
		dll.add(1.1);
		
		try{
			dll.get(-1);
		}
		catch(IndexOutOfBoundsException ex) {
			assertTrue(true);
		}
		
		dll.add(2.2);
		dll.add(3.3);
		dll.add(4.4);
		assertEquals(Double.valueOf(3.3), dll.remove(2));
		assertEquals(3, dll.size());
		assertEquals(Double.valueOf(4.4), dll.remove(2));
		
		try{
			dll.get(2);
		}
		catch(IndexOutOfBoundsException ex) {
			assertTrue(true);
		}
		
	}

	/**
	 * Test method for {@link utilities.MyDLL#remove(java.lang.Object)}.
	 */
	@Test
	void testRemoveE() {
		dll.add(1.1);
		
		try{
			dll.remove(null);
		}
		catch(NullPointerException ex) {
			assertTrue(true);
		}
		
		dll.add(2.2);
		dll.add(3.3);
		dll.add(4.4);
		dll.add(2.2);
		assertEquals(Double.valueOf(3.3), dll.remove(3.3));
		assertEquals(4, dll.size());
		assertEquals(Double.valueOf(2.2), dll.remove(2.2));
		assertEquals(Double.valueOf(2.2), dll.get(2));

	}

	/**
	 * Test method for {@link utilities.MyDLL#set(int, java.lang.Object)}.
	 */
	@Test
	void testSet() {
		dll.add(2.2);
		dll.add(3.3);
		
		dll.set(0, 1.1);
		assertEquals(Double.valueOf(1.1), dll.get(0));
		
		try{
			dll.set(0, null);
		}
		catch(NullPointerException ex) {
			assertTrue(true);
		}
		
		try{
			dll.set(-1, 1.1);
		}
		catch(IndexOutOfBoundsException ex) {
			assertTrue(true);
		}
		
		try{
			dll.set(3, 1.1);
		}
		catch(IndexOutOfBoundsException ex) {
			assertTrue(true);
		}
		
		
	}

	/**
	 * Test method for {@link utilities.MyDLL#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		dll.add(1.1);
		assertFalse(dll.isEmpty());
		dll.remove(0);
		assertTrue(dll.isEmpty());
	}

	/**
	 * Test method for {@link utilities.MyDLL#contains(java.lang.Object)}.
	 */
	@Test
	void testContains() {
		dll.add(2.2);
		dll.add(3.3);
		dll.add(4.4);
		
		try{
			dll.contains(null);
		}
		catch(NullPointerException ex) {
			assertTrue(true);
		}
		
		assertTrue(dll.contains(4.4));
		assertTrue(dll.contains(3.3));
		assertFalse(dll.contains(2.4));
		
	}

	/**
	 * Test method for {@link utilities.MyDLL#toArray(E[])}.
	 */
	@Test
	void testToArrayEArray() {
		dll.add(2.2);
		dll.add(3.3);
		dll.add(4.4);
		
		Double [] arrayTest = {2.2, 3.3, 4.4};
		
		Double [] array = dll.toArray(new Double [3]);
		assertEquals(arrayTest[0], array[0]);
		assertEquals(arrayTest[2], array[2]);
		
        array = dll.toArray(new Double [2]);
		assertEquals(arrayTest[0], array[0]);
		assertEquals(arrayTest[2], array[2]);
	}

	/**
	 * Test method for {@link utilities.MyDLL#toArray()}.
	 */
	@Test
	void testToArray() {
		dll.add(2.2);
		dll.add(3.3);
		dll.add(4.4);
		
		Double [] arrayTest = {2.2, 3.3, 4.4};
		
		Object [] objectArray = dll.toArray();
	    Double[] array = new Double[objectArray.length];
		 
	    for (int i = 0; i < objectArray.length; i++) {
	    	array[i] = (Double)objectArray[i];
	    }
		
		assertEquals(arrayTest[0], array[0]);
		assertEquals(arrayTest[2], array[2]);
	}

	/**
	 * Test method for {@link utilities.MyDLL#iterator()}.
	 */
	@Test
	void testIterator() {
		Iterator<Double> iterator = dll.iterator();
		assertFalse(iterator.hasNext());
		
		try{
			iterator.next();
		}
		catch(NoSuchElementException ex) {
			assertTrue(true);
		}
		
		dll.add(1.1);
		iterator = dll.iterator();
		
		assertTrue(iterator.hasNext());
		assertEquals(Double.valueOf(1.1), iterator.next());
		assertFalse(iterator.hasNext());
	}

}
