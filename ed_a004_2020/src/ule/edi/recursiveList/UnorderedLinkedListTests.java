package ule.edi.recursiveList;



import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.exceptions.TypeIsNotComparableExeception;
import ule.edi.model.Person;



public class UnorderedLinkedListTests {

	

	private UnorderedLinkedListImpl<String> lS;
	private UnorderedLinkedListImpl<String> lSABC;
	

	@Before
	public void setUp() {
		this.lS = new UnorderedLinkedListImpl<String>();
		
		
		this.lSABC = new UnorderedLinkedListImpl<String>("A", "B", "C");
	}
	
   @Test
   public void constructorElemens(){
	   lS=new UnorderedLinkedListImpl<String>("A", "B", "C", "D");
	   Assert.assertEquals("(A B C D )", lS.toString());
   }

// TESTS DE addFirst
   @Test
   public void addFirstTest(){
	   
	   lS.addFirst("D");
	   Assert.assertEquals("(D )", lS.toString());
	   lS.addFirst("C");
	   Assert.assertEquals("(C D )", lS.toString());
	   lS.addFirst("B");
	   Assert.assertEquals("(B C D )", lS.toString());
	   lS.addFirst("A");
	   Assert.assertEquals("(A B C D )", lS.toString());
   }
   
   // TESTS DE addBefore
   
   @Test
   public void addBeforeTest(){
	   lS.addFirst("D");
	   Assert.assertEquals("(D )", lS.toString());
	   lS.addBefore("C", "D");
	   Assert.assertEquals("(C D )", lS.toString());
	   lS.addBefore("A","C");
	   Assert.assertEquals("(A C D )", lS.toString());
	   lS.addBefore("B", "C");
	   Assert.assertEquals("(A B C D )", lS.toString());
	   lS.addBefore("H", "D");
	   Assert.assertEquals("(A B C H D )", lS.toString());
	   Assert.assertEquals(lS.size(), 5);
   }
   
   
   //Tests toStringReverse 
 
   @Test
   public void toStringReverse(){
	   lS=new UnorderedLinkedListImpl<String>("A", "B", "C", "D");
	   Assert.assertEquals("(A B C D )", lS.toString());
	   Assert.assertEquals("(D C B A )", lS.toStringReverse());
		  
   }
// Tests eliminar duplicados
	
   @Test
	public void testRemoveDuplicates() throws EmptyCollectionException {
	    UnorderedLinkedListImpl<String> lista=new UnorderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C"); 
		Assert.assertEquals(lista.removeDuplicates(),4); 
		Assert.assertEquals(lista.toString(), "(A B C )");
		Assert.assertEquals(lSABC.removeDuplicates(),0); // 0 repetids
		Assert.assertEquals(lSABC.toString(), "(A B C )");	
	
	}
  
   
   
// AÑADIR MAS TESTS para el resto de casos especiales y para el resto de métodos
 // de las clases AbstractLinkedListImpl y UnorderedLinkedListImpl
   
   @Test
   public void countTest() {
	   lS.addFirst("D");
	   lS.addFirst("C");
	   lS.addFirst("B");
	   lS.addLast("A");
	   Assert.assertEquals("(B C D A )", lS.toString());
	   Assert.assertEquals(lS.count("A"), 1);
	   lS.addFirst("A");
	   Assert.assertEquals(lS.count("A"), 2);
   }
   
   @Test
   public void getLastTest() throws EmptyCollectionException {
	   lS.addFirst("D");
	   lS.addFirst("C");
	   lS.addFirst("B");
	   Assert.assertEquals(lS.getLast(), "D");
   }
   
   @Test
   public void getFirstTest() throws EmptyCollectionException {
	   lS.addFirst("D");
	   lS.addFirst("C");
	   lS.addFirst("B");
	   Assert.assertEquals(lS.getFirst(), "B");
   }
   
   @Test
   public void iteratorTest() {
	   lS.addFirst("E");
	   lS.addFirst("D");
	   lS.addFirst("C");
	   lS.addFirst("B");
	   lS.addFirst("A");
	   Iterator<String> iter = lS.iterator();
	   Assert.assertEquals(iter.next().toString(),"A");
	   Assert.assertEquals(iter.next().toString(),"B");
   }
   
   @Test
   public void addLastTest() {
	   lS.addLast("E");
	   Assert.assertEquals(lS.toString(),"(E )");
	   lS.addLast("A");
	   Assert.assertEquals(lS.toString(),"(E A )");
   }
   
   @Test
   public void reverseTest() {
	   lS.addFirst("E");
	   Assert.assertEquals(lS.toStringReverse(),"(E )");
   }
   
   @Test
   public void removeTest() throws EmptyCollectionException {
	   lS.addFirst("E");
	   lS.addFirst("D");
	   lS.addFirst("C");
	   lS.addFirst("B");
	   lS.addFirst("A");
	   lS.remove("D");
	   Assert.assertEquals(lS.toString(),"(A B C E )");
	   lS.remove("E");
	   Assert.assertEquals(lS.toString(),"(A B C )");
	   lS.remove("A");
	   Assert.assertEquals(lS.toString(),"(B C )");
   }
   
   @Test
   public void stringFromUntilTest() {
	   lS.addFirst("E");
	   lS.addFirst("D");
	   lS.addFirst("C");
	   lS.addFirst("B");
	   lS.addFirst("A");
	   Assert.assertEquals(lS.toStringFromUntil(2, 4),"(B C D )");
	   Assert.assertEquals(lS.toStringFromUntil(1, 3),"(A B C )");
	   Assert.assertEquals(lS.toStringFromUntil(1, 5),"(A B C D E )");
   }
   
