package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Blog {

	@Id // 主キー
	@GeneratedValue(strategy = GenerationType.AUTO) // 主キー値を自動生成
	private Long blogId; // ブログID

	private String blogTitle; // ブログタイトル

	private String categoryName; // ブログカテゴリー名

	private String blogImage; // ブログ画像名

	private String article; // ブログ記事内容

	private Long accountId; // アカウントID

	public Blog() {
		// デフォルトコンストラクタ
	}

	public Blog(String blogTitle, String categoryName, String blogImage, String article, Long accountId) {
		this.blogTitle = blogTitle; // タイトル設定
		this.categoryName = categoryName; // カテゴリー名設定
		this.blogImage = blogImage; // 画像ファイル名設定
		this.article = article; // 記事内容を設定
		this.accountId = accountId; // アカウントID設定
	}

	public Long getBlogId() {
		return blogId; // ブログID取得
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId; // ブログID設定
	}

	public String getBlogTitle() {
		return blogTitle; // ブログタイトル取得
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle; // ブログタイトル設定
	}

	public String getCategoryName() {
		return categoryName; // ブログカテゴリー名取得
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName; // ブログカテゴリー名設定
	}

	public String getBlogImage() {
		return blogImage; // ブログ画像ファイル名取得
	}

	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage; // ブログ画像ファイル名設定
	}

	public String getArticle() {
		return article; // ブログ記事内容取得
	}

	public void setArticle(String article) {
		this.article = article; // ブログ記事内容設定
	}

	public Long getAccountId() {
		return accountId; // アカウントID取得
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId; // アカウントID設定
	}
}
