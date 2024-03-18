package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			deleteInstructor(appDAO);
		};

	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		appDAO.deleteInstructorById(id);
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 1;

		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("Instructor: " + instructor);
		System.out.println("Instructor details: " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		/*Instructor instructor
				= new Instructor("George", "Tsiga", "tsiga.george@mail.com");

		InstructorDetail instructorDetail =
				new InstructorDetail("https://www.youtube.com/channel/UCVTClkJCiDt5fgJfLwRvFrA", "Content creation");*/

		Instructor instructor
				= new Instructor("Tavish", "Tsiga", "tsiga.tavish@mail.com");

		InstructorDetail instructorDetail =
				new InstructorDetail("https://www.youtube.com/channel/UCVTClkJCiDt5fgJfLwRvFrA", "Always busy");

		instructor.setInstructorDetail(instructorDetail);

		System.out.println(instructor);
		appDAO.save(instructor);
	}


}
