package com.example.demo.model;

import org.springframework.beans.factory.annotation.Value;


/**
 * We can use a projection (to show limited information to the client)
 * with an interface or with a class.
 * The difference is that the interface is implemented by Spring
 * Instead, the class is implemented by ourselves
 * 
 * @author peter
 *
 */
public class Projection {

	public interface UserSummary {

        String getUsername();

        @Value("#{target.username}"+" "+"#{target.email}")
        String getInfo();

    }

    public static class UsernameOnly {
        private String username;

        public UsernameOnly(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

    }

}
