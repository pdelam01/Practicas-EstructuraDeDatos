package ule.edi.queuewithrep;


import java.util.Iterator;

import ule.edi.exceptions.EmptyCollectionException;
	
	/**
	 * TAD 'QueueWithRep'
	 * 
	 * Almacena un conjunto de objetos de tipo <code>T</code>, pero cuando varios objetos iguales (según su  
	 * método {@link #equals(Object)}) se añaden a la colección, se mantiene una única referencia al objeto 
	 * y un contador de duplicados.
	 * 
	 * Ejemplos de aplicación de un TAD como éste podría ser llevar el
	 * inventario de un personaje en un juego:
	 * 
	 * 	("Cloth"(10), "Gold"(5), "Silver Key"(2))
	 * 
	 * o almacenar información sobre una caja registradora:
	 * 
	 * 	("5€"(2), "0.5€"(20), "20€"(1))
	 * 
	 * 
	 * Se deben considerar hasta 2^31 - 1 instancias de un mismo elemento,
	 * por lo que se usará un <code>int</code> para almacenar ese dato.
	 * 
	 * El tamaño total de la cola {@link QueueWithRep#size()} será un resultado
	 * de tipo <code>long</code>.
	 * 
	 * En cualquier caso, para simplificar la práctica se supondrá que no es necesario hacer comprobaciones de 
	 * rangos. Si fuera necesario, se dispone de {@link Integer#MAX_VALUE} y {@link Long#MAX_VALUE}.
	 * 
	 * 
	 * Excepciones
	 * 
	 * No se permiten elementos <code>null</code>. Si a cualquier método que recibe un elemento se le pasa el 
	 * valor <code>null</code>, lanzará una excepción {@link NullPointerException}.
	 * 
	 * Los valores de parámetros <code>times</code> deben ser mayores o iguales a cero, nunca negativos. Si se 
	 * recibe un valor negativo se lanzará {@link IllegalArgumentException}.
	 * 
	 * Ambas son ejemplos de "unchecked exceptions"
	 * {@link http://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html}
	 * 
	 * 
	 * Constructores
	 * 
	 * Se definirá un constructor por defecto que inicialice la instancia
	 * como cola vacía.
	 * 
	 * 
	 * Métodos {@link Object#equals(Object)} y {@link Object#hashCode()}
	 * 
	 * No se definirán estos métodos. Ver la clase QueueWithRepS en el proyecto para la igualdad.
	 * 
	 * Método {@link Object#toString()}
	 * 
	 * El formato es libre, pero debe reflejar qué elementos y cuántos de cada uno hay en la cola. Recordad que 
	 * Eclipse usará este método en sesiones de depuración.
	 * 
	 *
	 * Iterador
	 * 
	 * El iterador se desplaza por todos los elementos de la cola, uno a uno.
	 * 
	 * Por ejemplo, con una cola
	 * 
	 * 	("A"(2), "B"(5))
	 * 
	 * el método {@link Iterator#next()} devolverá uno tras otro
	 * 
	 * 	"A", "A", "B", "B", "B", "B", "B"
	 * 
	 * @author profesor
	 *
	 * @param <T> tipo de elementos en la cola
	 */
	public interface QueueWithRep<T> extends Iterable<T> {

		/**
		 * Añade varias instancias de un elemento a esta cola
		 * 
		 * Si una cola contiene ("XYZ"(1), "123"(5)), y se añaden seis instancias de "ABC", se pasará a 
	           * tener una cola con ("ABC"(6), "XYZ"(1), "123"(5)). Si ahora se añaden dos instancias de 
	           * "123", se tendrá ("ABC"(6), "XYZ"(1), "123"(7)).
		 * 
		 * @param element el elemento a añadir
		 * @param times el número de instancias
		 * 
		 * @throws NullPointerException el elemento indicado es <code>null</code>
		 * @throws IllegalArgumentException si <code>times</code> fuera negativo
		 */
		public void add(T element, int times);
		
		/**
		 * Añade una instancia de un elemento a esta cola
		 * 
		 * @param element el elemento a añadir
		 * 
		 * @throws NullPointerException el elemento indicado es <code>null</code>
		 */
		public void add(T element);
		
		
		/**
		 * Saca varias instancias de un elemento de esta cola
		 * 
		 * Por ejemplo, al sacar dos instancias de "ABC" de una
		 * cola ("ABC"(10), "123"(2)), ésta pasará a ser
		 * ("ABC"(8), "123"(2)).
		 * 
		 * Se admite solamente un número de instancias menor del que hay
		 * en la cola; e.g. si inicialmente se tiene ("ABC"(2))
		 * y se sacan 8 instancias de "ABC", se queda como estaba, y lanza
		 * la excepción IllegalArgumentException
		 * 
		 * @param element elemento a sacar de esta cola
		 * @param times número de instancias a sacar
		 * 
	     * @throws NullPointerException el elemento indicado es <code>null</code>
         * @throws NoSuchElementException el elemento indicado  <code>no está en la cola</code>
      	 * @throws IllegalArgumentException si <code>times</code> fuera negativo o mayor o igual que 
               *.         el número de apariciones del elemento
			 */
		public void remove(T element, int times);
		
		/**
		 * Saca el primer elemento completo de esta cola
		 * 
		 * 
		 * @throws EmptyCollectionException si la cola está vacía
		 * @return el numero de apariciones que había en la cola del primer elemento (que elimina)
		 */
		public int remove() throws EmptyCollectionException;
		
		
		/**
		 * Elimina todo el contenido de esta cola
		 */
		public void clear();
		
		
		/**
		 * Indica si el elemento está en esta cola
		 * 
		 * Devuelve <code>true</code> si al menos existe una
		 * instancia del elemento dado en esta cola (es decir,
		 * un elemento 'x' tal que <code>x.equals(element)</code>)
		 * y <code>false</code> en caso contrario.
		 * 
		 * @param element elemento a buscar en esta cola
		 * @return <code>true</code>/<code>false</code> según el resultado
		 * 
		 * @throws NullPointerException el elemento indicado es <code>null</code>
		 */
		public boolean contains(T element);
		
		/**
		 * Indica si esta cola está vacía
		 * 
		 * @return <code>true</code> si no contiene elementos
		 */
		public boolean isEmpty();
		
		/**
		 * Devuelve el número total de instancias en esta cola
		 * 
		 * Por ejemplo, para una cola ("5��"(2), "10��"(8)) se 
		 * devolverá un tamaño de 2 + 8 = 10.
		 * 
		 * Data: defecto 10, aunmenta *2
		 * Count: seria dos(num instancias)
		 * Size: lo que pone arriba
		 * Num: va a ser el numero de repetidos tiene (2, 8)
		 * Elem: objeto (5 y 10)
		 * 
		 * 
		 * @return número total de instancias en esta cola
		 */
		public long size();

		/**
		 * Devuelve el numero de instancias del elemento dado
		 * 
		 * Es decir, el numero de instancias del objeto 'x' tal que
		 * <code>x.equals(element)</code>. Por ejemplo, con una cola
		 * B1 = (AX(2), BX(8)) se indicara 8 si se pregunta por
		 * <code>B1.count(BX)</code>.
		 * 
		 * Si el elemento no esta, se devuelve cero.
		 * 
		 * @param element el elemento a buscar en esta cola
		 * @return el número de instancias que se encuentran
		 * 
		 * @throws NullPointerException el elemento indicado es <code>null</code>
		 */
		public int count(T element);
		
	}

	
