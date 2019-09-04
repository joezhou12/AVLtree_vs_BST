package project1;

//Represents a student
public class Student implements Comparable<Student>{

	// student details
	private int studentId;
	private String name;
	private double gpa;
	
	// constructor
	public Student(int studentId, String name, double gpa) {
		this.studentId = studentId;
		this.name = name;
		this.gpa = gpa;
	}

	// Getters
	
	public int getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}

	public double getGpa() {
		return gpa;
	}

	
	// compare students
	@Override
	public int compareTo(Student o) {
		return this.studentId - o.studentId;
	}
	
	// string format
	@Override
	public String toString() {
		return String.format("%d - %s (GPA: %s)", studentId, name, gpa);
	}
	
	
}

