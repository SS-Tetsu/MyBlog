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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Account;
import blog.com.services.BlogService;

@Controller // コントローラとして定義
public class BlogRegisterController {

	@Autowired // BlogServiceを自動的に注入
	private BlogService blogService;

	@Autowired // HTTPセッションを自動的に注入
	private HttpSession session;

	// ブログ登録画面を表示する
	@GetMapping("/blog/register")
	public String getBlogRegisterPage(Model model) {
		// セッションからログインしているユーザーの情報を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");

		// ログインしていない場合、ログインページにリダイレクト
		if (account == null) {
			return "redirect:/account/login";
		} else {
			// ユーザー名をモデルに追加
			model.addAttribute("accountName", account.getAccountName());
			// ブログ登録ページを表示
			return "blogRegister.html";
		}
	}

	// ブログの登録処理を行う
	@PostMapping("/blog/register/process")
	public String blogRegisterProcess(@RequestParam String title,
			@RequestParam String category,
			@RequestParam MultipartFile image,
			@RequestParam String details) {
		// セッションからログインしているユーザーの情報を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");

		// ログインしていない場合、ログインページにリダイレクト
		if (account == null) {
			return "redirect:/account/login";
		}

		// ファイル名を生成
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + image.getOriginalFilename();

		try {
			// 指定されたパスにファイルを保存
			Path filePath = Path.of("src/main/resources/static/blog-img/" + fileName);
			Files.copy(image.getInputStream(), filePath);
			System.out.println("File uploaded successfully: " + filePath); // デバッグ用メッセージ
		} catch (IOException e) {
			e.printStackTrace(); // エラーログを出力
			return "blogRegister.html"; // ファイル保存に失敗した場合、登録ページを再表示
		}

		// ブログを作成
		if (blogService.createBlog(title, category, fileName, details, account.getAccountId())) {
			return "redirect:/blog/list"; // 成功した場合、ブログ一覧ページへリダイレクト
		} else {
			return "blogRegister.html"; // 失敗した場合、登録ページを再表示
		}
	}
}
