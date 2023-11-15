package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import com.example.demo.model.Projection;
import com.example.demo.model.User;

import jakarta.transaction.Transactional;

/**
 * We will test theses methods on the test package, allowing us to use JUnit
 * 
 * @author peter
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	/*
	 * FindUsersUsingQueriesTest
	 */
	
	User findByUsername(String username);

    List<User> findAllByOrderByUsernameAsc();

    List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);

    List<User> findByUsernameAndEmail(String username, String email);

    List<User> findByUsernameOrEmail(String username, String email);

    List<User> findByUsernameIgnoreCase(String username);

    List<User> findByLevelOrderByUsernameDesc(int level);

    List<User> findByLevelGreaterThanEqual(int level);

    List<User> findByUsernameContaining(String text);

    List<User> findByUsernameLike(String text);

    List<User> findByUsernameStartingWith(String start);

    List<User> findByUsernameEndingWith(String end);

    List<User> findByActive(boolean active);

    List<User> findByRegistrationDateIn(Collection<LocalDate> dates);

    List<User> findByRegistrationDateNotIn(Collection<LocalDate> dates);
    
    /*
     * FindUsersSortingAndPagingTest
     */
    
    User findFirstByOrderByUsernameAsc();

    User findTopByOrderByRegistrationDateDesc();

    @Override
    Page<User> findAll(Pageable pageable);

    List<User> findFirst2ByLevel(int level, Sort sort);

    List<User> findByLevel(int level, Sort sort);

    List<User> findByActive(boolean active, Pageable pageable);
    
    /*
     * QueryResultsTest
     */
    
    Streamable<User> findByEmailContaining(String text);

    Streamable<User> findByLevel(int level);
    
    
    /*
     * QueryResultsTest with Jakarta Persistence Query Language!!!
     * 
     * If the method naming is wrong for any of the previous methods that follow the Spring Data JPA naming 
     * conventions (for example, the entity property does not match in the query method), you will get an error when 
     * the application context is loaded. If you are using the @Query annotation and the query you wrote is wrong, you 
     * will get an error at runtime when executing that method. Thus, the @Query annotated methods are more flexible, but 
     * they also provide less safety.
     */
    @Query("select count(u) from User u where u.active = ?1") //one way of passing parameters
    int findNumberOfUsersByActivity(boolean active);

    @Query("select u from User u where u.level = :level and u.active = :active") // another way
    List<User> findByLevelAndActive(@Param("level") int level, @Param("active") boolean active);

    @Query(value = "SELECT COUNT(*) FROM USERS WHERE ACTIVE = ?1", nativeQuery = true) // native SQL LANGUAGE
    int findNumberOfUsersByActivityNative(boolean active);

    // Here we use Spring Expression Language (SpEL) to get the entity name
    @Query("select u.username, LENGTH(u.email) as email_length from #{#entityName} u where u.username like %?1%")
    List<Object[]> findByAsArrayAndSort(String text, Sort sort);
    
    
    /**
     * PROJECTION! 
     */
    
    // inner interface
    List<Projection.UserSummary> findByRegistrationDateAfter(LocalDate date);

    // inner class
    List<Projection.UsernameOnly> findByEmail(String username);

    // dynamic return type
    <T> List<T> findByEmail(String username, Class<T> type);
    
    
    /**
     * Modifying queries: importé para @Transactional el de Jakarta en lugar del de Spring, en el libro era al revés.
     * 
     * By combining @Modifying and @Transactional, you ensure that the modifying query is executed within a transaction, 
     * and the transactional behavior, including commit or rollback, is handled appropriately. This is crucial to maintain 
     * the consistency and integrity of your data when performing modifications to the database.
     */
    @Modifying
    @Transactional
    @Query("update User u set u.level = ?2 where u.level = ?1")
    int updateLevel(int oldLevel, int newLevel);

    /**
     * No usamos @Modifying porque es un método implementado por la interfaz, no por nosotros, que ya lo tiene!
     * 
     * @param level
     * @return
     */
    @Transactional
    int deleteByLevel(int level);

    @Transactional
    @Modifying
    @Query("delete from User u where u.level = ?1")
    int deleteBulkByLevel(int level);
}
