package blog.com.controllers;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.models.entity.Account;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;

@Controller
public class BlogListController {
    @Autowired
    private HttpSession session;
    @Autowired
    private BlogService blogService;
    
    @GetMapping("/blog/list")
    public String getBlogList(Model model) {
        Account account = (Account) session.getAttribute("loginAccountInfo");
        if (account == null) {
            return "redirect:/account/login";
        } else {
            List<Blog> blogList = blogService.selectAllBlogList(account.getAccountId());
            model.addAttribute("accountName", account.getAccountName());
            model.addAttribute("blogList", blogList);
            return "blogList"; // 不要加 .html
        }
    }
}
