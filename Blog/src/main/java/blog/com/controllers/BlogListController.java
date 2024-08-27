package blog.com.controllers;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.models.entity.Account;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;

@Controller // コントローラとして定義
public class BlogListController {

	@Autowired
	private HttpSession session; // HTTPセッションを自動的に注入

	@Autowired
	private BlogService blogService; // BlogServiceを自動的に注入

	// 一覧画面を表示する
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		// セッションからログインしているユーザーの情報を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");

		// ログインしていない場合、ログインページへリダイレクト
		if (account == null) {
			return "redirect:/account/login";
		} else {
			// ユーザーのブログ情報を取得
			List<Blog> blogList = blogService.selectAllBlogList(account.getAccountId());
			// モデルにユーザー名とブログリストを追加
			model.addAttribute("accountName", account.getAccountName());
			model.addAttribute("blogList", blogList);
			// ブログ一覧ページを表示
			return "blogList.html";
		}
	}
}
