package com.ashfaq.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {


	  @GetMapping("/home")
	  public String handleWelcome() {
	    return "home";
	  }

	  @GetMapping("/admin/home")
	  public String handleAdminHome() {
	    return "home_admin";
	  }

	  @GetMapping("/user/home")
	  public String handleUserHome() {
	    return "home_user";
	  }

	


}
