package com.nvh.intern.Config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path imageUploadDir = Paths.get("./images");
		String imageUploadPath = imageUploadDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/images/**").addResourceLocations("file:/" + imageUploadPath + "/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

}
