package blog.com.controllers;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blog.com.models.entity.Account;
import blog.com.services.BlogService;

@Controller // コントローラとして定義
public class BlogDeleteController {
	@Autowired // HttpSessionを自動的に注入する
	private HttpSession session;

	@Autowired // BlogServiceを自動的に注入する
	private BlogService blogService;

	@PostMapping("/blog/delete")
	public String blogDelete(Long blogId) {
		// セッションからログインしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// もしaccount == null ログイン画面にリダイレクトする
		if (account == null) {
			return "redirect:/account/login";
		} else {
			//もしdeleteBlogの結果がtrueの時に、blog一覧にリダイレクト
			//そうでない場合、編集画面にリダイレクト
			if (blogService.deleteBlog(blogId)) {
				return "redirect:/blog/list"; // 削除成功 ブログ一覧にリダイレクト
			} else {
				return "redirect:/blog/edit" + blogId; // 削除失敗 編集ページにリダイレクト
			}
		}
	}

}
