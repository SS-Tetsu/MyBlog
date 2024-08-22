package blog.com.controllers;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.entity.Account;
import blog.com.services.AccountService;

@Controller
public class AccountLoginController {
	@Autowired
	private AccountService accountService;
	
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/account/login")
	public String getAccountLoginPage() {
		return "login.html";
	}
	
	@PostMapping("/account/login/process")
	public String accountLoginProcess(@RequestParam("name") String accountName,
									@RequestParam("pass") String password) {
		Account account = accountService.loginCheck(accountName, password);
		if(account == null) {
			return "login.html";			
		}else {
			session.setAttribute("loginAccountInfo",account);
			return "redirect:/blog/list";
		}
	}
}
