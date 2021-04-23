package com.kamer.springbootuserregistration.repository;

import com.kamer.springbootuserregistration.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created on September, 2019
 *
 * @author kamer
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	//User findByEmailIgnoreCase(String email);
	Optional<User> findByEmailIgnoreCase(String email);

}
