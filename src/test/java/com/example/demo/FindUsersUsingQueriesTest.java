package com.example.demo;

import org.junit.jupiter.api.Test;

import com.example.demo.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


public class FindUsersUsingQueriesTest extends CatalinSpringDataJpa1ApplicationTests {

	@Test
    void testFindAll() {
        List<User> users = userRepository.findAll();
        assertEquals(10, users.size());
        //users.forEach(System.out::println);
    }
	
	@Test
    void testFindUser() {
        User beth = userRepository.findByUsername("beth");
        assertEquals("beth", beth.getUsername());
    }
}
