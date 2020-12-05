package com.gst.billbook.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gst.billbook.model.UserDetails;


@Controller
public class LoginController {
	
	/*
	 * @Autowired JdbcUserDetailsManager jdbcUserDetailsManager;
	 * 
	 * @Autowired PasswordEncoder passwordEncoder;
	 * 
	 * @RequestMapping("/") public String firstPage() { return "welcome"; }
	 * 
	 * @GetMapping("/register") public String showForm(Model model) { UserDetails
	 * user = new UserDetails(); List<String> professionList =
	 * Arrays.asList("Developer", "Designer", "Tester", "AppUser");
	 * 
	 * model.addAttribute("user", user); model.addAttribute("professionList",
	 * professionList);
	 * 
	 * return "register"; }
	 * 
	 * @PostMapping("/register") public String
	 * processRegister(@ModelAttribute("user") UserDetails userDetails) {
	 * 
	 * List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	 * authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	 * 
	 * User user = new User(userDetails.getUsername(),
	 * passwordEncoder.encode(userDetails.getPassword()), authorities);
	 * jdbcUserDetailsManager.createUser(user); return "welcome"; }
	 * 
	 * @GetMapping("/login") public String showLoginForm(Model model) { UserDetails
	 * user = new UserDetails(); model.addAttribute("user", user);
	 * 
	 * return "login"; }
	 * 
	 * @PostMapping("/login") public String processLogin(@ModelAttribute("user")
	 * UserDetails userDetails) {
	 * org.springframework.security.core.userdetails.UserDetails user =
	 * jdbcUserDetailsManager.loadUserByUsername(userDetails.getUsername());
	 * 
	 * if(user==null) return "login";
	 * 
	 * if(user.getUsername().equals(userDetails.getUsername()) &&
	 * user.getPassword().equals(passwordEncoder.encode(userDetails.getPassword())))
	 * { return "welcome"; } else return "login"; }
	 */	
}
