import java.util.Random;

public class SkipListNode {
	public SkipListNode next[];
	public String listElement;

	public SkipListNode() {
	}

	public void designateNext(int nextSize) {
		next = new SkipListNode[nextSize];
	}
	
	public SkipListNode(String string) {
		listElement = string;
		Random r = new Random();
		int l = 1;
		while (r.nextFloat() < 0.5) {
			l++;
			if (l == 5) {
				break;
			}
		}
		next = new SkipListNode[l];
	}

	
}