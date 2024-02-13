package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
			//createMultipleStudents(studentDAO);
			//deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int numOfRows = studentDAO.deleteAll();
		System.out.println(numOfRows + " Students deleted");
		findAllStudents(studentDAO);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		studentDAO.deleteStudent(4);
		findAllStudents(studentDAO);
	}

	private void updateStudent(StudentDAO studentDAO) {
		Student student = findStudentsByLastName(studentDAO);
		if (student != null) {
			student.setEmail("georget@gmail.com");
			studentDAO.updateStudent(student);
			printStudent(student);
		}
	}

	private Student findStudentsByLastName(StudentDAO studentDAO) {
		String lastname = "Jane";
		List<Student> studentList = studentDAO.findByLastName(lastname);
		if (studentList.isEmpty()) {
			System.out.println("Student with last name : '" + lastname + "' not found");
			return null;
		} else {
			for (Student student :
					studentList) {
				printStudent(student);
			}
			return studentList.get(0);
		}
	}

	private void findAllStudents(StudentDAO studentDAO) {
		List<Student> studentList = studentDAO.findAll();
		if (studentList.isEmpty()) {
			System.out.println("Students not found");
		} else {
			for (Student student :
					studentList) {
				printStudent(student);
			}
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		Student student = studentDAO.findById(2);
		System.out.println("Found Student");
		printStudent(student);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		Student student1 = new Student("John", "Doe", "johnd@gmail.com");
		Student student2 = new Student("Peter", "Parker", "peterp@gmail.com");
		Student student3 = new Student("Mary", "Jane", "maryj@gmail.com");

		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
		System.out.println("Saved Student. Generated id: " + student1.getId());
		System.out.println("Saved Student. Generated id: " + student2.getId());
		System.out.println("Saved Student. Generated id: " + student3.getId());
	}

	private void createStudent(StudentDAO studentDAO) {
		Student student = new Student("George", "Tsiga", "george@gmail.com");
		studentDAO.save(student);
		System.out.println("Saved Student. Generated id: " + student.getId());
	}

	private void printStudent(Student student) {
		System.out.println("-------------------------------");
		System.out.println("Name:      " + student.getFirstName());
		System.out.println("Last name: " + student.getLastName());
		System.out.println("Email:     " + student.getEmail());
	}
}
