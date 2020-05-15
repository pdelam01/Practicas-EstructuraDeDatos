package ule.edi.recursiveList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.xml.bind.TypeConstraintException;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.exceptions.TypeIsNotComparableExeception;


public class AbstractLinkedListImpl<T> implements ListADT<T> {

	// Estructura de datos, lista simplemente enlazada
	//
	// Este es el primer nodo de la lista
	protected Node<T> front = null;

	// Clase para cada nodo en la lista
	@SuppressWarnings("hiding")
	protected  class Node<T> {

		T elem;

		Node<T> next;

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

		@Override
		public String toString() {
			return "(" + elem + " )";
		}

	}
	
	@SuppressWarnings("hiding")
	private class IteratorImpl<T> implements Iterator<T> {
     // TODO Implementar el iterador normal
		private Node<T> current;

		public IteratorImpl(Node<T> nodo) {
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

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}


	// Ejemplos de ejercicios de recursividad
	//



	@Override
	public String toString() {
		if(size()==1) {
			return front.toString();
		}else {
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			return toStringRecursive(front,buffer);
		}
		
	}
	
	//Metodo toString() recursivo
	private String toStringRecursive(Node<T> node, StringBuffer buffer) {
		buffer.append(node.elem+" ");
		if(node.next!=null) {
			return toStringRecursive(node.next, buffer);
		}else {
			buffer.append(")");
			return buffer.toString();
		}	
	}
	
	
	@Override
	public boolean contains(T target) {
		if(target==null) {
			throw new NullPointerException();	
		}

		return containsRecursive(front,target);
	}
	
	//Metodo contains() recursivo
	private boolean containsRecursive(Node<T> node, T target) {
		if(node==null) {
			return false;
		}
		if(node.elem.equals(target)) {
			return true;
		}
		
		return containsRecursive(node.next, target);
	}

	
	@Override
	public int count(T element) {
		int count=0;
		return countRecursive(front,element,count);
	}
	
	//Metodo count() recursivo
	private int countRecursive(Node<T> node, T target, int count) {
		if(target==null) {
			throw new NullPointerException();	
		}
		
		if(node==null) {
			return count;
		}
		
		if(node.elem.equals(target)) {
			count++;
		}
		
		return countRecursive(node.next,target,count);
	}


	@Override
	public T getLast() throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("data");
		}
		
