public class Queue<Type> extends LinkedList<Type>{

	// adds an element to the end of the linked list (the back of the queue)
	public void enqueue(Type element){
		add(element);
	}

	// removes and returns the element at the front of the queue (or the beginning of the linked list)
	public Type dequeue(){
		Type element = getFirst();
		remove(0);
		return element;
	}

	// Return the elemnent at the front of the queue, but does not remove it
	public Type peek(){
		Type element = getFirst();
		return element;
	}
}