package ule.edi.doubleLinkedList;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedImplTest {
	DoubleLinkedListImpl<String> lv;
	DoubleLinkedListImpl<String> listaConElems;
	
@Before
public void antesDe() {
	lv=new DoubleLinkedListImpl<String>();
	listaConElems=new DoubleLinkedListImpl<String>();
	listaConElems.insertFirst("D");
	listaConElems.insertFirst("B");
	listaConElems.insertFirst("A");
	listaConElems.insertFirst("C");
	listaConElems.insertFirst("B");
	listaConElems.insertFirst("A");
	
}

	@Test
	public void anyadir() {
		lv=new DoubleLinkedListImpl<String>();
		listaConElems.insertFirst("1");
		listaConElems.insertFirst("2");
		listaConElems.insertFirst("3");
	}
	
	@Test
	public void anyadir2() {
		lv.insertPos("B", 1);
		Assert.assertEquals(lv.size(), 1);
		lv.insertPos("A", 2);
		lv.insertPos("C", 1);
		lv.insertPos("T", 1);
		lv.insertPos("r", 2);
		Assert.assertEquals(lv.toString(), "(T r C B A )");
	}
	
	@Test
	public void removeFirst() throws EmptyCollectionException {
		lv.insertFirst("1"); //4º pos
		lv.insertFirst("2");
		lv.insertFirst("3");
		lv.insertFirst("4"); //1º pos
		Assert.assertEquals(lv.removeFirst(), "4");
		Assert.assertEquals(lv.toString(), "(3 2 1 )");
		
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista=new DoubleLinkedListImpl<String>();
		lista.insertFirst("1");
		Assert.assertEquals(lista.removeFirst(), "1");
		Assert.assertEquals(lista.toString(), "()");
	}
	
	@Test
	public void removeLast() throws EmptyCollectionException {
		lv.insertFirst("1"); //4º pos
		lv.insertFirst("2");
		lv.insertFirst("3");
		lv.insertFirst("4"); //1º pos
		Assert.assertEquals(lv.removeLast(), "1");
		Assert.assertEquals(lv.toString(), "(4 3 2 )");
		
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista=new DoubleLinkedListImpl<String>();
		lista.insertFirst("1");
		Assert.assertEquals(lista.removeLast(), "1");
		Assert.assertEquals(lista.toString(), "()");
	}
	
	@Test
	public void getPosFirst() {
		lv.insertFirst("2"); //4º pos
		lv.insertFirst("1");
		lv.insertFirst("2");
		lv.insertFirst("3"); //1º pos
		Assert.assertEquals(lv.getPosFirst("2"), 2);
	}
	
	@Test
	public void insertLast() {
		lv.insertLast("2"); //1º pos
		lv.insertLast("1");
		lv.insertLast("2");
		lv.insertLast("3"); //4º pos
		Assert.assertEquals(lv.getPosFirst("2"), 1);
	}
	
	@Test
	public void getPosLast() {
		lv.insertFirst("B"); //5º pos
		lv.insertFirst("A");
		lv.insertFirst("C");
		lv.insertFirst("B");
		lv.insertFirst("A"); //1º pos
		Assert.assertEquals(lv.getPosLast("B"), 5);
		Assert.assertEquals(lv.getPosLast("A"), 4);
	}
	
	@Test
	public void getElemPos() {
		lv.insertFirst("1");
		lv.insertFirst("2");
		lv.insertFirst("3");
		lv.insertFirst("4");
		Assert.assertEquals(lv.getElemPos(2), "3");
	}
	
	@Test
	public void testToString() {
		lv.insertFirst("1");
		lv.insertFirst("2");
		lv.insertFirst("3");
		lv.insertFirst("4");
		Assert.assertEquals(lv.toString(), "(4 3 2 1 )");
	}
	
	@Test
	public void testToStringReverse() {
		lv.insertFirst("1");
		lv.insertFirst("2");
		lv.insertFirst("3");
		lv.insertFirst("4");
		Assert.assertEquals(lv.toStringReverse(), "(1 2 3 4 )");
	}
	
	@Test
	public void getMax() {
		lv.insertFirst("B"); //5º pos
		lv.insertFirst("B");
		lv.insertFirst("C");
		lv.insertFirst("B");
		lv.insertFirst("A"); //1º pos
		Assert.assertEquals(lv.maxRepeated(), 3);
	}
	
	@Test
	public void igual() {
		lv.insertFirst("B"); //3º pos
		lv.insertFirst("B");
		lv.insertFirst("A"); //1º pos
		
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista=new DoubleLinkedListImpl<String>();
		lista.insertFirst("B"); //3º pos
		lista.insertFirst("B");
		lista.insertFirst("A"); //1º pos
		Assert.assertEquals(lv.isEquals(lista), true);
		
		DoubleLinkedListImpl<String> lista1 = new DoubleLinkedListImpl<String>();
		lista1=new DoubleLinkedListImpl<String>();
		lista1.insertFirst("B"); //3º pos
		lista1.insertFirst("B");
		lista1.insertFirst("A"); //1º pos
		
		DoubleLinkedListImpl<String> listaF = new DoubleLinkedListImpl<String>();
		listaF=new DoubleLinkedListImpl<String>();
		listaF.insertFirst("B"); //3º pos
		listaF.insertFirst("B");
		listaF.insertFirst("M"); //1º pos
		Assert.assertEquals(lista1.isEquals(listaF), false);
		
		DoubleLinkedListImpl<String> lista2 = new DoubleLinkedListImpl<String>();
		lista2=new DoubleLinkedListImpl<String>();
		lista2.insertFirst("C"); //3º pos
		lista2.insertFirst("B");
		lista2.insertFirst("A"); //1º pos
		
		DoubleLinkedListImpl<String> listaFa = new DoubleLinkedListImpl<String>();
		listaFa=new DoubleLinkedListImpl<String>();
		listaFa.insertFirst("A"); //3º pos
		listaFa.insertFirst("B");
		listaFa.insertFirst("C"); //1º pos
		Assert.assertEquals(lista2.isEquals(listaFa), false);
		
		DoubleLinkedListImpl<String> lista3 = new DoubleLinkedListImpl<String>();
		lista3=new DoubleLinkedListImpl<String>();
		lista3.insertFirst("D"); //4º pos
		lista3.insertFirst("C");
		lista3.insertFirst("B");
		lista3.insertFirst("A"); //1º pos
		
		DoubleLinkedListImpl<String> listaFax = new DoubleLinkedListImpl<String>();
		listaFax=new DoubleLinkedListImpl<String>();
		listaFax.insertFirst("C"); //3º pos
		listaFax.insertFirst("B");
		listaFax.insertFirst("A"); //1º pos
		Assert.assertEquals(lista3.isEquals(listaFax), false);
		
		DoubleLinkedListImpl<String> lista4 = new DoubleLinkedListImpl<String>();
		lista4=new DoubleLinkedListImpl<String>();
		lista4.insertFirst("D"); 
		lista4.insertFirst("C");
		Assert.assertEquals(lista4.isEquals(listaFax), false);
	}
	
	@Test
	public void igualTodos() {
		lv.insertFirst("B"); //3º pos
		lv.insertFirst("B");
		lv.insertFirst("A"); //1º pos
		
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista=new DoubleLinkedListImpl<String>();
		lista.insertFirst("B"); //2º pos
		lista.insertFirst("A"); //1º pos
		Assert.assertEquals(lv.containsAll(lista), true);
		
		lv.insertFirst("D"); //4º pos
		lv.insertFirst("C");
		lv.insertFirst("B");
		lv.insertFirst("A"); //1º pos
		
		DoubleLinkedListImpl<String> listaFa = new DoubleLinkedListImpl<String>();
		listaFa=new DoubleLinkedListImpl<String>();
		listaFa.insertFirst("A"); //3º pos
		listaFa.insertFirst("M");
		listaFa.insertFirst("C"); //1º pos
		Assert.assertEquals(lv.containsAll(listaFa), false);
	}
	
	
	@Test
	public void sublista() {
		lv.insertFirst("E"); //5º pos
		lv.insertFirst("D");
		lv.insertFirst("C"); 
		lv.insertFirst("B");
		lv.insertFirst("A"); //1º pos
		
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista=new DoubleLinkedListImpl<String>();
		lista.insertFirst("D"); //3º pos
		lista.insertFirst("C"); 
		lista.insertFirst("B"); //1º pos
		Assert.assertEquals(lv.isSubList(lista), true);
		
		lv.insertFirst("D"); //4º pos
		lv.insertFirst("C");
		lv.insertFirst("B");
		lv.insertFirst("A"); //1º pos
		
		DoubleLinkedListImpl<String> listaFa = new DoubleLinkedListImpl<String>();
		listaFa=new DoubleLinkedListImpl<String>();
		listaFa.insertFirst("C");
		listaFa.insertFirst("D"); //1º pos
		Assert.assertEquals(lv.isSubList(listaFa), false);
	}
	
	@Test
	public void stringFromUntil() {
		lv.insertFirst("E"); //5º pos
		lv.insertFirst("D");
		lv.insertFirst("C"); 
		lv.insertFirst("B");
		lv.insertFirst("A"); //1º pos
		Assert.assertEquals(lv.toStringFromUntil(2,4), "(B C D )");
	}
	
	@Test
	public void reverseList() {
		lv.insertFirst("E"); //3º pos
		lv.insertFirst("B");
		lv.insertFirst("A"); //1º pos
		
		DoubleLinkedListImpl<String> lista = new DoubleLinkedListImpl<String>();
		lista=(DoubleLinkedListImpl<String>) lv.reverse();
		Assert.assertEquals(lista.toString(),"(E B A )");
	}
	
	@Test
	public void isEmptyTest() {
		Assert.assertTrue(lv.isEmpty());
		Assert.assertTrue(lv.size()==0);
		Assert.assertFalse(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==6);
	}
	
	@Test
	public void clearTest() {
		lv.clear();
		Assert.assertTrue(lv.isEmpty());
		Assert.assertTrue(lv.size()==0);
		
		listaConElems.clear();
		Assert.assertTrue(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==0);
		Assert.assertEquals(listaConElems.toString(),listaConElems.toStringReverse());
		
	}
	
	
	
	@Test
	public void containsTest() {
		Assert.assertFalse(lv.contains("A"));
		Assert.assertTrue(listaConElems.contains("A"));
		Assert.assertTrue(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("D"));
		Assert.assertFalse(listaConElems.contains("Z"));
		
	}
	
	@Test
	public void removeAllTest() throws EmptyCollectionException {
        Assert.assertEquals(2, listaConElems.removeAll("A"));
    	Assert.assertEquals(listaConElems.toString(),"(B C B D )");
    	
        listaConElems.removeAll("B");
		Assert.assertFalse(listaConElems.contains("A"));
		Assert.assertFalse(listaConElems.contains("B"));
		Assert.assertEquals(listaConElems.toString(),"(C D )");
		listaConElems.removeAll("C");
		
		Assert.assertTrue(listaConElems.contains("D"));
		Assert.assertFalse(listaConElems.contains("C"));
        listaConElems.removeAll("D");
		
		Assert.assertFalse(listaConElems.contains("D"));
		Assert.assertTrue(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==0);
		Assert.assertEquals(listaConElems.toString(),listaConElems.toStringReverse());
		
	}
	
	@Test
	public void isSubListTest() throws EmptyCollectionException {
		Assert.assertTrue(listaConElems.isSubList(lv));
	    Assert.assertTrue(listaConElems.isSubList(new DoubleLinkedListImpl<String>("A", "B", "C")));
	    Assert.assertEquals(listaConElems.toString(),"(A B C A B D )");
	    Assert.assertEquals(new DoubleLinkedListImpl<String>("A", "C").toString(),"(A C )");   
	    Assert.assertFalse(listaConElems.isSubList(new DoubleLinkedListImpl<String>("A", "C")));
	    Assert.assertEquals(listaConElems.maxRepeated(),2);
	    listaConElems.insertBefore("A", "D");
	    Assert.assertEquals(listaConElems.toString(),"(A B C A B A D )");
	    Assert.assertTrue(listaConElems.maxRepeated()==3);
	        	  
	}
	
	@Test (expected = NullPointerException.class)
	public void insertFirstNull() {
		lv.insertFirst(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void insertLastNull() {
		lv.insertLast(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void insertPosNull() {
		lv.insertPos(null, 4);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void insertPosIllegal() {
		lv.insertPos("A", 0);
	}
	
	@Test (expected = EmptyCollectionException.class)
	public void removeFirstNull() throws EmptyCollectionException {
		lv.removeFirst();
	}
	
	@Test (expected = EmptyCollectionException.class)
	public void removeLastNull() throws EmptyCollectionException {
		lv.removeLast();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getElemPosIll() {
		lv.getElemPos(0);
	}
	
	@Test (expected = NullPointerException.class)
	public void getElemFirstNull() {
		lv.getPosFirst(null);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void getElemFirstSuch() {
		lv.insertFirst("1");
		lv.getPosFirst("2");
	}
	
	@Test (expected = NullPointerException.class)
	public void getElemLastNull() {
		lv.getPosLast(null);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void getElemLastSuch() {
		lv.insertFirst("1");
		lv.getPosLast("2");
	}
	
	@Test (expected = NullPointerException.class)
	public void removeAllNull() {
		lv.removeAll(null);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeAllSuch() {
		lv.insertFirst("1");
		lv.removeAll("2");
	}
	
	@Test (expected = NullPointerException.class)
	public void containsNull() {
		lv.contains(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void equalsNull() {
		lv.isEquals(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void containsAllNull() {
		lv.containsAll(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFromUntil() {
		lv.toStringFromUntil(-1, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFromUntil2() {
		lv.toStringFromUntil(0, -1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFromUntil3() {
		lv.toStringFromUntil(7, 2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFromUntil4() {
		lv.toStringFromUntil(3, -2);
	}

}
