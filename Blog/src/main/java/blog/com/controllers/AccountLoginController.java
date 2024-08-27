package blog.com.controllers;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.entity.Account;
import blog.com.services.AccountService;

@Controller // コントローラとして指定
public class AccountLoginController {
	@Autowired // AccountServiceを自動的に注入
	private AccountService accountService;

	@Autowired // HttpSessionを自動的に注入
	private HttpSession session;

	@GetMapping("/account/login") // GETリクエストを処理
	public String getAccountLoginPage() {
		return "login.html"; // ログインページを表示
	}

	@PostMapping("/account/login/process") // POSTリクエストを処理
	public String accountLoginProcess(@RequestParam("name") String accountName, // リクエストパラメータ "name" を受け取る
			@RequestParam("pass") String password) { // リクエストパラメータ "pass" を受け取る
		Account account = accountService.loginCheck(accountName, password); // サービスを使ってログインチェック
		if (account == null) { // account存在しない場合
			return "login.html"; // ログインページに戻る		
		} else { // accountが存在する場合
			session.setAttribute("loginAccountInfo", account); // sessionにアカウント情報を保存
			return "redirect:/blog/list"; // blogListページにリダイレクト
		}
	}
}
