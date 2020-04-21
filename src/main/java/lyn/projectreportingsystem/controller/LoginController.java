package lyn.projectreportingsystem.controller;

import lyn.projectreportingsystem.pojo.User;
import lyn.projectreportingsystem.service.impl.UserService;
import lyn.projectreportingsystem.util.CookieUtil;
import lyn.projectreportingsystem.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService = null;

    @RequestMapping("/")
    public String home(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpServletResponse response){
        password = MD5.getMD5(password);
        User user = userService.SelectUserByEmailPassword(email, password);
        if(user == null){
            map.put("msg", "账号或密码不正确！");
            return "login";
        }
        //设置cookie
        CookieUtil.setCookie(response, "email", email, 60*60*24*2); //保存2天
        CookieUtil.setCookie(response, "password", password, 60*60*24*2);
        map.put("user", user);
        return "main";
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
                           Map<String, Object> map){
        password = MD5.getMD5(password);
        User user = userService.SelectUserByEmail(email);
        if(user != null){
            map.put("msg", "该邮箱已被注册！");
            return "login";
        }
        user = new User(email, name, password, phone, sex, school, college);
        try{
            userService.InsertUser(user);
        }catch (Exception e){
            map.put("msg", "错误！请重试！");
            return "login";
        }
        map.put("user", user);
        CookieUtil.setCookie(response, "email", email, 60*60*24*2); //保存2天
        CookieUtil.setCookie(response, "password", password, 60*60*24*2);
        return "main";
    }

}
