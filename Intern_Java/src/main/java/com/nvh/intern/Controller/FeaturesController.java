package com.nvh.intern.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nvh.intern.Entity.User;
import com.nvh.intern.Repository.UsersRepository;

@Controller
public class FeaturesController {
	@Autowired
	UsersRepository userRepository;
	
	@GetMapping("")
	public String showUserList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "list_users";
	}
	//edit user
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("user", user);
		return "update-user";
	}
	//detail user
	@GetMapping("/detail/{id}")
	public String DetailUser(@PathVariable("id") long id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("user", user);
		return "detail-user";
	}
	//update user
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "update-user";
		}
		userRepository.save(user);
		return "redirect:../list_users";
	}
	//delete user
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);
		return "redirect:../list_users";
	}
}