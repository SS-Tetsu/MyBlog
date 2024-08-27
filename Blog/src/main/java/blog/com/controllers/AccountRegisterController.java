package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.services.AccountService;

@Controller // コントローラとして定義
public class AccountRegisterController {

	@Autowired // AccountServiceを自動注入
	private AccountService accountService;

	// 登録ページを表示するためのGETリクエストを処理
	@GetMapping("/account/register")
	public String getAccountRegisterPage() {
		return "register.html"; // 登録ページに戻る
	}

	// 登録フォームPOSTリクエスト処理、送信されたデータでaccountを作成
	@PostMapping("/account/register/process")
	// POSTリクエストを処理するメソッド
	public String accountRegisterProcess(@RequestParam("name") String accountName, //  name パラメータを取得、accountName 変数に格納
										@RequestParam("email") String accountEmail, // emailパラメータを取得、accountEmail 変数に格納
										@RequestParam("pass") String password) {    // passパラメータを取得、password 変数に格納
		// 作成成功できたらloginページへ、失敗なら再度registerページを表示
		if (accountService.createAccount(accountName, accountEmail, password)) {
			return "login.html"; // 成功時loginページに移動
		} else {
			return "register.html"; // 失敗時registerページを表示
		}
	}
}
