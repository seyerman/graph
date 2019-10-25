package collections;

import java.util.Collection;

/**
 * An alternative Collections Interface that will be used among different implementations of IGraph. As of now, no class implements this interface.
 * @author AED Third Group - Universidad ICESI - 2019-2
 * @version 1.0 - 10/2019
 * @param <T> The type of the elements to be stored in this collection.
 */
public interface ICollection<T> {
	
	/**Adds a given element in the collection.
	 * @param element The element to be added in the collection. 
	 */
	public void add(T element);
	
	/**
	 * Retrieves and removes the first element in a data structure. First element should be defined by the implementor data structure.
	 * @return The element removed from the data structure.
	 */
	public T poll();
	
	/**
	 * Retrieves, but does not remove, the first element in the data structure. First element should be defined by the implementor data structure.
	 * @return The element removed from the data structure. 
	 */
	public T peek();
	
	/**
	 * Determines whether a Collection is empty or not.
	 * @return True if this Collection has no items in it, false if not.
	 */
	public boolean isEmpty();
	
	public void addAll(Collection<T> c);
}
