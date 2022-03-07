public class LinkedList<Type> {
	/*************
	* ATTRIBUTES *
	*************/
	public static final int MAX_SIZE = 100;
	private Node<Type> head;
	private Node<Type> current;
	private Node<Type> tail;
	private int numElements;

	/***************
	* CONSTRUCTORS *
	***************/

	// Newly constructed lists have no nodes, so head and tail point to null
	public LinkedList() {
		head = null;
		current = null;
		tail = null;
		numElements = 0;
	}

	// Copy constructor
	public LinkedList(LinkedList<Type> l) {
		head = null;
		current = null;
		tail = null;
		numElements = 0;

		l.first();
		for (int i = 0; i < l.size(); i++) {
			add(l.getCurrent());
			l.next();
		}
		l.first();
	}

	/**********
	* METHODS *
	**********/

	// Returns a String representation of the list ("NULL" if empty)
	public String toString() {
		// Return "NULL" if the list is empty
		if (head == null) {
			return "NULL";
		}
		else {
			// Initialize the string representation of the linked list
			String s = "";

			// Start traversing the list at the head
			Node<Type> temp = head;

			// Iterate through the list and concatenate each element to the string
			// Separated each element by a space
			while (temp != null) {
				s += temp.getData() + " ";
				temp = temp.getLink();
			}

			return s;
		}
	}

	// Returns the number of elements in the list (not the max capacity)
	public int size() {
		return numElements;
	}

	// Returns true if there are no elements in the list
	public boolean isEmpty() {
		return (numElements == 0);
	}

	// Returns true if the number of elements in the list is equal to MAX_SIZE
	public boolean isFull() {
		return (numElements == MAX_SIZE);
	}

	// Set the current Node reference to the head node
	public void first() {
		current = head;
	}

	// Set current to the next node in the list
	public void next() {
		current = (current != null) ? current.getLink() : current;
	}

	// Return the element at the current node
	public Type getCurrent() {
		return (current != null) ? current.getData() : null;
	}

	// Adds the specified element to the end of the list.
	// Not possible for a full list.
	public void add(Type element) {
		// Don't add anything if the list is full
		if (!isFull()) {
			// If the list is empty, the new node becomes the head
			if (isEmpty()) {
				head = new Node<Type>();
				head.setData(element);
				current = head;
				tail = head;
			}

			// If the list is not empty, the new node is added to the end of the list
			else {
				tail.setLink(new Node<Type>());
				tail = tail.getLink();
				tail.setData(element);
			}

			// Increment the number of elements
			numElements++;
		}
	}

	// Adds the specified element to the list at the given index
	// Not possible for a full list
	public void add(int index, Type element) {
		// Don't add anything if the list is full or the index is out of bounds
		if (!(isFull() || index < 0 || index > numElements)) {
			// If the list is empty, it only works if the index is 0
			if (isEmpty() && index == 0) {
				head = new Node<Type>();
				head.setData(element);
				current = head;
				tail = head;
				numElements++;
			}

			// If the node is being inserted at the beginning of the list,
			// link the new node to head and update head
			else if (index == 0) {
				Node<Type> node = new Node<Type>();
				node.setData(element);
				node.setLink(head);
				head = node;
				numElements++;
			}

			// If the node is being inserted at the end of the list,
			// call the other add method
			else if (index == numElements) {
				add(element);
			}

			// If an element already exists at the given index, insert a new node between existing nodes
			else {
				Node<Type> trail = null;
				Node<Type> temp = head;
				int i = 0;

				// Traverse the list starting at the head until index is reached
				while (i < index) {
					trail = temp;
					temp = temp.getLink();
					i++;
				}

				// Insert the new node
				Node<Type> node = new Node<Type>();
				node.setData(element);
				trail.setLink(node);
				node.setLink(temp);
				numElements++;
			}
		}
	}

	// Returns the value in the node at the given index
	public Type get(int index) {
		// Don't traverse the list if the index is out of bounds
		if (!(index < 0 || index >= numElements)) {
			Node<Type> temp = head;
			int i = 0;

			// Traverse the list starting at the head until index is reached
			while (i < index) {
				temp = temp.getLink();
				i++;
			}

			return temp.getData();
		}
		else {
			return null;
		}
	}

	// Returns the value in the head node
	public Type getFirst() {
		if (head != null) {
			return head.getData();
		}
		else {
			return null;
		}
	}

	// Returns the value in the tail node
	public Type getLast() {
		if (tail != null) {
			return tail.getData();
		}
		else {
			return null;
		}
	}

	// Removes the element at the specified index.  Not possible for an empty list
	public void remove(int index) {
		// Don't traverse the list if the index is out of bounds or the list is empty
		if (!isEmpty() && !(index < 0 || index >= numElements)) {
			// If the index is 0, set the head to its link
			if (index == 0) {
				head = head.getLink();
			}

			else {
				Node<Type> trail = null;
				Node<Type> temp = head;
				int i = 0;

				// Traverse the list starting at the head until index is reached
				while (i < index) {
					trail = temp;
					temp = temp.getLink();
					i++;
				}

				// Remove the node at index
				trail.setLink(temp.getLink());
			}

			numElements--;
		}
	}

	// Replaces the element at the specified index with the given element
	public void set(int index, Type element) {
		// Don't traverse the list if the index is out of bounds or the list is empty
		if (!isEmpty() && !(index < 0 || index >= numElements)) {
			Node<Type> temp = head;
			int i = 0;

			// Traverse the list starting at the head until the index is reached
			while (i < index) {
				temp = temp.getLink();
				i++;
			}

			// Replace the element at the index with the given element
			temp.setData(element);
		}
	}

	// Returns the index of the first occurrence of the specified element.
	// Returns -1 if the list does not contain the element.
	public int indexOf(Type element) {
		// Don't traverse the list if it is empty
		if (!isEmpty()) {
			Node<Type> temp = head;
			int i = 0;

			// Traverse the list starting at the head until the element is found or the end of the list is reached
			while (!temp.getData().equals(element) || temp == null) {
				temp = temp.getLink();
				i++;
			}

			if (temp != null) {
				return i;
			}
			else {
				return -1;
			}
		}
		else {
			return -1;
		}
	}

	// Bonus
	// Returns an array of the elements in the linked list
	public Type[] toArray(Type[] array) {
		if (array.length == numElements) {
			Node<Type> temp = head;
			for (int i = 0; i < numElements; i++) {
				array[i] = temp.getData();
				temp = temp.getLink();
			}
			return array;
		}
		else {
			return null;
		}
	}
}