package com.kamer.springbootuserregistration.repository;

import com.kamer.springbootuserregistration.entity.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created on September, 2019
 *
 * @author kamer
 */
@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {

	Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);
}
