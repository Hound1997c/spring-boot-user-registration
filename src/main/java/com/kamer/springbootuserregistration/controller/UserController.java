package com.kamer.springbootuserregistration.controller;

import com.kamer.springbootuserregistration.entity.User;
import com.kamer.springbootuserregistration.entity.ConfirmationToken;
import com.kamer.springbootuserregistration.service.ConfirmationTokenService;
import com.kamer.springbootuserregistration.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Created on September, 2019
 *
 * @author kamer
 */
@Controller
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	private final ConfirmationTokenService confirmationTokenService;

	@GetMapping("/sign-in")
	String signIn(Model model) {

		model.addAttribute("user",new User());
		return "sign-in";
	}

	@PostMapping("/sign-in")
	String goIndex() {

		return "index";
	}

	@GetMapping("/sign-up")
	String signUpPage(User user) {

		return "sign-in";
	}

	@PostMapping("/sign-up")
	String signUp(User user) {

		userService.signUpUser(user);

		return "redirect:/sign-in";
	}

	@GetMapping("/sign-up/confirm")
	String confirmMail(@RequestParam("token") String token) {

		Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

		optionalConfirmationToken.ifPresent(userService::confirmUser);

		return "redirect:/sign-in";
	}


	@RequestMapping(value="/forgot-password", method= RequestMethod.GET)
	public ModelAndView displayResetPassword(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("forgot-password");
		return modelAndView;
	}

	@RequestMapping(value="/forgot-password", method=RequestMethod.POST)
	public ModelAndView forgotUserPassword(ModelAndView modelAndView, User user) {
		User existingUser = userService.findByEmailIgnoreCase(user.getEmail());
		if (existingUser != null) {
			// Create token
			userService.initiateResetUserPassword(existingUser);


			modelAndView.addObject("message", "Request to reset password received. Check your inbox for the reset link.");
			modelAndView.setViewName("forgot-password");

		} else {
			modelAndView.addObject("message", "This email address does not exist!");
			modelAndView.setViewName("forgot-password");
		}
		return modelAndView;
	}


	@RequestMapping(value="/confirm-reset", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token")String confirmationToken) {
		Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(confirmationToken);
		//ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		ConfirmationToken token = optionalConfirmationToken.get();
		if (token != null) {
			//User user = userService.findByEmailIgnoreCase(token.getUser().getEmail());

			modelAndView.addObject("user", token.getUser());
			modelAndView.addObject("emailId", token.getUser().getEmail());
			modelAndView.setViewName("update-password");
			//userService.confirmUser(token);
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("sign-in");
		}
		return modelAndView;
	}

	// Endpoint to update a user's password
	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public ModelAndView resetUserPassword(ModelAndView modelAndView, User tokenUser) {
		if (tokenUser!=null && tokenUser.getEmail() != null) {
			// Use email to find user

			//User tokenUser = userService.findByEmailIgnoreCase(user.getEmail());
			 userService.resetUserPassword(tokenUser);

			modelAndView.addObject("message", "Password successfully reset. You can now log in with the new credentials.");
			modelAndView.setViewName("sign-in");
		} else {
			modelAndView.addObject("message","The link is invalid or broken!");
			modelAndView.setViewName("sign-in");
		}
		return modelAndView;
	}


}
