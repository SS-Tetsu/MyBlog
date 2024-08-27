package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AccountDao;
import blog.com.models.entity.Account;

@Service // サービスとして指定
public class AccountService {

	@Autowired // AccountDao を自動注入
	private AccountDao accountDao;

	// 新しいアカウントを作成するメソッド
	public boolean createAccount(String accountEmail, String accountName, String password) {
		// アカウントが存在しない場合、新しいアカウントを保存
		if (accountDao.findByAccountEmail(accountEmail) == null) {
			accountDao.save(new Account(accountEmail, accountName, password));
			return true; // アカウント作成成功
		} else {
			return false; // アカウント作成失敗（アカウントが既に存在する）
		}
	}

	// ログインのチェックを行うメソッド
	public Account loginCheck(String accountEmail, String password) {
		// メールアドレスとパスワードでアカウントを検索
		Account account = accountDao.findByAccountEmailAndPassword(accountEmail, password);
		// アカウントが存在しない場合はnullを返す
		if (account == null) {
			return null;
		} else {
			return account; // アカウントが存在する場合、アカウントオブジェクトを返す
		}
	}
}