		return getLastRecursive(front);
	}
	
	//Metodo getLast() recursivo
	private T getLastRecursive(Node<T> node) {
		if(node.next==null) {
			return node.elem;
		}else {
			return getLastRecursive(node.next);
		}
	}

	
	@Override
	public boolean isOrdered() throws TypeIsNotComparableExeception{
		try {
			boolean buleano=true;
			if(isEmpty()) {
				return true;
			}else {
				return isOrderedRecursive(front,buleano);
			}
    	 
		} catch (ClassCastException e) {
			throw new TypeIsNotComparableExeception("data");
		}

	}
	
	private boolean isOrderedRecursive(Node<T> node, boolean comprobante) {
		if(node.next==null) {
			return comprobante;
		}else {
			comprobante=isOrderedElems(node.elem,node.next.elem);
			if(comprobante==true) {
				node=node.next;
				return isOrderedRecursive(node, comprobante);
			}else {
				return comprobante;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean isOrderedElems(T elemA, T elemB) {
		if(((Comparable<T>) elemA).compareTo(elemB) <=0) {
			return true;
		}else {
			return false;
		}
	}

	
	@Override
	public T remove(T element) throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("data");
		}else {
			if(element==null) {
				throw new NullPointerException();
			}else {
				if(!contains(element)) {
					throw new NoSuchElementException();
				}else {
					return removeRecursive(front,null,element,1);
				}
			}
		}
	}
	
	private T removeRecursive(Node<T> node,Node<T>prev,T element,int count) {
		if(node.elem.equals(element)) {
			if(count==1) {
				front=node.next;
				return node.elem;
			}else {
				if(size()==count) {
					prev.next=null;
					return node.elem;
				}else {
					prev.next=node.next;
					node.next=null;
					return node.elem;
				}
			}
		}else {
			count++;
			prev=node;
			node=node.next;
			return removeRecursive(node, prev, element,count);
		}
	}


	@Override
	public T removeLast(T element) throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("data");
		}else {
			if(element==null) {
				throw new NullPointerException();
			}else {
				if(!contains(element)) {
					throw new NoSuchElementException();
				}else {
					if(count(element)==1) {
						return remove(element);
					}else {
						Node<T> prev = null;
						Node<T> temp = null;
						return removeLastRecursive(front,prev,temp,element);
					}
				}
			}
		}
	}
	
	private T removeLastRecursive(Node<T> node, Node<T> prev, Node<T> temp,T element) {
		if(node==null) {
			T el = temp.elem;
			if(temp.next.next!=null) {
				temp.next=temp.next.next;
			}
			if(temp.next.next==null) {
				temp.next=null;
			}
			return el;
		}
		
		if(node.elem.equals(element)) {
			temp=prev;
		}
		prev=node;
		return removeLastRecursive(node.next, prev, temp, element);
		
	}

	@Override
	public boolean isEmpty() {
		if(front==null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int size() {
		return sizeRecursive(front);
	}
	
	//Metodo size() recursivo
	private int sizeRecursive(Node<T> node) {
		if(node==null) {
			return 0;
		}
		
		return 1+sizeRecursive(node.next);
	}

	@Override
	public T getFirst() throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("data");
		}
		
		return front.elem;
	}

	@Override
	public String toStringFromUntil(int from, int until) {
		if(from<=0 || until<=0 || from>until) {
			throw new IllegalArgumentException();
		}else {
			if(size()==1) {
				return front.toString();
			}else {
				StringBuffer buffer = new StringBuffer();
				buffer.append("(");
				return toStringFURecursive(front,buffer,from,until,1);
			}
		}
	}
	
	private String toStringFURecursive(Node<T> node,StringBuffer buffer, int from, int until,int pos) {
		if(node.next!=null) {
			if(pos>=from && pos<=until) {
				buffer.append(node.elem+" ");
				pos++;
				return toStringFURecursive(node.next, buffer,from,until,pos);
			}else {
				pos++;
				return toStringFURecursive(node.next, buffer,from,until,pos);
			}
		}else {
			if(pos==until) {
				buffer.append(node.elem+" ");
				buffer.append(")");
				return buffer.toString();
			}else {
				buffer.append(")");
				return buffer.toString();
			}
			
		}
	}


	@Override
	public String toStringReverse() {
		if(size()==1) {
			return front.toString();
		}else {
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			return toStringRecursive(toStringReverseRecursive(front),buffer);
		}
	}
	
	private Node<T> toStringReverseRecursive(Node<T> node) {
		if(node.next==null) {
			return node;
		}
		
		Node<T> rest = toStringReverseRecursive(node.next); //Reverse en parte restante (dividimos en dos partes)
		node.next.next=node;								//Linkeamos node(front) al ultimo de la parte rest
		node.next=null;										//Node (front) es ahora ultimo, borramos su next a null
		
		return rest;
	}

	@Override
	public int removeDuplicates() throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("data");
		}else {
			return removeDuplicatesRecursive(front,front,0);
		}
	}
	
	private int removeDuplicatesRecursive(Node<T> node, Node<T> nodeAux,int count) {
		int times;
		if(node!=null) {
			times=count(node.elem);
			if(times>1) {
				T eleme = node.elem;
				if(nodeAux.next.elem==eleme) {
					nodeAux.next=nodeAux.next.next;
					count=1+removeDuplicatesRecursive(node, nodeAux.next, count);
				}else {
					count=removeDuplicatesRecursive(node, nodeAux.next, count);
				}
			}else {
				count=removeDuplicatesRecursive(node.next, node.next, count);
			}
		}
		return count;
	}

	
	@Override
	public Iterator<T> iterator() {
		return new IteratorImpl<T>(front);
	}


}
