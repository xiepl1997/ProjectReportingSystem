package lyn.projectreportingsystem.controller;

import lyn.projectreportingsystem.pojo.User;
import lyn.projectreportingsystem.service.impl.UserService;
import lyn.projectreportingsystem.util.CookieUtil;
import lyn.projectreportingsystem.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService = null;

    @RequestMapping("/")
    public String home() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model,
                        HttpServletResponse response,
                        HttpServletRequest request) {
        password = MD5.getMD5(password);
        User user = userService.SelectUserByEmailPassword(email, password);
        if (user == null) {
            model.addAttribute("msg", "账号或密码不正确！");
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        //设置cookie
//        CookieUtil.setCookie(response, "email", user.getEmail(), 60*60*24*2); //保存2天
//        CookieUtil.setCookie(response, "name", user.getName(), 60*60*24*2);
//        CookieUtil.setCookie(response, "password", user.getPassword(), 60*60*24*2);
//        CookieUtil.setCookie(response, "phone", user.getPhone(), 60*60*24*2);
//        CookieUtil.setCookie(response, "sex", user.getSex(), 60*60*24*2);
//        CookieUtil.setCookie(response, "school", user.getSchool(), 60*60*24*2);
//        CookieUtil.setCookie(response, "college", user.getCollege(), 60*60*24*2);

        return "redirect:/main.html";
    }

    @RequestMapping("/register")
    public String register(@RequestParam("email") String email,
                           @RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("phone") String phone,
                           @RequestParam("type") String sex,
                           @RequestParam("school") String school,
                           @RequestParam("college") String college,
                           HttpServletResponse response,
                           HttpServletRequest request,
                           Model model) {
        password = MD5.getMD5(password);
        User user = userService.SelectUserByEmail(email);
        if (user != null) {
            model.addAttribute("msg", "该邮箱已被注册！");
            return "login";
        }
        user = new User(email, name, password, phone, sex, school, college);
        try {
            userService.InsertUser(user);
        } catch (Exception e) {
            model.addAttribute("msg", "错误！请重试！");
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

//        CookieUtil.setCookie(response, "email", user.getEmail(), 60*60*24*2); //保存2天
//        CookieUtil.setCookie(response, "name", user.getName(), 60*60*24*2);
//        CookieUtil.setCookie(response, "password", user.getPassword(), 60*60*24*2);
//        CookieUtil.setCookie(response, "phone", user.getPhone(), 60*60*24*2);
//        CookieUtil.setCookie(response, "sex", user.getSex(), 60*60*24*2);
//        CookieUtil.setCookie(response, "school", user.getSchool(), 60*60*24*2);
//        CookieUtil.setCookie(response, "college", user.getCollege(), 60*60*24*2);

        return "redirect:/main.html";
    }

}
