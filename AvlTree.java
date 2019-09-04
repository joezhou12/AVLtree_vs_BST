package project1;

/*
 * Implements an AVL tree.
 */
public class AvlTree {

	private static final int ALLOWED_IMBALANCE = 1;

	/* The tree root. */
	private AvlNode root;
	private int comparisons;

	/*
	 * Construct the tree.
	 */
	public AvlTree() {
		root = null;
	}

	// insert into tree
	public void insert(Student x) {
		root = insert(x, root);
	}

	// balance the node
	private AvlNode balance(AvlNode t) {
		if (t == null)
			return t;

		if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
			if (height(t.left.left) >= height(t.left.right))
				t = rotateWithLeftChild(t);
			else
				t = doubleWithLeftChild(t);
		else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE)
			if (height(t.right.right) >= height(t.right.left))
				t = rotateWithRightChild(t);
			else
				t = doubleWithRightChild(t);

		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}

	/*
	 * Internal method to insert into a subtree.
	 */
	private AvlNode insert(Student x, AvlNode t) {
		if (t == null)
			return new AvlNode(x, null, null);

		int compareResult = x.compareTo(t.data);

		if (compareResult < 0)
			t.left = insert(x, t.left);
		else if (compareResult > 0)
			t.right = insert(x, t.right);
		return balance(t);
	}

	/*
	 * Return the height of node t, or -1, if null.
	 */
	private int height(AvlNode t) {
		return t == null ? -1 : t.height;
	}

	/*
	 * Rotate binary tree node with left child. For AVL trees, this is a single
	 * rotation for case 1. Update heights, then return new root.
	 */
	private AvlNode rotateWithLeftChild(AvlNode k2) {
		AvlNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	/*
	 * Rotate binary tree node with right child. For AVL trees, this is a single
	 * rotation for case 4. Update heights, then return new root.
	 */
	private AvlNode rotateWithRightChild(AvlNode k1) {
		AvlNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}

	/*
	 * Double rotate binary tree node: first left child with its right child; then
	 * node k3 with new left child. For AVL trees, this is a double rotation for
	 * case 2. Update heights, then return new root.
	 */
	private AvlNode doubleWithLeftChild(AvlNode k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	/*
	 * Double rotate binary tree node: first right child with its left child; then
	 * node k1 with new right child. For AVL trees, this is a double rotation for
	 * case 3. Update heights, then return new root.
	 */
	private AvlNode doubleWithRightChild(AvlNode k1) {
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}
	
	
	/*
	 * Find the student with specified id
	 */
	public Student find(int id) {
		comparisons = 0;
		AvlNode current = root;
		while(current != null) {
			comparisons++;
			if(current.getData().getStudentId() == id)
				return current.getData();
			else if(id < current.getData().getStudentId())
				current = current.getLeft();
			else
				current = current.getRight();
		}
		
		return null;
	}
	
	/*
	 * Find the student with maximum id
	 * (Rightmost student)
	 */
	public Student maxId() {
		comparisons = 0;
		AvlNode current = root;
		while(current != null) {
			comparisons++;
			
			if(current.getRight() == null)
				return current.getData();
			
			current = current.getRight();
		}
		
		return null;
	}
	
	/*
	 * Find the student with minimum id
	 * (Leftmost student)
	 */
	public Student minId() {
		comparisons = 0;
		AvlNode current = root;
		while(current != null) {
			comparisons++;
			
			if(current.getLeft() == null)
				return current.getData();
			
			current = current.getLeft();
		}
		
		return null;
	}
	
	
	public int totalNodesVisited() {
		return comparisons;
	}
	
	private static class AvlNode {

		private Student data;
		private AvlNode left, right;
		private int height;

		AvlNode(Student data, AvlNode lt, AvlNode rt) {
			this.data = data;
			left = lt;
			right = rt;
			height = 0;
		}

		public Student getData() {
			return data;
		}

		public AvlNode getLeft() {
			return left;
		}

		public AvlNode getRight() {
			return right;
		}

		public int getHeight() {
			return height;
		}
		
		

	}

}

