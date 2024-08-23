package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Account;
import blog.com.services.BlogService;

@Controller
public class BlogRegisterController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private HttpSession session;

    @GetMapping("/blog/register")
    public String getBlogRegisterPage(Model model) {
        Account account = (Account) session.getAttribute("loginAccountInfo");
        if (account == null) {
            return "redirect:/account/login";
        } else {
            model.addAttribute("accountName", account.getAccountName());
            return "blogRegister.html";
        }
    }

    @PostMapping("/blog/register/process")
    public String blogRegisterProcess(@RequestParam String title,
                                      @RequestParam String category,
                                      @RequestParam MultipartFile image,
                                      @RequestParam String details) {
        Account account = (Account) session.getAttribute("loginAccountInfo");

        if (account == null) {
            return "redirect:/account/login";
        }

        // 生成文件名
        String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + image.getOriginalFilename();
        
        try {
            // 保存文件到指定路径
            Path filePath = Path.of("src/main/resources/static/blog-img/" + fileName);
            Files.copy(image.getInputStream(), filePath);
            System.out.println("File uploaded successfully: " + filePath); // 调试信息
        } catch (IOException e) {
            e.printStackTrace();
            return "blogRegister.html"; // 如果文件保存失败，返回注册页面
        }

        // 使用 account.getAccountId() 调用 createBlog 方法
        if (blogService.createBlog(title, category, fileName, details, account.getAccountId())) {
            return "redirect:/blog/list";
        } else {
            return "blogRegister.html"; // 如果创建失败，返回注册页面
        }
    }
}
