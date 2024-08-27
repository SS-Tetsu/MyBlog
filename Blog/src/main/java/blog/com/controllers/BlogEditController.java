package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Account;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;

@Controller    // コントローラとして定義
public class BlogEditController {

	@Autowired // BlogServiceを自動的に注入
	private BlogService blogService;

	@Autowired // HTTPセッションを自動的に注入
	private HttpSession session;

	// 編集画面表示
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		// セッションからログインしているaccount情報を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");

		// ログインしていない場合、ログインページへリダイレクト
		if (account == null) {
			return "redirect:/account/login";
		} else {
			// 編集するブログ情報を取得
			Blog blog = blogService.blogEditCheck(blogId);

			// ブログが存在しない場合、ブログ一覧ページへリダイレクト
			if (blog == null) {
				return "redirect:/blog/list";
			} else {
				// モデルにログイン account とブログ情報追加
				model.addAttribute("accountName", account.getAccountName());
				model.addAttribute("blog", blog);
				return "blogEdit.html"; // 編集ページ表示
			}
		}
	}

	// 更新処理をする
	@PostMapping("/blog/edit/process")
	public String blogUpdate(@RequestParam String blogTitle, @RequestParam String categoryName,
			@RequestParam MultipartFile blogImage, @RequestParam String article,
			@RequestParam Long blogId) {
		// セッションからログインしているユーザーの情報を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");

		// ログインしていない場合、ログインページへリダイレクト
		if (account == null) {
			return "redirect:/account/login";
		} else {
			// 現在の日時を基にファイル名を作成
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();

			// ファイルを指定のパスに保存
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				e.printStackTrace(); // ファイル保存中のエラーを出力
			}

			// ブログの更新処理を実行
			if (blogService.blogUpdate(blogId, blogTitle, categoryName, fileName, article, account.getAccountId())) {
				return "redirect:/blog/list"; // 成功した場合、ブログ一覧ページへリダイレクト
			} else {
				return "redirect:/blog/edit" + blogId; // 失敗した場合、編集ページにリダイレクト
			}
		}
	}
}
