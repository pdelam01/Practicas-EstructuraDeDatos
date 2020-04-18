package ule.edi.queuewithrep;


import static org.junit.Assert.*;

import java.awt.event.ItemEvent;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.queuewithrep.ArrayQueueWithRepImpl.ElemQueueWithRep;

public abstract class AbstractQueueWithRefTests {

	protected abstract <T> QueueWithRep<T> createQueueWithRep();
	

	private QueueWithRep<String> S1;

	private QueueWithRep<String> S2;
	
	@Before
	public void setupQueueWithReps() {

		this.S1 = createQueueWithRep();
		
		this.S2 = createQueueWithRep();
		
		S2.add("ABC", 5);
		S2.add("123", 5);
		S2.add("XYZ", 10);
	}

	@Test
	public void testConstructionIsEmpty() {
		assertTrue(S1.isEmpty());
		assertFalse(S2.isEmpty());
	}
	
	@Test
	//Las nuevas instancias del TAD tienen tamaño cero: 
	public void testConstructionCardinality() {
		assertEquals(S1.size(), 0);
	}

	@Test
	public void testToStringInEmpty() {
		assertTrue(S1.isEmpty());
		assertEquals(S1.toString(), "()");
	}
	
	@Test
	public void testToString1elem() {
		assertTrue(S1.isEmpty());
		S1.add("A",3);
		assertEquals(S1.toString(), "(A A A )");
	}
	
	@Test
	//Añadir elementos con una multiplicidad incrementa su contador y el tamaño de la cola: ")
	public void testAddWithCount() {
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 5);
		assertEquals(S1.size(), 5);
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 10);    //ALERTA!	
		assertEquals(S1.size(), 10);
		S1.add("123", 5);	
		assertEquals(S1.count("123"), 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 15);
	}
	
	@Test
	public void testSize() {
		QueueWithRep<String> qw = createQueueWithRep();
		qw.add("a");
		qw.add("b");
		qw.add("c");
		assertEquals(qw.size(), 3);
	}
	
	@Test
	public void testCount() {
		QueueWithRep<String> qw = createQueueWithRep();
		qw.add("a",5);
		assertEquals(qw.count("a"), 5);
		qw.add("b",5);
		assertEquals(qw.count("b"), 5);
	}
	
	
	@Test
	//Se pueden eliminar cero instancias de un elemento con remove(x, 0): ")
	public void testRemoveZeroInstances() {
		S1.add("ABC", 5);
		S1.remove("ABC", 0);
		assertEquals(S1.size(), 5);
	}
	
	
	// TODO AÑADIR MAS TESTS
	@Test
	public void testClear() {
		QueueWithRep<String> qw = createQueueWithRep();
		qw.add("ABC",5);
		qw.add("C",5);
		qw.clear();
		assertEquals(qw.isEmpty(),true);
	}
	
	@Test
	public void testEmpty() {
		QueueWithRep<String> qw = createQueueWithRep();
		qw.add("ABC",5);
		assertFalse(qw.isEmpty());
	}
	
	@Test
	public void testRemove(){
		S1.add("ABC",5);
		S1.remove("ABC",2);
		assertEquals(S1.size(),3);
		S1.clear();
		assertEquals(S1.size(),0);
	}
	
	@Test
	public void testRemoveSinElem() throws EmptyCollectionException {		
		S1.add("ABC",5);
		S1.add("123",7);
		S1.add("456",5);
		S1.remove();
		assertEquals(S1.size(), 12);
	}
	
	@Test
	public void testRemoveSinElem2() throws EmptyCollectionException {		
		S1.add("ABC",5);
		S1.remove();
		assertEquals(S1.size(), 0);
	}
	
	@Test
	public void addNada() {
		S1.add("ABC",5);
		S1.add("123",7);
		S1.add("456",5);
		S1.add("ABC",5);
		S1.remove("456",1);
	}
	
	@Test
	public void testAux() {
		S1.add("123");
		S1.add("123");
		assertEquals(S1.count("123"), 1);
	}
	
	@Test 
	public void testDosMetodosAdd() {
		S1.add("4");
		S1.add("4",2);
		assertEquals(S1.count("4"), 3);
	}
	
	@Test
	public void testContains() {
		S1.add("ABC",5);
		S1.add("123",5);
		assertTrue(S1.contains("123"));
		assertFalse(S1.contains("565"));
	}
	
	@Test (expected = NullPointerException.class)
	public void testRemove1() throws NullPointerException, EmptyCollectionException{
		QueueWithRep<String> q = createQueueWithRep();
		q.add(null,5);
		q.remove(null,5);
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNull() throws NullPointerException, EmptyCollectionException{
		QueueWithRep<String> q = createQueueWithRep();
		q.add(null,5);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testAddIllegal(){
		QueueWithRep<String> q = createQueueWithRep();
		q.add("123",-2);
	}
	
	@Test (expected = NullPointerException.class)
	public void testRemoveNull() {
		QueueWithRep<String> q = createQueueWithRep();
		q.remove(null, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveIllegal() {
		QueueWithRep<String> q = createQueueWithRep();
		q.add("123", 5);
		q.remove("123", 7);
		q.remove("123", -1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveIllegal2() {
		QueueWithRep<String> q = createQueueWithRep();
		q.add("123", 5);
		q.remove("123", -1);
	}
	
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveNoSuch(){
		QueueWithRep<String> q = createQueueWithRep();
		q.add("123", 5);
		q.remove("abc",5);
	}
	
	@Test (expected = EmptyCollectionException.class)
	public void testRemoveEmpty() throws EmptyCollectionException {
		QueueWithRep<String> q = createQueueWithRep();
		q.remove();
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddElemNull() {
		QueueWithRep<String> q = createQueueWithRep();
		q.add(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testContainsNull() {
		QueueWithRep<String> q = createQueueWithRep();
		q.contains(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testCountNull() {
		QueueWithRep<String> q = createQueueWithRep();
		q.count(null);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testExceptionNext() {
		QueueWithRep<String> q = createQueueWithRep();
		q.add("123", 2);
		Iterator<String> iter = q.iterator();
		iter.next();
		iter.next();
		iter.next();
	}
	
	
	
	

}
