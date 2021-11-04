class SkipList {
	private SkipListNode[] head;
	private int n = 0; // list size

	public SkipList() {
		head = new SkipListNode[5];
	}

	public void createTestList() {
		head[0] = new SkipListNode("Anne");
		head[0].designateNext(3);
		head[1] = head[0];
		head[2] = head[0];
		head[0].next[0] = new SkipListNode("Ben");
		head[0].next[1] = new SkipListNode("Charlie");
		head[0].next[2] = new SkipListNode("Ernie");
		SkipListNode benNode = head[0].next[0];
		SkipListNode charlieNode = head[0].next[1];
		SkipListNode ernieNode = head[0].next[2];
		benNode.designateNext(4);
		ernieNode.designateNext(4);
		charlieNode.designateNext(4);
		charlieNode.next[1] = ernieNode;
		benNode.next[0] = charlieNode;
		charlieNode.next[0] = new SkipListNode("Don");
		SkipListNode don = charlieNode.next[0];
		don.designateNext(4);
		don.next[0] = ernieNode;
		n = 5;
	}

	public void print() {
		if (n == 0) {
			System.err.println("list empty.");
			return;
		}
		
		SkipListNode node;
		int start = head.length - 1;
		while (head[start] == null) {
			start--;
		}
		for (int i = start; i > -1; i--) {
			node = head[i];
			while (!(node.next[i] == null)) {
				System.out.print(node.listElement + ", ");
				node = node.next[i];
			}
			System.out.println(node.listElement);
		}
	}

	public boolean inList(String searchString) {
		SkipListNode node;
		int nodePosition = head.length - 1;
		while (head[nodePosition] == null) {
			nodePosition--;
		}
		node = head[nodePosition];
		while (nodePosition > -1) {
			if (node.listElement.compareTo(searchString) == 0) {
				return true;
			} else if (node.next[nodePosition] != null) {
				if (node.next[nodePosition].listElement.compareTo(searchString) > 0) {
					nodePosition--;
					if (nodePosition < 0) {
						break;
					} else {
						node = head[nodePosition];
					}
				} else if (node.next[nodePosition].listElement.compareTo(searchString) <= 0) {
					node = node.next[nodePosition];
				}
			} else {
				nodePosition--;
				if (nodePosition < 0) {
					break;
				} else {
					node = head[nodePosition];
				}
			}
		}
		return false;
	}

	public void insert(String insertString) {

		SkipListNode newNode = new SkipListNode(insertString);
		int lanes = newNode.next.length - 1;
		if (n == 0) {
			for (int i = lanes; i > -1; i--) {
				head[i] = newNode;
				n++;
			}
			return;
		}
		n++;
		while (head[lanes] == null) {
			head[lanes] = newNode;
			newNode.next[lanes] = null;
			lanes--;
		}

		while (head[lanes] == null) {
			SkipListNode node = head[lanes];
			head[lanes] = newNode;
			newNode.next[lanes] = node;
			lanes--;
		}
		if (insertString.compareTo(head[lanes].listElement) < 0) {
			SkipListNode node = head[lanes];
			head[lanes] = newNode;
			newNode.next[lanes] = node;
			for (int i = lanes - 1; i > -1; i--) {
				node = head[i];
				head[i] = newNode;
				newNode.next[i] = node;
			}
			return;
		}
		boolean lastElement = false;
		SkipListNode node = head[lanes];
		for (int i = lanes; i > -1; i--) {
			node = head[lanes];
			if (node.next[i] != null) {
				while (insertString.compareTo(node.next[i].listElement) > 0) {
					node = node.next[i];
					if (node.next[i] == null)
						break;
				}
			} else if (node.next[i] == null) {
				node.next[i] = newNode;
				lastElement = true;
			}
			if (lastElement == false) {
				SkipListNode node2 = node.next[i];
				node.next[i] = newNode;
				newNode.next[i] = node2;
			}
			lastElement = false;
		}
	}

	public static void main(String[] args) {
		SkipList skipListExample = new SkipList();
		//SkipList emptySkipListExample = new SkipList();
		skipListExample.createTestList();
		System.out.println("part a.");
		skipListExample.print();
		//emptySkipListExample.print();
		System.out.println("\npart b.");
		System.out.println("checking for Anne: " + (skipListExample.inList("Anne")));
		System.out.println("checking for Ben: " + (skipListExample.inList("Ben")));
		System.out.println("checking for Charlie: " + (skipListExample.inList("Charlie")));
		System.out.println("checking for invalid list element: " + (skipListExample.inList("invalid list element")));
		System.out.println("checking for Don: " + (skipListExample.inList("Don")));
		System.out.println("checking for Ernie: " + (skipListExample.inList("Ernie")));
		System.out.println("\npart c.");
		SkipList skipListExample2 = new SkipList();
		skipListExample2.insert("Fred");
		skipListExample2.insert("Greg");
		skipListExample2.insert("Pete");
		skipListExample2.insert("Mike");
		skipListExample2.insert("Bobby");
		skipListExample2.insert("Craig");
		skipListExample2.insert("Anne");
		skipListExample2.print();
	}
}