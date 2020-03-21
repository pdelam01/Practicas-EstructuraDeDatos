package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;


public class LinkedQueueWithRepImpl<T> implements QueueWithRep<T> {
 
	// Atributos
	private QueueWithRepNode<T> front;
	int count;
	
	
	// Clase interna
	@SuppressWarnings("hiding")
	public class QueueWithRepNode<T> {
		T elem;
		int num;
		QueueWithRepNode<T> next;
		
		public QueueWithRepNode (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
		
	}
	
	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class LinkedQueueWithRepIterator<T> implements Iterator<T> {
		private QueueWithRepNode<T> current;
		int veces;
		
		public LinkedQueueWithRepIterator(QueueWithRepNode<T> nodo) {
			current=nodo;
			veces=0;
		}
		
		@Override
		public boolean hasNext() {
			return (current!=null);
		}

		@Override
		public T next() {
			if (! hasNext())
				 throw new NoSuchElementException();
			
			T item = current.elem;
			veces++;
			if(veces==current.num) {
				current = current.next;
				veces=0;
			}
			return item;
			 
		}

		

	}
	////// FIN ITERATOR
	
	public LinkedQueueWithRepImpl() {
		front=null;
		count=0;
	}

	/////////////
	
	@Override
	public void add(T element) {
		if(element!=null) {
			//Creamos un nuevo nodo
			QueueWithRepNode<T> queue = new QueueWithRepNode<T>(element, 1);
			queue.next=null;
			//Si está vacia, creamos un nuevo nodo con el frente
			if(!contains(element)) {
				if(front==null) {
					front=queue;
					count++;
				}else {
					//Creamos un nuevo nodo...
					QueueWithRepNode<T> aux = front;
					count++;
					while(aux.next!=null) {
						aux=aux.next;
					}
					//... y añadimos el nuevo nodo como ultimo
					aux.next=queue;
				}
			}
		}else {
			throw new NullPointerException();
		}
	}
	
	@Override
	public void add(T element, int times) {
		int i=0;
		if(element!=null) {
			if(times>=0) {
				QueueWithRepNode<T> queue = new QueueWithRepNode<T>(element, times);
				queue.next=null;
				if(front==null) {
					front=queue;
					count++;
				}else {
					QueueWithRepNode<T> aux = front;
					if(contains(element)==true) {
						while(i<this.count) {
							if(aux.elem.equals(element)) {
								aux.num=aux.num+times;
							}
							aux=aux.next;
							i++;
						}
					}else {
						while(aux.next!=null) {
							aux=aux.next;
						}
						aux.next=queue;
						count++;
					}
					
				}
			}else {
				throw new IllegalArgumentException();
			}
		}else {
			throw new NullPointerException();
		}
	}


	@Override
	public void remove(T element, int times) {
		if(element!=null) {
			QueueWithRepNode<T> aux = front;
			if(contains(element)==true) {
				while(aux!=null) {
					if(aux.elem.equals(element)) {
						if(aux.num>=times && times>=0) {
							aux.num=aux.num-times;
						}else{
							throw new IllegalArgumentException();
						}
					}
					aux=aux.next;
				}
			}else{
				throw new NoSuchElementException();
			}
		}else {
			throw new NullPointerException();
		}
	}

	
	@Override
	public boolean contains(T element) {
		if(element!=null) {
			QueueWithRepNode<T> aux1=front;
			while(aux1!=null) {
				if(aux1.elem.equals(element)) {
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
	public long size() {
		int count=0;
		QueueWithRepNode<T> aux= front;
		while(aux!=null) {
			count+=aux.num;	
			aux=aux.next;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		if(this.count==0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int remove() throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("data");
		}
		
		int aux=front.num;
		if(this.count!=1) {
			front=front.next;
			count--;
		}else {
			clear();
		}
		
		return aux;
	}

	@Override
	public void clear() {
		front=null;
		count=0;
	}
	

	@Override
	public int count(T element) {
		int aux=0;
		if(element!=null) {
			QueueWithRepNode<T> aux1= front;
			while(aux1!=null) {
				if(aux1.elem.equals(element)) {
					aux=aux1.num;
				}
				aux1=aux1.next;
			}
		}else {
			throw new NullPointerException();
		}
		return aux;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedQueueWithRepIterator<T>(front);
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

	
	

}
