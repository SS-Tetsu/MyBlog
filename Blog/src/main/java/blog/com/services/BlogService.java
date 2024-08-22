package blog.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.BlogDao;
import blog.com.models.entity.Blog;

@Service
public class BlogService {
    @Autowired
    private BlogDao blogDao;

    public List<Blog> selectAllBlogList(Long accountId) {
        if (accountId == null) {
            return null;
        } else {
            return blogDao.findByAccountId(accountId);
        }
    }

    public boolean createBlog(String blogTitle, String categoryName, String blogImage, String article, Long accountId) {
        if (blogDao.findByBlogTitle(blogTitle) == null) {
            blogDao.save(new Blog(blogTitle, categoryName, blogImage, article, accountId));
            return true;
        } else {
            return false;
        }
    }
}
