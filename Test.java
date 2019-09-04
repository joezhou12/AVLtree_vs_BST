package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		//student tests file
		final String FILE_NAME = "student2.txt";

		// read students
		Student[] students = readStudents(FILE_NAME);

		// create trees
		BST bst = new BST();
		AvlTree avlTree = new AvlTree();

		// insert to trees
		for (int i = 0; i < students.length; i++) {
			bst.insert(students[i]);
			avlTree.insert(students[i]);
		}

		boolean quit = false;

		Scanner scanner = new Scanner(System.in);

		while (!quit) {
			// show menu
			System.out.println("1. Search a student by id");
			System.out.println("2. Find student with min id");
			System.out.println("3. Find student with max id");
			System.out.println("4. Quit");
			System.out.print(" >> ");

			int choice = scanner.nextInt();

			System.out.println();

			switch (choice) {
			case 1: // find with id
				System.out.print("Enter student id: ");
				int id = scanner.nextInt();

				Student resultBST = bst.find(id);
				Student resultAVL = avlTree.find(id);

				System.out.println("Student in BST: " + resultBST);
				System.out.println("Student in AVL: " + resultAVL);
				System.out.println("Number of comp in BST: " + bst.totalNodesVisited());
				System.out.println("Number of comp in AVL: " + avlTree.totalNodesVisited());
				break;

			case 2: // min id student
				resultBST = bst.minId();
				resultAVL = avlTree.minId();

				System.out.println("Student in BST: " + resultBST);
				System.out.println("Student in AVL: " + resultAVL);
				System.out.println("Number of comp in BST: " + bst.totalNodesVisited());
				System.out.println("Number of comp in AVL: " + avlTree.totalNodesVisited());
				break;

			case 3: // max id student
				resultBST = bst.maxId();
				resultAVL = avlTree.maxId();

				System.out.println("Student in BST: " + resultBST);
				System.out.println("Student in AVL: " + resultAVL);
				System.out.println("Number of comp in BST: " + bst.totalNodesVisited());
				System.out.println("Number of comp in AVL: " + avlTree.totalNodesVisited());
				break;
				
			case 4: 
				quit = true;
				break;

			default:
				break;
			}

			System.out.println();
		}

		scanner.close();
	}

	// read students from file
	private static Student[] readStudents(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));

		int count = scanner.nextInt();
		Student[] students = new Student[count];

		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(scanner.nextInt(), scanner.next(), scanner.nextDouble());
		}

		scanner.close();

		return students;
	}
}
