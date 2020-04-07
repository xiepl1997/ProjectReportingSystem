package lyn.projectreportingsystem.controller;

import lyn.projectreportingsystem.pojo.User;
import lyn.projectreportingsystem.service.impl.UserService;
import lyn.projectreportingsystem.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Map<String, Object> map){
        password = MD5.getMD5(password);
        User user = userService.SelectUser(email, password);
        if(user == null){
            map.put("msg", "账号或密码不正确！");
            return "login";
        }
        return "main";
    }
}
