package com.example.demo;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.repositories.UserRepository;

import com.example.demo.model.User;

/**
 * Abstract class that will be implemented by all the classes with their respective tests
 * Each time a test starts, the beforeall and afterall will execute
 * 
 * Using the @TestInstance(TestInstance.Lifecycle.PER_CLASS) annotation, we ask JUnit 5 to 
 * create a single instance of the test class and reuse it for all test methods. This will allow 
 * us to make the @BeforeAll and @AfterAll annotated methods nonstatic and to directly use the autowired 
 * UserRepository instance field inside them.
 * 
 * @author peter
 *
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class CatalinSpringDataJpa1ApplicationTests {

	@Autowired
    UserRepository userRepository;

	/**
	 * The @BeforeAll annotated method will be executed once before executing all tests from a 
	 * class that extends SpringDataJpaApplicationTests.
	 */
    @BeforeAll
    void beforeAll() {
        userRepository.saveAll(generateUsers()); // general methods, we don't implement them
    }
    
    @AfterAll
    void afterAll() {
        userRepository.deleteAll();  // general methods, we don't implement them
    }
    
    private static List<User> generateUsers() {
        List<User> users = new ArrayList<>();

        User john = new User("john", LocalDate.of(2020, Month.APRIL, 13));
        john.setEmail("john@somedomain.com");
        john.setLevel(1);
        john.setActive(true);

        User mike = new User("mike", LocalDate.of(2020, Month.JANUARY, 18));
        mike.setEmail("mike@somedomain.com");
        mike.setLevel(3);
        mike.setActive(true);

        User james = new User("james", LocalDate.of(2020, Month.MARCH, 11));
        james.setEmail("james@someotherdomain.com");
        james.setLevel(3);
        james.setActive(false);

        User katie = new User("katie", LocalDate.of(2021, Month.JANUARY, 5));
        katie.setEmail("katie@somedomain.com");
        katie.setLevel(5);
        katie.setActive(true);

        User beth = new User("beth", LocalDate.of(2020, Month.AUGUST, 3));
        beth.setEmail("beth@somedomain.com");
        beth.setLevel(2);
        beth.setActive(true);

        User julius = new User("julius", LocalDate.of(2021, Month.FEBRUARY, 9));
        julius.setEmail("julius@someotherdomain.com");
        julius.setLevel(4);
        julius.setActive(true);

        User darren = new User("darren", LocalDate.of(2020, Month.DECEMBER, 11));
        darren.setEmail("darren@somedomain.com");
        darren.setLevel(2);
        darren.setActive(true);

        User marion = new User("marion", LocalDate.of(2020, Month.SEPTEMBER, 23));
        marion.setEmail("marion@someotherdomain.com");
        marion.setLevel(2);
        marion.setActive(false);

        User stephanie = new User("stephanie", LocalDate.of(2020, Month.JANUARY, 18));
        stephanie.setEmail("stephanie@someotherdomain.com");
        stephanie.setLevel(4);
        stephanie.setActive(true);

        User burk = new User("burk", LocalDate.of(2020, Month.NOVEMBER, 28));
        burk.setEmail("burk@somedomain.com");
        burk.setLevel(1);
        burk.setActive(true);

        users.add(john);
        users.add(mike);
        users.add(james);
        users.add(katie);
        users.add(beth);
        users.add(julius);
        users.add(darren);
        users.add(marion);
        users.add(stephanie);
        users.add(burk);

        return users;
    }

}
