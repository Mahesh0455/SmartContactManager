package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.util.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;	



@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String home(Model model) {

		model.addAttribute("title", "Home- Smart Contact Manager");

		return "home";
	
	}

	@GetMapping("/about")
	public String about(Model model) {

		model.addAttribute("title", "about- Smart Contact Manager");

		return "about";
	}

	@GetMapping("/signup")
	public String signup(Model model) {

		model.addAttribute("title", "Register- SmartContactManager");

		model.addAttribute("user", new User());

		return "signup";

	}

	@PostMapping(path = "/doRegister")
	public String resgisterUser( @Valid @ModelAttribute("user") User user, BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {

		try {

			if (!agreement) {
				System.out.println("You must agree terms and condition");

				throw new Exception("You must agree terms and condition");

			}
			
			
			if(result.hasErrors()) {
				
				
				System.out.println(result.toString());
				model.addAttribute("user", user);
				model.addAttribute(session);
				
				return "signup";
				
				
			}
			
			
			

			user.setRole("User");
			user.setEnabled(true);
			System.out.println("Agreement" + agreement);

			System.out.println("User: " + user);

			this.userRepository.save(user);
			
			model.addAttribute("user", user);
			
			model.addAttribute("message", new Message("Successfully Registered","alert-success"));
			

			return "signup";

		} catch (Exception e) {

			model.addAttribute("user", user);
			model.addAttribute("message", new Message("You must agree Terms and condition","alert-danger"));

			return "signup";

		}

	}

}
