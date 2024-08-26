package blog.com.models.dao;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Blog;

@Repository
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {
    // Blogエンティティを保存する
    Blog save(Blog blog);

    // すべてのBlogエンティティを検索する
    List<Blog> findAll();

    // タイトルでBlogエンティティを検索する
    Blog findByBlogTitle(String blogTitle); // エンティティのフィールド名に一致させること

    // IDでBlogエンティティを検索する
    Blog findByBlogId(Long blogId);

    // IDでBlogエンティティを削除する
    void deleteByBlogId(Long blogId);

    // accountIdでBlogエンティティリストを検索する
    List<Blog> findByAccountId(Long accountId); // このメソッドを追加
}