   @Test
   public void stringFromUntil1ElemTest() {
	   lS.addFirst("E");
	   Assert.assertEquals(lS.toStringFromUntil(1, 1),"(E )");
   }
   
   @Test
   public void removeLastTest() throws EmptyCollectionException {
	   lS.addFirst("A");
	   lS.addFirst("D");
	   lS.addFirst("C");
	   lS.addFirst("A");
	   lS.addFirst("A");
	   lS.removeLast("A");
	   Assert.assertEquals(lS.toString(),"(A A C D )");
	   lS.removeLast("A");
	   Assert.assertEquals(lS.toString(),"(A C D )");
	   lS.removeLast("A");
	   Assert.assertEquals(lS.toString(),"(C D )");
	   
   }
   
   @Test
   public void isOrderedTest() throws EmptyCollectionException {
	   Assert.assertEquals(lS.isOrdered(), true);
	   
	   lS.addFirst("E");
	   lS.addFirst("D");
	   lS.addFirst("C");
	   lS.addFirst("B");
	   lS.addFirst("A");
	   Assert.assertEquals(lS.isOrdered(), true);
	   
	   lS.addFirst("E");
	   Assert.assertEquals(lS.isOrdered(), false);
   }
   
   @Test
	public void testEquals() throws Exception{
		Person person = new Person("48755566V","Martha",34);
		
		Person person1 = new Person("48755566V","Pedro",30);
		Assert.assertTrue(person.equals(person1));
		
		Person person2 = null;
		Assert.assertFalse(person.equals(person2));
		
		Assert.assertTrue(person.equals(person));
	}
	
   @Test (expected = NullPointerException.class)
   public void testAddFirstNull() {
	   lS.addFirst(null);
   }
   
   @Test (expected = NullPointerException.class)
   public void testAddLastNull() {
	   lS.addLast(null);
   }
   
   @Test (expected = NullPointerException.class)
   public void testAddBeforeNull() {
	   lS.addBefore(null,"C");
   }
   
   @Test (expected = NoSuchElementException.class)
   public void testAddBeforeNoSuch() {
	   lS.addBefore("D","C");
   }
   
   @Test (expected = NullPointerException.class)
   public void testContainsNull() {
	   lS.contains(null);
   }
   
   @Test (expected = NullPointerException.class)
   public void testCountNull() {
	   lS.count(null);
   }
   
   @Test (expected = EmptyCollectionException.class)
   public void testGetLastEmpty() throws EmptyCollectionException {
	   lS.getLast();
   }
   
   @Test (expected = EmptyCollectionException.class)
   public void testGetFirstEmpty() throws EmptyCollectionException {
	   lS.getFirst();
   }
   
   @Test (expected = NoSuchElementException.class)
   public void testIteratorNosuch() {
	   lS.addFirst("C");
	   lS.addFirst("B");
	   lS.addFirst("A");
	   Iterator<String> iter = lS.iterator();
	   iter.next();
	   iter.next();
	   iter.next();
	   iter.next();
   }
   
   @Test (expected = UnsupportedOperationException.class)
   public void testIteratorRemove() {
	   lS.addFirst("C");
	   lS.addFirst("B");
	   lS.addFirst("A");
	   Iterator<String> iter = lS.iterator();
	   iter.remove();
   }
   
   @Test(expected = EmptyCollectionException.class)
   public void testRemoveEmpty() throws EmptyCollectionException {
	   lS.remove(null);
   }
   
   @Test(expected = NullPointerException.class)
   public void testRemoveNull() throws EmptyCollectionException {
	   lS.addFirst("A");
	   lS.remove(null);
   }
   
   @Test(expected = NoSuchElementException.class)
   public void testRemoveNoSuch() throws EmptyCollectionException {
	   lS.addFirst("D");
	   lS.remove("A");
   }
   
  @Test (expected = TypeIsNotComparableExeception.class)
   public void testIsOrdered() throws TypeIsNotComparableExeception{
	   int [] a = new int [1];
	   int [] b = new int[1];
	   a[0]=1;
	   b[0]=3;
	   UnorderedLinkedListImpl<int[]> list = new UnorderedLinkedListImpl<int[]>(a,b);
	   list.isOrdered();
   }
  
  @Test(expected = NullPointerException.class)
  public void testRemoveLastNull() throws EmptyCollectionException {
	   lS.addFirst("A");
	   lS.removeLast(null);
  }
  
  @Test(expected = NoSuchElementException.class)
  public void testRemoveLastNoSuch() throws EmptyCollectionException {
	   lS.addFirst("D");
	   lS.removeLast("A");
  }
  
  @Test(expected = EmptyCollectionException.class)
  public void testRemoveLastEmpty() throws EmptyCollectionException {
	   lS.removeLast(null);
  }
  
  @Test (expected = IllegalArgumentException.class)
	public void toStringFromUntil() {
		lS.toStringFromUntil(-1, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFromUntil2() {
		lS.toStringFromUntil(0, -1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFromUntil3() {
		lS.toStringFromUntil(7, 2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void toStringFromUntil4() {
		lS.toStringFromUntil(3, -2);
	}
	
	@Test (expected = EmptyCollectionException.class)
	public void removeDupEmp() throws EmptyCollectionException{
		lS.removeDuplicates();
	}
   
}
