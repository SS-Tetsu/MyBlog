package blog.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.BlogDao;
import blog.com.models.entity.Blog;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao; // BlogDaoオブジェクトを自動的に注入

	// blog一覧を取得するメソッド
	public List<Blog> selectAllBlogList(Long accountId) {
		if (accountId == null) {
			return null; // アカウントIDがnullの場合、nullを返す
		} else {
			return blogDao.findByAccountId(accountId); // アカウントIDでブログリストを取得
		}
	}

	// 新しいブログ登録するメソッド
	// blogTitleが存在しない場合、新しいブログを保存する
	// 成功した場合はtrue、存在する場合はfalseを返す
	public boolean createBlog(String blogTitle, String categoryName, String blogImage, String article, Long accountId) {
		if (blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(blogTitle, categoryName, blogImage, article, accountId)); // 新しいブログを保存
			return true; // ブログの作成成功
		} else {
			return false; // ブログタイトルが既に存在する
		}
	}

	// 編集用のブログ情報を取得するメソッド
	// blogIdがnullの場合、nullを返す
	// それ以外の場合、blogIdに基づいてブログ情報を取得する
	public Blog blogEditCheck(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}

	// ブログを更新するメソッド
	// blogIdがnullの場合、更新処理は行わずfalseを返す
	// それ以外の場合、指定されたブログIDのブログを取得し、更新処理を行う
	// 更新が成功した場合はtrueを返す
	public boolean blogUpdate(Long blogId,
			String blogTitle,
			String categoryName,
			String blogImage,
			String article,
			Long accountId) {
		if (blogId == null) {
			return false;
		} else {
			Blog blog = blogDao.findByBlogId(blogId); // ブログIDでブログを取得
			blog.setBlogTitle(blogTitle); // タイトル更新
			blog.setCategoryName(categoryName); // カテゴリ名更新
			blog.setBlogImage(blogImage); // 画像更新
			blog.setArticle(article); // 記事内容更新
			blog.setAccountId(accountId); // アカウントID更新
			blogDao.save(blog); // 更新されたブログ保存
			return true; // ブログ更新成功
		}
	}

	// ブログを削除するメソッド
	// blogIdがnullの場合、削除処理は行わずfalseを返す
	// それ以外の場合、指定されたブログIDでブログを削除し、trueを返す
	public boolean deleteBlog(Long blogId) {
		if (blogId == null) {
			return false; // ブログIDがnull場合、削除処理をしない
		} else {
			blogDao.deleteByBlogId(blogId); // ブログIDでブログを削除
			return true; // ブログ削除が成功
		}
	}
}
