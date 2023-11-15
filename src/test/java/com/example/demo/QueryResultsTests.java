package com.example.demo;

import org.junit.jupiter.api.Test;

import com.example.demo.model.User;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QueryResultsTests extends CatalinSpringDataJpa1ApplicationTests {

	@Test
    void testStreamable() {
        try (Stream<User> result = userRepository.findByEmailContaining("someother")
                .and(userRepository.findByLevel(2))
                .stream().distinct()) {
            assertEquals(6, result.count());
        }
    }
}
