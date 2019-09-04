package project1;

/*
 * Implements a BST
 */
public class BST {

	private BSTNode root;
	private int comparisons;

	/*
	 * Inserts student into the tree
	 */
	public void insert(Student data) {
		insert(data, root);
	}

	/*
	 * Recursive helper for inserting a student
	 */
	private void insert(Student data, BSTNode current) {
		if (current == null) { // if end
			root = new BSTNode(data);
		} else {
			// compare with current
			int compare = data.compareTo(current.getData());
			if (compare < 0) {
				// add to left if nothing at left
				if (current.getLeft() == null) {
					current.setLeft(new BSTNode(data));
				} else {
					// recursion
					insert(data, current.getLeft());
				}

			} else if (compare > 0) {
				// if no right node
				if (current.getRight() == null) {
					current.setRight(new BSTNode(data));
				} else {
					insert(data, current.getRight());
				}
			}
		}
	}
	
	/*
	 * Find the student with specified id
	 */
	public Student find(int id) {
		comparisons = 0;
		BSTNode current = root;
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
		BSTNode current = root;
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
		BSTNode current = root;
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

	private class BSTNode {
		private Student data;
		private BSTNode left;
		private BSTNode right;

		public BSTNode(Student data) {
			this.data = data;
		}

		public Student getData() {
			return data;
		}

		public BSTNode getLeft() {
			return left;
		}

		public BSTNode getRight() {
			return right;
		}

		public void setLeft(BSTNode left) {
			this.left = left;
		}

		public void setRight(BSTNode right) {
			this.right = right;
		}

	}

}
