package com.kamer.springbootuserregistration.service;

import com.kamer.springbootuserregistration.entity.ConfirmationToken;
import com.kamer.springbootuserregistration.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created on September, 2019
 *
 * @author kamer
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenService {

	private final ConfirmationTokenRepository confirmationTokenRepository;

	public void saveConfirmationToken(ConfirmationToken confirmationToken) {

		confirmationTokenRepository.save(confirmationToken);
	}

	public void deleteConfirmationToken(Long id) {

		confirmationTokenRepository.deleteById(id);
	}


	public Optional<ConfirmationToken> findConfirmationTokenByToken(String token) {

		return confirmationTokenRepository.findConfirmationTokenByConfirmationToken(token);
	}


}
