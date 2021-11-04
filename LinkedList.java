public class LinkedList {
	private ListNode head = null;
	private int n = 0; // list size

	public void addFirst(Object o) {
		head = new ListNode(o, head);
		tail = head;
		n++;
	}

	private ListNode tail = null;

	public void add(Object o) {
		if (n == 0) {
			head = new ListNode(o, head);
			tail = head;
			n++;
		} else {

			ListNode node = tail;
			node.next = new ListNode(o, node.next);
			tail = node.next;
			n++;
		}
	}

	public Object get(int i) {
		if (i < 0 || i >= n) {
			System.err.println("error");
		}
		ListNode node = head;
		for (int j = 0; j < i; j++)
			node = node.next;
		return node.element;
	}

	public void insert(Object o, int i) {
		if (i < 0 || i > n) {
			System.err.println("error");
		}
		if (i == n) {
			ListNode node = tail;
			node.next = new ListNode(o, node.next);
			tail = node.next;
			n++;
			return;
		}
		if (i == 0) {
			addFirst(o);
			return;
		}
		ListNode node = head;
		for (int j = 0; j < i - 1; j++)
			node = node.next;
		node.next = new ListNode(o, node.next);
		n++;
	}

	public void remove(int i) {
		if (i < 0 || i >= n) {
			System.err.println("error");
		}
		if (i == n - 1) {
			ListNode node = head;
			for (int j = 0; j < n - 2; j++) {
				node = node.next;
			}
			tail = node;
			node.next = null;
			n--;
			return;
		}
		if (i == 0) { // special case
			head = head.next;
			n--;
			return;
		}
		ListNode node = head;
		for (int j = 0; j < i - 1; j++)
			node = node.next;
		node.next = node.next.next;
		n--;
	}
}