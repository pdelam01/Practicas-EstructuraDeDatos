package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class ArrayQueueWithRepImpl<T> implements QueueWithRep<T> {
	
	// atributos
	
    private final int capacityDefault = 10;
	
	ElemQueueWithRep<T>[] data;
    private int count;
    
	// Clase interna 
    
	@SuppressWarnings("hiding")
	public class ElemQueueWithRep<T> {
		T elem;
		int num;
		public ElemQueueWithRep (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
	}

	
	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class ArrayQueueWithRepIterator<T> implements Iterator<T> {
		private int count; // total de numeros en la coleccion
		private int current; //poscion actual del iterador
		private ElemQueueWithRep<T>[] items;
		int veces;
	
		public ArrayQueueWithRepIterator(ElemQueueWithRep<T>[] cola, int count){
			items= cola;
			this.count = count;
			current = 0;
			veces=0;
			
		}

		@Override
		public boolean hasNext() {
			//true si lo que esta en el return se cumple
			return (current < count); 
		}

		@Override
		public T next() {
			if (!hasNext()) {
				 throw new NoSuchElementException();
			}
			
			//De esta manera result está apuntando al primer elemento
			T result=(T)data[current].elem;
			//Dejamos veces "apuntando" al siguiente
			veces++;
			
			//En el caso en el que sean los mismo, reiniciamos veces y pasamos al siguiente elemento
			if(veces==data[current].num) {
				//Aumentamos el current (la posicion del iterador)
				current++;
				//Cuando apunta al siguiente elemento, lo reiniciamos a 0
				veces=0;
			}
			return result; 	
		}
		
	}
	////// FIN ITERATOR
	
	
    // Constructores

	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl() {
		data =  new ElemQueueWithRep[capacityDefault];
		count=0;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl(int capacity) {
		data =  new ElemQueueWithRep[capacity];
		count=0;
	}
	
	/*
	 * Creamos un nuevo array que contiene los datos almacenados en
	 * data, con el tamaño doble
	 */
	 @SuppressWarnings("unchecked")
	 private void expandCapacity() {
			ElemQueueWithRep<T>[] nuevo= (ElemQueueWithRep<T>[]) new ElemQueueWithRep[data.length*2];
			for(int i=0;i<data.length;i++) {
				nuevo[i]=data[i];
			}
			//Con esto hacemos que data y nuevo apunten a la misma dirreccion, volcamos todo lo de data a nuevo
			data=nuevo;
		}
	 
	
			@Override
			public void add(T element, int times) {
				if(element!=null) {
					if(times>0) {
						if(contains(element)==true) {
							for(int i=0;i<this.count;i++) {
								if(data[i].elem.equals(element)) {
									data[i].num+=times;
								}
							}
						}else {
							ElemQueueWithRep<T> eq = new ElemQueueWithRep<T>(element, times);
							data[count]=eq;
							count++;
						}
					}else {
						throw new IllegalArgumentException();
					}
				}else {
					throw new NullPointerException();
				}
			}
			
	 
	 
			@Override
			public void add(T element) {
				if(element!=null) {
					if(!(contains(element))) {
						if(size()==data.length) {
							expandCapacity();
						}
						ElemQueueWithRep<T> eq = new ElemQueueWithRep<T>(element,1);
						data[count]=eq;
						count++;
					}
				}else {
					throw new NullPointerException();
				}
			}

			@Override
			public void remove(T element, int times) {
				if(element!=null) {
					if(contains(element)==true) {
						for(int i=0;i<this.count;i++) {
							if(data[i].elem.equals(element)) {
								if(data[i].num>times && times>=0) {
									data[i].num=data[i].num-times;
								}else {
									throw new IllegalArgumentException();
								}
							}
						}
					}else {
						throw new NoSuchElementException();
					}
				}else {
					throw new NullPointerException();
				}
			}

			@Override
			public int remove() throws EmptyCollectionException {
				if(isEmpty()) {
					throw new EmptyCollectionException("data");
				}
				
				int aux=data[0].num;
				count--;
				//El contador estará apuntando a la primera posicion	
				for(int i=0;i<this.count;i++) {
					data[i]=data[i+1];
				}
				data[count]=null;
				return aux;
			}

			@Override
			public void clear() {
				for(int i=0;i<this.count;i++) {
					data[i]=null;
				}
				count=0;
			}
			

			@Override
			public boolean contains(T element) {
				boolean search=false;
				if(element!=null) {
					for(int i=0;i<this.count && !search;i++) {
						if(data[i].elem.equals(element)) {
							search=true;
						}
					}
				}else {
					throw new NullPointerException();
				}
				return search;
			}

			@Override
			public boolean isEmpty() {
				//Si se cumple=true, si no false
				return(count==0);	
			}

			@Override
			public long size() {
				int count=0;
				for(int i=0;i<this.count;i++) {
					count+=data[i].num;
				}
				return count;
			}

			@Override
			public int count(T element) {
				int aux=0;
				if(element!=null) {
					for(int i=0;i<this.count;i++) {
						if(data[i].elem.equals(element)) {
							aux=data[i].num;
						}
					}
				}else {
					throw new NullPointerException();
				}
				return aux;
			}

			@Override
			public Iterator<T> iterator() {
				return new ArrayQueueWithRepIterator<T>(data,count); 
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
