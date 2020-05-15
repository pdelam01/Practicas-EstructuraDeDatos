package ule.edi.recursiveList;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.recursiveList.AbstractLinkedListImpl.Node;

public class OrderedLinkedListImpl<T extends Comparable<? super T>> extends
		AbstractLinkedListImpl<T> implements OrderedListADT<T> {

	public OrderedLinkedListImpl() {
		// Vacía
	}

	@SafeVarargs
	public OrderedLinkedListImpl(T... v) {
		// Añade todos los elementos del array 'v'
		for (T Vi : v) {
			add(Vi);
		}
	}

	@Override
	public void add(T element) {
		if(element!=null) {
			Node<T> aux = new Node<T>(element);

			if(isEmpty()) {
				front=aux;
			}else {
				addRecursive(front,null,aux,1);
			}
		}else {
			throw new NullPointerException();
		}
	}
	
	private void addRecursive(Node<T> actual, Node<T> prev, Node<T> element,int count) {
		if(actual!=null) {
			if(element.elem.compareTo(actual.elem)<=0) {
				if(count==1) {
					element.next=front;
					front=element;
				}else {
					prev.next=element;
					element.next=actual;
				}
			}else {
				prev=actual;
				actual=actual.next;
				count++;
				addRecursive(actual, prev, element,count);
			}
		}else {
			prev.next=element;
			element.next=actual;
			count++;
		}
	}

	@Override
	public int removeDuplicates() throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("data");
		}else {
			return removeDuplicatesRecursive(front,front,0);
		}
	}

	private int removeDuplicatesRecursive(Node<T> node,Node<T> nodeAux,int count) {
		int times=0;
		if(node!=null) {
			times=count(node.elem);
			if(times>1) {
				times--;
				node.next=node.next.next;
				count=1+removeDuplicatesRecursive(node, nodeAux.next, count);
			}else {
				count=removeDuplicatesRecursive(node.next, node.next, count);
			}
		}
		return count;
		
	}
}
