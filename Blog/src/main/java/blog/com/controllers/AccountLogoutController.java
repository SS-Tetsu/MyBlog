package blog.com.controllers;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Controllerとして定義
public class AccountLogoutController {

	@Autowired // HttpSession自動注入
	private HttpSession session;

	@GetMapping("/account/logout") // GETリクエスト /account/logout 処理
	public String accountLogout() {
		session.invalidate(); // sessionデータを削除（ログアウト）
		return "redirect:/account/login"; // loginページにリダイレクト
	}
}
