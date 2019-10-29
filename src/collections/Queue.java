/**
 * 
 */
package collections;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Own implementation of class Queue, to be used in several methods along Graph implementations.
 * @author AED Third Group - Universidad ICESI - 2019-2
 * @version 1.0 - 10/2019
 * @param T The data type that this queue will use.
 */
public class Queue<T> implements ICollection<T> {

	/**
	 * The representative Linked List (LL) of this queue.
	 */
	private LinkedList<T> rep;
	
	public Queue() {
		rep = new LinkedList<>();
	}
	
	/**
	 * Adds an element in the tail (last position of the representative LL) of this queue.
	 */
	@Override
	public void add(T element) {
		rep.addLast(element);
	}

	/**
	 * Retrieves and removes the head (first element of the representative LL) of this queue.
	 */
	@Override
	public T poll() {
		return rep.removeFirst();
	}

	/**
	 * Retrieves, but does not remove, the head (first element of the representative LL) of this queue.
	 */
	@Override
	public T peek() {
		return rep.getFirst();
	}

	/**
	 * Determines whether or not this queue is empty.
	 */
	@Override
	public boolean isEmpty() {
		return rep.isEmpty();
	}

	/**
	 * Adds all of the elements given in the collection to this queue, in the order they are given.
	 */
	@Override
	public void addAll(Collection<T> c) {
		rep.addAll(c);
	}
}
