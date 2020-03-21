package ule.edi.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;

public class EventArrayImpl implements Event {
	
	private String name;
	private Date eventDate;
	private int nSeats;
	
	private Double price;    // precio de entradas 
	private Byte discountAdvanceSale;   // descuento en venta anticipada (0..100)
   	
	private Seat[] seats;
		
	
	
   public EventArrayImpl(String name, Date date, int nSeats){
	 // utiliza los precios por defecto: DEFAULT_PRICE y DEFAULT_DISCOUNT definidos en Configuration.java   
	 // Debe crear el array de butacas
	   this.name = name;
       this.eventDate=date;
       this.nSeats=nSeats;
       price=Configuration.DEFAULT_PRICE;
       discountAdvanceSale=Configuration.DEFAULT_DISCOUNT;
       this.seats=new Seat[nSeats];
   }
   
   
   public EventArrayImpl(String name, Date date, int nSeats, Double price, Byte discount){
	   // Debe crear el array de butacas
	   this.name = name;
       this.eventDate=date;
       this.price=price;
       this.nSeats=nSeats;
       this.discountAdvanceSale=discount;
       this.seats=new Seat[nSeats];
   }


@Override
public String getName() {
	return name;
}


@Override
public Date getDateEvent() {
	return eventDate;
}


@Override
public Double getPrice() {
	return price;
}


@Override
public Byte getDiscountAdvanceSale() {
	return discountAdvanceSale;
}


@Override
public int getNumberOfSoldSeats() {
	int count=0;
	for(int i=0;i<nSeats;i++) {
		if(seats[i]==null) {
			count++;
		}
	}
	return (nSeats-count);
}


@Override
public int getNumberOfNormalSaleSeats() {
	int count=0;
	for(int i=0;i<nSeats;i++) {
		if(seats[i]!=null) {
			if(seats[i].getType().equals(Type.NORMAL)) {
				count++;
			}
		}
	}
	return count;
}


@Override
public int getNumberOfAdvanceSaleSeats() {
	int count=0;
	for(int i=0;i<nSeats;i++) {
		if(seats[i]!=null) {
			if(seats[i].getType().equals(Type.ADVANCE_SALE)) {
				count++;
			}
		}
	}
	return count;
}


@Override
public int getNumberOfSeats() {
	return nSeats;
}


@Override
public int getNumberOfAvailableSeats() {
	int count=0;
	for(int i=0;i<nSeats;i++) {
		if(seats[i]==null) {
			count++;
		}
	}
	return count;
}


@Override
public Seat getSeat(int pos) {
	if(pos>0 && pos<=nSeats) {
		if(seats[pos-1]!=null) {
			return seats[pos-1];
		}else {
			return null;
		}
	}else {
		return null;
	}
}


@Override
public Person refundSeat(int pos) {
	Person other = null;
	if(pos>0 && pos<=nSeats) {
		if(seats[pos-1]!=null) {
				other = seats[pos-1].getHolder();
				seats[pos-1]=null;
			}else {
				other= null;
		}
	}else {
		other=null;
	}
	return other;
}


@Override
public boolean sellSeat(int pos, Person p, boolean advanceSale) {
	boolean comprobante;
	Seat seat;
	if(pos>0 && pos<=nSeats) {
			if(advanceSale==true) {
				comprobante=true;
				seat = new Seat(this, pos, Type.ADVANCE_SALE, p);
				seats[pos-1]=seat;
			}else {
				comprobante=true;
				seat = new Seat(this, pos, Type.NORMAL, p);
				seats[pos-1]=seat;
			}
		}else {
			comprobante=false;
		}
	return comprobante;
}


@Override
public int getNumberOfAttendingChildren() {
	int count=0;
	for(int i=0;i<nSeats;i++) {
		if(seats[i]!=null) {
			if(seats[i].getHolder().getAge()<Configuration.CHILDREN_EXMAX_AGE) {
				count++;
			}
		}
	}
	return count;
}


@Override
public int getNumberOfAttendingAdults() {
	int count=0;
	for(int i=0;i<nSeats;i++) {
		if(seats[i]!=null) {
			if(seats[i].getHolder().getAge()>=Configuration.CHILDREN_EXMAX_AGE && seats[i].getHolder().getAge()<Configuration.ELDERLY_PERSON_INMIN_AGE) {
				count++;
			}
		}
	}
	return count;
}


@Override
public int getNumberOfAttendingElderlyPeople() {
	int count=0;
	for(int i=0;i<nSeats;i++) {
		if(seats[i]!=null) {
			if(seats[i].getHolder().getAge()>=Configuration.ELDERLY_PERSON_INMIN_AGE) {
				count++;
			}
		}
	}
	return count;
}


@Override
public List<Integer> getAvailableSeatsList() {
	List<Integer> al = new ArrayList<Integer>();
	for(int i=0;i<nSeats;i++) {
		if(seats[i]==null) {
			al.add(i+1);
		}
	}
	return al;
}


@Override
public List<Integer> getAdvanceSaleSeatsList() {
	List<Integer> al = new ArrayList<Integer>();
	for(int i=0;i<nSeats;i++) {
		if(seats[i]!=null) {
			if(seats[i].getType().equals(Type.ADVANCE_SALE)) {
				al.add(i+1);
			}
		}
	}
	return al;
}


@Override
public int getMaxNumberConsecutiveSeats() {
	int count=0,big=0;
	for(int i=0;i<nSeats;i++) {
		if(seats[i]==null) {
			count++;
		}else{
			if(count>big) {
				big=count;
				count=0;
			}	
		}
	}
	return big;
}

@Override
public Double getPrice(Seat seat) {
	if(seat.getEvent().equals(this)) {
		if(seat.getType().equals(Type.ADVANCE_SALE)) {
			price=100-(Configuration.DEFAULT_PRICE*Configuration.DEFAULT_DISCOUNT/100);
		}else {
			price=Configuration.DEFAULT_PRICE;
		}
	}else {
		price=(double) 0;	
	}
	return price;
}


@Override
public Double getCollectionEvent() {
	double count=0.0;
	for(int i=0;i<nSeats;i++) {
		if(seats[i]!=null) {
			count=seats[i].getEvent().getPrice(seats[i])+count;
		}
	}
	return count;
}


@Override
public int getPosPerson(Person p) {
	int comprobante=-1;
	for(int i=0;i<nSeats;i++) {
		if(seats[i]!=null) {
			if(p.equals(seats[i].getHolder())) {
				return i+1;	
			}
		}
	}
	return comprobante;
}


@Override
public boolean isAdvanceSale(Person p) {
	boolean comprobante=false;
	for(int i=0;i<nSeats;i++) {
		if(seats[i]!=null) {
			if(p.equals(seats[i].getHolder())) {
				if(seats[i].getType().equals(Type.ADVANCE_SALE)) {
					comprobante=true;
					break;
				}else {
					comprobante=false;
				}
			}
		}
	}
	return comprobante;
}
   


}	