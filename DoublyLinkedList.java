class DoublyLinkedList {

	private ListNode2 head = null;
	private int n = 0; // list size
	//add at start
	public void addFirst(Object e) {
		if (n == 0) {
			head = new ListNode2(e, null, head);
			n++;
			return;
		} else if (n != 0) {
			ListNode2 node = new ListNode2(e, null, head);
			head.prev = node;
			head = node;
			n++;
			return;
		}
	}
	//remove element at given position
	public void remove(int removePosition) {
		if ( n == 0) {
			System.out.println("error");
		}
		else if ( removePosition == 0 && n == 1) {
			head = null;
			n--;
			return;
		}
		else if (removePosition == 0) {
			head = head.next;
			head.prev = null;
			n--;
			return;
		}
		else if ( removePosition == n-1) {
			ListNode2 node = head;
			for (int i = 0; i < removePosition - 1; i++)
				node = node.next;
			node.next = null;
			n--;
			return;
		}
		ListNode2 node = head;
		for (int i = 0; i < removePosition - 1; i++) {
			node = node.next;
		}
		node.next = node.next.next;
		node.next.prev = node;
		n--;
		return;
	}
	
	//insert in given position
	public void insert(Object o, int insertPosition) {
		if (n == 0) {
			addFirst(o);
			return;
		}
		else if (n == 1) {
			ListNode2 node = head;
			node.next = new ListNode2(o, node, null);
			n++;
			return;
		}
		if (insertPosition == n) {
			ListNode2 node = head;
			for (int j = 0; j < insertPosition - 1; j++) {
				node = node.next;
			}
			node.next = new ListNode2(o, node, null);
			n++;
			return;
		}
		ListNode2 node = head;
		for (int i = 0; i < insertPosition - 1; i++) {
			node = node.next;
		}
		node.next = new ListNode2(o, node, node.next);
		node.next.next.prev = node.next;
		n++;
		return;
		
	}
	
	//get from given position
	public Object get(int getPosition) {
		ListNode2 node = head;
		for (int i = 0; i < getPosition; i++) {
			node = node.next;
		}
		return node.element;
	}

	/**
	 * Prints out the elements in the list from the first to the last (front to
	 * back) and then from the last to the first (back to front). This is useful to
	 * test whether the list nodes are connected correctly with next and prev
	 * references.
	 */
	public void print() {
		// no elements to print for empty list
		if (head == null) {
			System.out.println("list empty.");
			return;
		}

		// follow next references to list elements from the front to the back of the
		// list
		System.out.print("front to back: ");
		ListNode2 node = head;
		System.out.print(node.element + " ");
		while (node.next != null) {
			node = node.next;
			System.out.print(node.element + " ");
		}

		// follow prev references to list elements from the back to the front of the
		// list
		System.out.print("-- and back to front: ");
		while (node != null) {
			System.out.print(node.element + " ");
			node = node.prev;
		}
		System.out.println();
	}
}