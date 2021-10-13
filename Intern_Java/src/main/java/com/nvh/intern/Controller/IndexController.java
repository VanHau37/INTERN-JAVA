package com.nvh.intern.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nvh.intern.Entity.User;
import com.nvh.intern.Repository.UserRepository;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class IndexController {
	@Autowired
	private UserRepository repo;

	// index
	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}

	// get sign-up
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}

	// post sign-up
	@PostMapping("/process_register")
	public String processRegistration(@Valid User user, Errors er,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		if (er.hasErrors()) {
			return "signup_form";
		} else {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setEmoji(fileName);

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodePassword = encoder.encode(user.getPassword());

			user.setPassword(encodePassword);
			User savedUser = repo.save(user);
			String uploadDir = "/images/" + savedUser.getId();
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try (InputStream inputStream = multipartFile.getInputStream()) {				
					Path filePath = uploadPath.resolve(fileName);
					System.out.println(filePath.toFile().getAbsolutePath());
					Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);				
			} catch (IOException e) {
				throw new IOException("could not save uploaded file:" + fileName);
			}
		}
		return "register_success";
	}

	// list users
	@GetMapping("/list_users")
	public String viewUsersList(Model model) {
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
}
