package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId; // アカウントID（主キー）

	private String accountEmail; // メールアドレス

	private String accountName; // アカウント名

	private String password; // パスワード

	// デフォルトコンストラクタ
	public Account() {
	}

	// コンストラクタ
	public Account(String accountEmail, String accountName, String password) {
		this.accountEmail = accountEmail;
		this.accountName = accountName;
		this.password = password;
	}

	public Long getAccountId() {
		return accountId; // アカウントIDを取得する
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId; // アカウントIDを設定する
	}

	public String getAccountEmail() {
		return accountEmail; // メールアドレスを取得する
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail; // メールアドレスを設定する
	}

	public String getAccountName() {
		return accountName; // アカウント名を取得する
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName; // アカウント名を設定する
	}

	public String getPassword() {
		return password; // パスワードを取得する
	}

	public void setPassword(String password) {
		this.password = password; // パスワードを設定する
	}
}
