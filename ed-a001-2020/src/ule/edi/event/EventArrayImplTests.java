package ule.edi.event;

import static org.junit.Assert.assertEquals;

import java.awt.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.*;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;

public class EventArrayImplTests {

	private DateFormat dformat = null;
	private EventArrayImpl e;
	
	private Date parseLocalDate(String spec) throws ParseException {
        return dformat.parse(spec);
	}

	public EventArrayImplTests() {
		dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	@Before
	public void testBefore() throws Exception{
	    e = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 110);
	}
	
	@Test
	public void testEventoVacio() throws Exception {
	    Assert.assertTrue(e.getNumberOfAvailableSeats()==110);
	    Assert.assertEquals(e.getNumberOfAvailableSeats(), 110);
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
	}
	
	@Test
	public void testSellSeat1Adult() throws Exception{
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
		Assert.assertTrue(e.sellSeat(1, new Person("10203040A","Alice", 34),false));	//venta normal
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);  
	    Assert.assertEquals(e.getNumberOfNormalSaleSeats(), 1);
	}
	
	@Test
	public void testgetCollection() throws Exception{
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 4);
		Assert.assertEquals(ep.sellSeat(1, new Person("1010", "AA", 10), true),true);
		Assert.assertTrue(ep.getCollectionEvent()==75.0);	
	
		Person person = new Person("H","90",54);
		ep.sellSeat(1, person, true);
		Assert.assertEquals(ep.getCollectionEvent(), 75.0,0);
	}
	
	
	// TODO EL RESTO DE METODOS DE TESTS NECESARIOS PARA LA COMPLETA COMPROBACIÓN DEL BUEN FUNCIONAMIENTO DE TODO EL CÓDIGO IMPLEMENTADO
	@Test
	public void testGetName() throws Exception {
		Assert.assertEquals(e.getName(),"The Fabulous Five");
	}
	
	@Test
	public void testGetDate() throws Exception{
		Assert.assertEquals(e.getDateEvent(), parseLocalDate("24/02/2018 17:00:00"));
	}
	
	@Test
	public void testGetPrice() throws Exception{
		Event ev=new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 4,Configuration.DEFAULT_PRICE,Configuration.DEFAULT_DISCOUNT);
		Assert.assertEquals(ev.getPrice(), Configuration.DEFAULT_PRICE);
	}
	
	@Test
	public void testGetDiscountAdvanceSale() throws Exception{
		Event ev=new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 4,Configuration.DEFAULT_PRICE,Configuration.DEFAULT_DISCOUNT);
		Assert.assertEquals(ev.getDiscountAdvanceSale(), Configuration.DEFAULT_DISCOUNT);
	}
	
	@Test
	public void testGetNumberOfSeats() throws Exception{
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 4);
		Assert.assertEquals(ep.getNumberOfSeats(), 4);
	}
	
	@Test
	public void testGetNumberOfAvalibleSeats() throws Exception{
		Assert.assertEquals(e.getNumberOfAvailableSeats(), 110);
		Person person = new Person("Alice", "71466644D", 44);
		e.sellSeat(2, person, true);
		Assert.assertEquals(e.getNumberOfAvailableSeats(), 109);
	}
	
	@Test
	public void testGetNumberOfAttendingAdults() throws Exception{
		Person person = new Person("Alice", "71466644D", 44);
		e.sellSeat(2, person, true);
		Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);
		
		Person person1 = new Person("Jhon", "71466644D", 10);
		e.sellSeat(3, person1, true);
		Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);
		
		Person person2 = new Person("Peter","71456578A",65);
		e.sellSeat(3, person2, true);
		Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);
		
		Person person3 = new Person("Charlotte","71423322R",14);
		e.sellSeat(4, person3, true);
		Assert.assertEquals(e.getNumberOfAttendingAdults(), 2);
		
	}
	
	@Test
	public void testGetNumberOfAttendingChildren() throws Exception{
		Person person = new Person("Alice", "71466644D", 44);
		e.sellSeat(2, person, true);
		Assert.assertEquals(e.getNumberOfAttendingChildren(), 0);
		
		Person person1 = new Person("Max", "71234578D", 9);
		e.sellSeat(3, person1, true);
		Assert.assertEquals(e.getNumberOfAttendingChildren(), 1);
	}
	
	@Test
	public void testGetNumberOfAttendingElderlyPeople() throws Exception{
		Person person = new Person("Alice", "71466644D", 44);
		e.sellSeat(2, person, true);
		Assert.assertEquals(e.getNumberOfAttendingElderlyPeople(), 0);
		
		Person person1 = new Person("Alice", "71466644D", 75);
		e.sellSeat(3, person1, true);
		Assert.assertEquals(e.getNumberOfAttendingElderlyPeople(), 1);
	}
	
	@Test
	public void testSellSeats() throws Exception{
		Person person1 = new Person("Albert", "89541256K", 40);
		Assert.assertEquals(e.sellSeat(2, person1, true),true);
		
		Person person2= new Person("James", "48725610F", 29);
		Assert.assertEquals(e.sellSeat(111, person2, true),false);
		
		Person person = new Person("Alice", "71466644D", 44);
		Assert.assertEquals(e.sellSeat(0, person, true),false);
	}
	
	@Test
	public void testGetNumberOfSoldSeats() throws Exception{
		Person person = new Person("Alice", "71466644D", 44);
		e.sellSeat(1, person, true);
		Assert.assertEquals(e.getNumberOfSoldSeats(), 1);
		
		Person person1 = new Person("James", "71488899D", 41);
		e.sellSeat(1, person1, true);
		Assert.assertEquals(e.getNumberOfSoldSeats(), 1);
	}
	
	@Test
	public void testGetNumberOfNormalSaleSeats() throws Exception{
		Person person = new Person("Alice", "71466644D", 44);
		e.sellSeat(1, person, true);
		Assert.assertEquals(e.getNumberOfNormalSaleSeats(), 0);
		
		Person person1 = new Person("Patrice", "78966655A", 34);
		e.sellSeat(2, person1, false);
		Assert.assertEquals(e.getNumberOfNormalSaleSeats(), 1);
	}
	
	@Test
	public void testGetNumberOfAdvanceSaleSeats() throws Exception{
		Person person = new Person("Alice", "71466644D", 44);
		e.sellSeat(1, person, false);
		Assert.assertEquals(e.getNumberOfAdvanceSaleSeats(), 0);
		
		Person person1 = new Person("Christian", "71452399F", 44);
		e.sellSeat(2, person1, true);
		Assert.assertEquals(e.getNumberOfAdvanceSaleSeats(), 1);
	}
	
	@Test
	public void testGetSeat() throws Exception{
		Assert.assertEquals(e.getSeat(200), null);
		Assert.assertEquals(e.getSeat(0), null);
		Assert.assertEquals(e.getSeat(5), null);
		
		Person person = new Person("Alice", "71466644D", 44);
		e.sellSeat(1, person, true);
		Assert.assertEquals(person,e.getSeat(1).getHolder());
		Assert.assertEquals(e, e.getSeat(1).getEvent());
		Assert.assertEquals(Type.ADVANCE_SALE, e.getSeat(1).getType());
	}
	
	@Test
	public void testRefundSeat() throws Exception{
		Person person = new Person("Alice", "71466644D", 44);
		e.sellSeat(1, person, true);
		Assert.assertEquals(e.refundSeat(1), person);
		
		Person person1 = new Person("Tom","71488799P",56);
		e.sellSeat(110,person1,true);
		Assert.assertEquals(e.refundSeat(110), person1);
		
		Assert.assertEquals(e.refundSeat(200), null);
		Assert.assertEquals(e.refundSeat(0), null);
		Assert.assertEquals(e.refundSeat(20), null);
		
	}
	
	@Test
	public void testGetAvailableSeatsList() throws Exception{
		Event ev=new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 3);
		ArrayList<Integer> lista = new ArrayList<Integer>();
		Person person = new Person("Alice", "71466644D", 44);
		ev.sellSeat(1, person, true);
		lista.add(2);
		lista.add(3);
		
		Assert.assertEquals(ev.getAvailableSeatsList(), lista);
	}
	
	@Test
	public void testGetAdvanceSaleSeatsList() throws Exception{
		Event ev=new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 3);
		
		ArrayList<Integer> lista2 = new ArrayList<Integer>();
		Person person1 = new Person("Thomas", "71409077T", 55);
		ev.sellSeat(1, person1, false);
		Assert.assertEquals(ev.getAdvanceSaleSeatsList(), lista2);
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		Person person = new Person("Alice", "71466644D", 44);
		ev.sellSeat(2, person, true);
		lista.add(2);
		Assert.assertEquals(ev.getAdvanceSaleSeatsList(), lista);
	}
	
	@Test
	public void getMaxNumberConsecutiveSeats() throws Exception{
		Event ev=new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 10);
		
		Person person = new Person("Thomas", "71409077T", 55);
		ev.sellSeat(1, person, false);
		
		Person person1 = new Person("Alice", "78966654D", 14);
		ev.sellSeat(2, person1, true);
		
		Person person2 = new Person("Patric", "98908766D", 9);
		ev.sellSeat(6, person2, true);
		
		Person person3 = new Person("Salome", "23451166D", 76);
		ev.sellSeat(7, person3, true);
		
		Person person4 = new Person("Marianne", "56544433T", 44);
		ev.sellSeat(9, person4, true);
		
		Assert.assertEquals(ev.getMaxNumberConsecutiveSeats(), 3);
	}
	

	@Test
	public void testGetPriceSeat() throws Exception{
		Person person = new Person("Thomas", "71409077T", 55);
		Seat seat = new Seat(e, 1, Type.ADVANCE_SALE, person);
		Assert.assertEquals(e.getPrice(seat), 75.0,0);
		
		Person person1 = new Person("Lolita", "78433369A", 15);
		Seat seat1 = new Seat(e, 1, Type.NORMAL, person1);
		Assert.assertEquals(e.getPrice(seat1), 100.0,0);
		
		Event ev=new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 10);
		Person person2 = new Person("Lolita", "36655522V", 55);
		Seat seat2 = new Seat(ev, 1, Type.NORMAL, person2);
		Assert.assertEquals(e.getPrice(seat2), 0.0,0);
	}
	
	@Test
	public void testGetPosPerson() throws Exception{
		Person person2 = new Person("Pedro","78455599B",13);
		Assert.assertEquals(e.getPosPerson(person2), -1);
		
		Person person=new Person("Ron","56777788P",54);
		e.sellSeat(2, person, true);
		Assert.assertEquals(e.getPosPerson(person), 2);
		
		Person person1 = new Person("Gloria","12345678Q",43);
		Assert.assertEquals(e.getPosPerson(person1), -1);
	}
	
	@Test
	public void testIsAdvancedSale() throws Exception{
		Person person = new Person("Thomas", "71409077T", 55);
		e.sellSeat(3, person, true);
		Assert.assertEquals(e.isAdvanceSale(person), true);
		
		Person person1 = new Person("Carl", "71499977N", 23);
		e.sellSeat(2, person1,false);
		Assert.assertEquals(e.isAdvanceSale(person1), false);
	}
	
	@Test
	public void testEquals() throws Exception{
		Person person = new Person("Martha","48755566V",34);
		
		Person person1 = new Person("Pedro","48755566V",30);
		Assert.assertTrue(person.equals(person1));
		
		Person person2 = null;
		Assert.assertFalse(person.equals(person2));
		
		Person person3= new Person("Paloma",null,90);
		Assert.assertFalse(person3.equals(person));
		
		Person person4= new Person("Roman",null,9);
		Assert.assertTrue(person3.equals(person4));
		
		Seat seat = new Seat(e, 2, Type.ADVANCE_SALE, person);
		Assert.assertFalse(person.equals(seat));
		
		
	}
	
}
