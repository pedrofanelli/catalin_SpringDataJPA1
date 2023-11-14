package com.example.demo;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.repositories.UserRepository;
import com.example.demo.model.User;

/**
 * We will test the methods on the test package, allowing us to use JUnit
 * 
 * @author peter
 *
 */
@SpringBootApplication
public class CatalinSpringDataJpa1Application {

	public static void main(String[] args) {
		SpringApplication.run(CatalinSpringDataJpa1Application.class, args);
	}

    @Bean
    ApplicationRunner configure(UserRepository userRepository) {
		return env ->
		{
			User user1 = new User("beth", LocalDate.of(2023, Month.AUGUST, 3));
			User user2 = new User("mike", LocalDate.of(2023, Month.JANUARY, 18));
			User user3 = new User("gandalf", LocalDate.of(2023, Month.SEPTEMBER, 11));
			User user4 = new User("aragorn", LocalDate.of(2023, Month.JANUARY, 22));
			User user5 = new User("gimli", LocalDate.of(2023, Month.FEBRUARY, 3));
			User user6 = new User("legolas", LocalDate.of(2023, Month.JUNE, 18));
			User user7 = new User("frodo", LocalDate.of(2023, Month.JULY, 7));
			User user8 = new User("sam", LocalDate.of(2023, Month.NOVEMBER, 30));
			User user9 = new User("pippin", LocalDate.of(2023, Month.MARCH, 1));
			User user10 = new User("merry", LocalDate.of(2023, Month.APRIL, 12));

			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);
			userRepository.save(user5);
			userRepository.save(user6);
			userRepository.save(user7);
			userRepository.save(user8);
			userRepository.save(user9);
			userRepository.save(user10);

			userRepository.findAll().forEach(System.out::println);
		};
	}
	
}
