package ule.edi.doubleLinkedList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {
	
	
	//	referencia al primer nodo de la lista
	private DoubleNode<T> front;
	
	//	referencia al Último nodo de la lista
	private DoubleNode<T> last;
	
	
	private class DoubleNode<T> {
		
		DoubleNode(T element) {
			this.elem = element;
			this.next = null;
			this.prev = null;
		}
		
		T elem;
		
		DoubleNode<T> next;
	    DoubleNode<T> prev;
	}

///// ITERADOR normal //////////

	@SuppressWarnings("hiding")
	private class DobleLinkedListIterator<T> implements Iterator<T> {
		 private DoubleNode<T> current;
		
       	
		public DobleLinkedListIterator(DoubleNode<T> nodo) {
			this.current=nodo;
		}
		
		@Override
		public boolean hasNext() {
			return (current!=null);
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			T item = current.elem;
			current = current.next;
			return item;
		}
	}
	
	////// FIN ITERATOR
	
	
	
	/// TODO :  AÑADIR OTRAS CLASES PARA LOS OTROS 3 ITERADORES
	private class reverseIterator<T> implements Iterator<T>{
		private DoubleNode<T> current;
		
       	
		public reverseIterator(DoubleNode<T> nodo) {
			this.current=nodo;
		}
		
		@Override
		public boolean hasNext() {
			return (current!=null);
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			T item = current.elem;
			current = current.prev;
			return item;
		}
	}
	
	private class evenIterator<T> implements Iterator<T> {
		 private DoubleNode<T> current;
		 private int count;
      	
		public evenIterator(DoubleNode<T> nodo) {
			this.current=nodo;
			this.count=0;
		}
		
		@Override
		public boolean hasNext() {
			if(current.next!=null) {
				if(current.next.next!=null) {
					return true;
				}
			}
			return false;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			T item;
			if(count==0) {
				current = current.next;
				 item = current.elem;
				count++;
			}else {
				current = current.next.next;
				item = current.elem;
			}
			return item;
		}
	}
	
	
	private class progressIterator<T> implements Iterator<T> {
		 private DoubleNode<T> current;
		 private int auxCount;//num de iteracion estamos
		 

     	
		public progressIterator(DoubleNode<T> nodo) {
			this.current=nodo;
			this.auxCount=1; //iteraciones, numero de ellas
			
		}
		
		@Override
		public boolean hasNext() {
			int count=0;
			count=0;
			DoubleNode<T> aux = current;
			//Nos apoyamos en el auxiliar para avanzar con el hasNext()
			while(count<auxCount) {
				if(aux.next==null) {
					return false;
				}
				aux=aux.next;
				count++;
			}
			return true;
			
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			T item;
			item=current.elem;
			int count=0; //reiniciamos siempre, avanzamos 1 a 1
			
			while(count<auxCount){
				current=current.next;
				count++;
			}
			auxCount++;
			
			return item;
		}
	}
	
    /////
	
	@SafeVarargs
	public DoubleLinkedListImpl(T...v ) {
		for (T elem:v) {
			this.insertLast(elem);
		}
	}


	@Override
	public boolean isEmpty() {
		if(size()==0) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public void clear() {
		front=last=null;
	}


	@Override
	public void insertFirst(T elem) {
		if(elem!=null) {
			DoubleNode<T> queue = new DoubleNode<T>(elem);
			if(isEmpty()) {
				front=queue;
				last=queue;
			}else {
				front.prev=queue;
				queue.next=front;
				front=queue;
			}
		}else {
			throw new NullPointerException();
		}
	}


	@Override
	public void insertLast(T elem) {
		if(elem!=null) {
			DoubleNode<T> queue = new DoubleNode<T>(elem);
			if(isEmpty()) {
				last=queue;
				front=queue;
			}else {
				last.next=queue;
				queue.prev=last;
				last=queue;
			}
		}else {
			throw new NullPointerException();
		}
	}

	
	@Override
	public T removeFirst() throws EmptyCollectionException{
		if(isEmpty()) {
			throw new EmptyCollectionException("data");
		}
		
		T result = front.elem;
		front=front.next;
		
		if(front==null) {
			last=null;
		}else {
			front.prev=null;
		}

		return result;
	}


	@Override
	public T removeLast()  throws EmptyCollectionException{
		if(isEmpty()) {
			throw new EmptyCollectionException("data");
		}
		
		T result= last.elem;
		last=last.prev;
		
		if(last==null) {
			front=null;
		}else {
			last.next=null;
		}
		
		return result;
	}


	@Override
	public void insertPos(T elem, int position) {
		DoubleNode<T> aux = new DoubleNode<T>(elem);
		if(elem!=null) {
			if(position>this.size()) {
				insertLast(elem);
			}else {
				if(position>0) {
					//comprobamos si es la cabeza
					if(position==1) {
						insertFirst(elem);
					}else {
						while(position-- != 0) {
							if(position==1) {
								aux.next=front.next;
								front.next=aux;
							}
							aux=aux.next;
						}
					}
				}else {
					throw new IllegalArgumentException();
				}
			}
		}else {
			throw new NullPointerException();
		}
	}


	@Override
	public void insertBefore(T elem, T target) {
		DoubleNode<T> aux = front;
		DoubleNode<T> aux2 = new DoubleNode<T>(elem);
		int a=0;
		if(elem!=null) {
			if(contains(target)) {
				while(aux!=null && a==0) {
					if(aux.elem.equals(target)) {
						
						aux2.prev=aux.prev;
						aux2.next=aux;
						
						if(aux.prev!=null) {
							aux.prev.next=aux2;
						}
						
						if(aux==front) {
							front=aux2;
						}
						
						a=1;
					}
					aux=aux.next;
				}
				
			}else {
				throw new NoSuchElementException();
			}
		}else {
			throw new NullPointerException();
		}
	}


	@Override
	public T getElemPos(int position) {
		DoubleNode<T> aux=front;
		int count=1;
		if(position>0 && position<=this.size()) {
			while(count!=position) {
				count++;
				aux=aux.next;
			}
		}else {
			throw new IllegalArgumentException();
		}
		return aux.elem;
	}


	@Override
	public int getPosFirst(T elem) {
		int count=1;
		int countFinal=0;
		boolean check=false;
		if(elem!=null) {
			if(contains(elem)) {
				DoubleNode<T> aux=front;
				while(aux!=null) {
					if(aux.elem.equals(elem)) {
						if(check==false) {
							countFinal=count;
							check=true;
						}
					}
					count++;
					aux=aux.next;
				}
			}else {
				throw new NoSuchElementException();
			}
		}else {
			throw new NullPointerException();
		}
		return countFinal;
	}


	@Override
	public int getPosLast(T elem) {
		int count=0;
		int countFinal=0;
		boolean check=false;
		if(elem!=null) {
			if(contains(elem)) {
				DoubleNode<T>aux=last;
				while(aux!=null) {
					if(aux.elem.equals(elem)) {
						if(check==false) {
							countFinal=count;
							check=true;
						}
					}
					count++;
					aux=aux.prev;
				}
			}else {
				throw new NoSuchElementException();
			}
		}else {
			throw new NullPointerException();
		}
		return (this.size()-countFinal);
	}


	@Override
	public T removePos(int pos) {
		if(pos>=1 && pos<=size()) {
			//Caso de que solo haya 1 elemento
			if(this.size()==1) {
				T result3= front.elem;
				front=last=null;
				return result3;
			}
			//Si eliminamos la primera posicion
			if(pos==1) {
				T result= front.elem;
				front=front.next;
				front.prev=null;
				return result;
			}else {
				if(pos==size()) {
					T result2= last.elem;
					last=last.prev;
					last.next=null;
					return result2;
				}else {
					DoubleNode<T> aux=front;
	
					for(int i=1;i<pos;i++) {
						aux=aux.next;
					}
					
					aux.prev.next=aux.next;
					aux.next.prev=aux.prev;
					
					return aux.elem;
				}
			}
		}else {
			throw new IllegalArgumentException();
		}
	}


	@Override
	public int removeAll(T elem) {
		int count=0,countAux=1;
		if(elem!=null) {
			if(contains(elem)) {
				DoubleNode<T> aux=front;
				while(aux!=null) {
					if(aux.elem.equals(elem)) {
						aux=aux.next;
						removePos(countAux);
						count++;
					}else {
						aux=aux.next;
						countAux++;
					}
				}
			}else {
				throw new NoSuchElementException();
			}
		}else {
			throw new NullPointerException();
		}
		return count;
	}


	@Override
	public boolean contains(T elem) {
		if(elem!=null) {
			DoubleNode<T> aux1=front;
			while(aux1!=null) {
				if(aux1.elem.equals(elem)) {
					return true;
				}
				aux1=aux1.next;
			}
		}else {
			throw new NullPointerException();
		}
		return false;
	}


	@Override
	public int size() {
		int count=0;
		DoubleNode<T> aux = front;
		
		while(aux!=null) {
			count++;
			aux=aux.next;
		}
		return count;
	}


	@Override
	public String toStringReverse() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("(");
		
		//Empleamos el uso del iterador
		Iterator<T> aux = this.reverseIterator();
		
		while(aux.hasNext()) {
			buffer.append(aux.next().toString());
			buffer.append(" ");
		}
		buffer.append(")");
		return buffer.toString();
	}

	@Override
	public DoubleList<T> reverse() {
		DoubleLinkedListImpl<T> lv = new DoubleLinkedListImpl<T>();
		DoubleNode<T> aux = last;
 
        while (aux != null) { 
            lv.insertLast((T) aux.elem);
            aux=aux.prev;
        } 

		return lv;
	}
	

	@Override
	public int maxRepeated() {
		DoubleNode<T> aux=front;
		int count=0,big=0;
		while(aux!=null) {
			DoubleNode<T> aux2=aux.next;
			while(aux2!=null) {
				if(aux.elem==aux2.elem) {
					count++;
				}
				aux2=aux2.next;
			}
			//Asignamos a big el mayor valor que adquiera count
			if(count>big) {
				big=count;
			}
			aux=aux.next;
		}
		return big;
	}


	@Override
	public boolean isEquals(DoubleList<T> other) {
		DoubleNode<T> aux=front;
		int count=0,count1=1;
		if(other!=null) {
			if(this.size()!=other.size()) {
				return false;
			}else {
				while(aux!=null) {
					if(other.getElemPos(count1).equals(aux.elem)) {
						count++;
					}
					count1++;
					aux=aux.next;
				}
			}
			
			if(count==(other.size())) {
				return true;
			}else {
				return false;
			}
			
		}else {
			throw new NullPointerException();
		}
	}


	@Override
	public boolean containsAll(DoubleList<T> other) {
		DoubleNode<T> aux=front;
		if(other!=null) {
			while(aux!=null) {
				if(!other.contains(aux.elem)) {
					return false;
				}
				aux=aux.next;
			}
			
		}else {
			throw new NullPointerException();
		}
		return true;
	}


	@Override
	public boolean isSubList(DoubleList<T> other) {
		DoubleNode<T> aux=front;
		int countOther=1;
		int correcto=0;
		if(other!=null) {
			while(aux!=null && countOther<=other.size()) {
				if(aux.elem.equals(other.getElemPos(countOther))) {
					countOther++;
					correcto++;
				}else {
					countOther=1;
					correcto=0;
				}
				aux=aux.next;
			}
			if(correcto==other.size()) {
				return true;
			}
		}else {
			throw new NullPointerException();
		}
		return false;
	}


	@Override
	public String toStringFromUntil(int from, int until) {
		if(from<=0 || until<=0 || from>until) {
			throw new IllegalArgumentException();
		}else {
			final StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			
		    for(int i=from;i<=until;i++){ 
		    	buffer.append(this.getElemPos(i).toString());
		    	buffer.append(" ");
		    }
			
			buffer.append(")");
			return buffer.toString();
		}
	}
	
	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("(");
		
		//Empleamos el uso del iterador
		Iterator<T> aux = this.iterator();
		
		while(aux.hasNext()) {
			buffer.append(aux.next().toString());
			buffer.append(" ");
		}
		buffer.append(")");
		return buffer.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new DobleLinkedListIterator<T> (front);
	}

	@Override
	public Iterator<T> reverseIterator() {
		return new reverseIterator<T>(last);
	}

	@Override
	public Iterator<T> evenPositionsIterator() {
		return new evenIterator<T>(front);
	}

	@Override
	public Iterator<T> progressIterator() {
		return new progressIterator<T>(front);
	}


}
