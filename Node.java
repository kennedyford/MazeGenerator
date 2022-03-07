class Node<Type> {
	private Type data;
	private Node<Type> link;

	// Newly constructed nodes have no data or link
	public Node(){
		data = null;
		link = null;
	}

	// Accessors for the node's data
	public Type getData(){
		return data;
	}

	// Mutator for the node's data
	public void setData(Type data){
		this.data = data;
	}

	// Accessor for the node's link
	public Node<Type> getLink(){
		return link;
	}

	// Mutator for the node's link
	public void setLink(Node<Type> link){
		this.link = link;
	}
}