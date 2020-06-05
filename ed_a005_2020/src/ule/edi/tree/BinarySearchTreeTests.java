package ule.edi.tree;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;





public class BinarySearchTreeTests {

   
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  15
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  30
	* |  |  |  ∅
	* |  |  |  ∅
    */	
	private BinarySearchTreeImpl<Integer> ejemplo = null;
	
	
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  15
	* |  |  |  12
	* |  |  |  |  ∅
	* |  |  |  |  ∅
	* |  |  ∅
  */
	private BinarySearchTreeImpl<Integer> other=null;
	
	@Before
	public void setupBSTs() {

		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 20, 5, 2, 15, 30);
		Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");

		other = new BinarySearchTreeImpl<Integer>();
		other.insert(10, 20, 5, 2, 15, 12);
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");

	}
	
	@Test
	public void testContains() {
		Assert.assertEquals(ejemplo.contains(10), true);
		Assert.assertEquals(ejemplo.contains(30), true);
		Assert.assertEquals(ejemplo.contains(40), false);
	}
	
	@Test
	public void testInsertDuplicado() {
		Assert.assertEquals(ejemplo.insert(10), false);
	}
	
	@Test
	public void testIterator() {
		Iterator<Integer> iter = ejemplo.iteratorWidth();
	}
	
	@Test
	public void testInsertCollections() {
		LinkedList<Integer> lista = new LinkedList<Integer>();
		lista.add(26);
		Assert.assertEquals(1, other.insert(lista));
		lista.add(10);
		Assert.assertEquals(0, other.insert(lista));
		LinkedList<Integer> prueba = new LinkedList<Integer>();
		prueba.add(10);
		prueba.add(20);
		Assert.assertEquals(0, ejemplo.insert(prueba));
	}

	@Test
	public void testRemoveHoja() {
		ejemplo.remove(30);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, ∅}}",ejemplo.toString());
	}
	
	@Test
	public void testRemove1Hijo() {
		ejemplo.remove(5);
		Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
		
		//Eliminar raiz
		BinarySearchTreeImpl<Integer> arbol2 = new BinarySearchTreeImpl<Integer>();
		arbol2.insert(2,4,6,9);
		arbol2.remove(2);
		Assert.assertEquals("{4, ∅, {6, ∅, {9, ∅, ∅}}}",arbol2.toString());
		
		arbol2.remove(9);
		Assert.assertEquals("{4, ∅, {6, ∅, ∅}}",arbol2.toString());
		
	}
	
	@Test
	public void testRemove2Hijos() {
		ejemplo.remove(10);
		Assert.assertEquals("{15, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}",ejemplo.toString());
	}
	
	@Test
	public void testTagDecendentsEjemplo() {
		ejemplo.tagDecendents();
		ejemplo.filterTags("decendents");
		Assert.assertEquals(
				"{10 [(decendents, 5)], {5 [(decendents, 1)], {2 [(decendents, 0)], ∅, ∅}, ∅}, {20 [(decendents, 2)], {15 [(decendents, 0)], ∅, ∅}, {30 [(decendents, 0)], ∅, ∅}}}",
				ejemplo.toString());
	}

	@Test
	public void testTagHeightEjemplo() {
		other.tagHeight();
		other.filterTags("height");
		Assert.assertEquals(
				"{10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], ∅, ∅}, ∅}, {20 [(height, 2)], {15 [(height, 3)], {12 [(height, 4)], ∅, ∅}, ∅}, ∅}}",
				other.toString());
	}

	@Test
	public void testTagOnlySonEjemplo() {
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");
		Assert.assertEquals(3, other.tagOnlySonInorder());
		other.filterTags("onlySon");
		Assert.assertEquals(
				"{10, {5, {2 [(onlySon, 1)], ∅, ∅}, ∅}, {20, {15 [(onlySon, 3)], {12 [(onlySon, 2)], ∅, ∅}, ∅}, ∅}}",
				other.toString());
		
		BinarySearchTreeImpl<Integer> arbol = new BinarySearchTreeImpl<Integer>();
		Assert.assertEquals(0, arbol.tagOnlySonInorder());
		
		BinarySearchTreeImpl<Integer> arbol2 = new BinarySearchTreeImpl<Integer>();
		arbol2.insert(2,4,6,9);
		Assert.assertEquals(3, arbol2.tagOnlySonInorder());

	}
	
	@Test 
	public void testInsertIf() {
		ejemplo.insert(7,2);
	}
	
	@Test 
	public void testRemoveFor() {
		BinarySearchTreeImpl<Integer> arbol2 = new BinarySearchTreeImpl<Integer>();
		arbol2.insert(2,4,6,7,8,9);
		arbol2.remove(6,9);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testContainsIllegal() {
		ejemplo.contains(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInsertIllegal() {
		ejemplo.insert((Integer) null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveIllegal() {
		ejemplo.remove(null,10);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveNoSuch() {
		ejemplo.remove(10,40);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemove2Illegal() {
		ejemplo.remove((Integer)null);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemove2NoSuch() {
		ejemplo.remove(40);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInsert2Illegal() {
		ejemplo.insert(null,10);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInsert3Illegal() {
		LinkedList<Integer> aux = new LinkedList<Integer>();
        aux.add(null);
        Assert.assertEquals(0, other.insert(aux));
	}
	
	

}

