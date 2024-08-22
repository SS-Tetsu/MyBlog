package blog.com.models.dao;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Blog;

@Repository
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {
	
	Blog save(Blog blog);
	
	List<Blog>findAll();
	
	Blog findByBlogTitle(String blogName);
	
	Blog findByBlogId(Long blogId);

	void deleteByBlogId(Long blogId);
}
