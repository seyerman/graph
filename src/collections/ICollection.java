package collections;

public interface ICollection<T> {
	public void add(T element);
	public T poll();
	public T peek();
	public boolean isEmpty();
}
