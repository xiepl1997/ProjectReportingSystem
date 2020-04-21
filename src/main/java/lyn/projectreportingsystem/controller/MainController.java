package lyn.projectreportingsystem.controller;

import lyn.projectreportingsystem.pojo.User;
import lyn.projectreportingsystem.service.impl.UserService;
import lyn.projectreportingsystem.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService userService = null;

    @RequestMapping("/main")
    public String mainPage(HttpServletRequest request, Map<String, Object> map){
        Map<String, String> cookie_map = CookieUtil.getCookies(request);
        String email = cookie_map.get("email");
        String password = cookie_map.get("password");
        User user = null;
        if(email != null && password != null){
            user = userService.SelectUserByEmailPassword(email, password);
            if(user == null)
                return "login";
            else{
                map.put("user", user);
                return "main";
            }
        }
        return "logon";
    }

}
