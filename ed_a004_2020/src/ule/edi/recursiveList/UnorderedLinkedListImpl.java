package ule.edi.recursiveList;

import java.util.NoSuchElementException;


public class UnorderedLinkedListImpl<T> extends AbstractLinkedListImpl<T> implements UnorderedListADT<T> {

	public UnorderedLinkedListImpl() {
		//	Vacía
	}
	
	@SafeVarargs
	public UnorderedLinkedListImpl(T ... v) {
		//	Añadir en el mismo orden que en 'v'
		for (T Vi : v) {
			addLast(Vi);
		}
	}
	
	@Override
	public void addFirst(T element) {
		if(element!=null) {
			Node<T> aux = new Node<T>(element);
			if(isEmpty()) {
				front=aux;
			}else {
				aux.next=front;
				front=aux;
			}
		}else {
			throw new NullPointerException();
		}
	}
	
	
	@Override
	public void addLast(T element) {
		if(element!=null) {
			Node<T> aux = new Node<T>(element);
			if(isEmpty()) {
				front=aux;
			}else {
				addLastRecursively(front,element);
			}
		}else {
			throw new NullPointerException();
		}
	}	
	
	private Node<T> addLastRecursively(Node<T> node, T element) {
		if(node==null) {
			node = new Node<T>(element);
		}else {
			node.next=addLastRecursively(node.next, element);
		}
		return node;
	}

	
	@Override
	public void addBefore(T element, T target) {
		Node<T> nuevo = new Node<T>(element);
		int count=0;
		if(element!=null) {
			if(contains(target)) {
				if(front.next==null) {
					nuevo.next=front;
					front=nuevo;
				}else {
					Node<T> aux = front;
					addBeforeRecursive(aux,nuevo,target,count);
				}
			}else {
				throw new NoSuchElementException();
			}
		}else {
			throw new NullPointerException();
		}
	}
	
	private Node<T> addBeforeRecursive(Node<T> aux ,Node<T> nuevo,T target,int count){
		if(count==0 && aux.elem.equals(target)) {
			nuevo.next=aux;
			return front=nuevo;
		}
		
		if(aux.next.elem.equals(target)) {
			nuevo.next=aux.next;
			return aux.next=nuevo;
		}else {
			return addBeforeRecursive(aux.next, nuevo, target,count++);
		}
	}

		
}
