package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

	// Accountエンティティを保存する
	Account save(Account account);

	// メールアドレスでAccountエンティティを検索する
	Account findByAccountEmail(String accountEmail);

	// メールアドレスとパスワードでAccountエンティティを検索する
	Account findByAccountEmailAndPassword(String accountEmail, String password);
}
