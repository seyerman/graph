package collections;

public class Stack <T> implements ICollection<T>{
	private java.util.Stack<T> myStack;
	
	public Stack() {
		myStack = new java.util.Stack<T>();
	}

	@Override
	public void add(T element) {
		myStack.push(element);
	}

	@Override
	public T poll() {
		return myStack.pop();
	}

	@Override
	public T peek() {
		return myStack.peek();
	}

	@Override
	public boolean isEmpty() {
		return myStack.empty();
	}
	
}
