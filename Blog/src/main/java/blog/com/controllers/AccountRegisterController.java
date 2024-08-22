package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.services.AccountService;

@Controller
public class AccountRegisterController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/account/register")
	public String getAccountRegisterPage() {
		return "register.html";
	}
	
	@PostMapping("/account/register/process")
	public String accountRegisterProcess(@RequestParam("name") String accountName,
           				@RequestParam("email") String accountEmail,
           				@RequestParam("pass") String password) {
		if(accountService.createAccount(accountName,accountEmail,password)) {
			return "login.html";
		}else {
			return "register.html";
		}
		
	}
	
	

}
