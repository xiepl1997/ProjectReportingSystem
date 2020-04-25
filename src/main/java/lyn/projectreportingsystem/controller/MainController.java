package lyn.projectreportingsystem.controller;

import lyn.projectreportingsystem.pojo.User;
import lyn.projectreportingsystem.service.impl.UserService;
import lyn.projectreportingsystem.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService userService = null;

    @RequestMapping("/main")
    public String mainPage(HttpServletRequest request,
                           Model model){
//        HttpSession session = request.getSession();
//        Object user = session.getAttribute("user");
//        if(user != null){
//            user = userService.SelectUserByEmailPassword(((User) user).getEmail(), ((User) user).getPassword());
//            if(user == null)
//                return "redirect:/login.html";
//            else{
//                return "main";
//            }
//        }
//        return "redirect:/login.html";

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        return "main";

    }

}
