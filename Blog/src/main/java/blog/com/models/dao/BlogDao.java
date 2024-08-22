package blog.com.models.dao;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Blog;

@Repository
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {
    // 保存 Blog 实体
    Blog save(Blog blog);

    // 查询所有 Blog 实体
    List<Blog> findAll();

    // 根据标题查询 Blog 实体
    Blog findByBlogTitle(String blogTitle); // 确保参数名与实体类中的字段名一致

    // 根据 ID 查询 Blog 实体
    Blog findByBlogId(Long blogId);

    // 根据 ID 删除 Blog 实体
    void deleteByBlogId(Long blogId);

    // 根据 accountId 查询 Blog 实体列表
    List<Blog> findByAccountId(Long accountId); // 添加此方法
}
