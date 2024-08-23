package blog.com.controllers;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blog.com.models.entity.Account;
import blog.com.services.BlogService;

@Controller
public class BlogDeleteController {
   @Autowired
   private HttpSession session;
   
   @Autowired
   private BlogService blogService;
   
   @PostMapping("/blog/delete")
   public String blogDelete(Long blogId) {
	// セッションからログインしている人の情報をaccountという変数に格納
			Account account = (Account) session.getAttribute("loginAccountInfo");
			// もしaccount == null ログイン画面にリダイレクトする
			if (account == null) {
				return "redirect:/account/login";
			} else {
				//もしdeleteProductの結果がtrueの時に、商品一覧にリダイレクトする
				//そうでない場合、　編集画面にリダイレクトする
				if(blogService.deleteBlog(blogId)) {
					return "redirect:/blog/list";
				}else {
					return "redirect:/blog/edit"+blogId;
				}
			}
   }
   
}
