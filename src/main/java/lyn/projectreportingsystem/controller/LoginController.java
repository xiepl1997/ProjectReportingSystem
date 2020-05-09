package lyn.projectreportingsystem.controller;

import lyn.projectreportingsystem.pojo.Team;
import lyn.projectreportingsystem.pojo.User;
import lyn.projectreportingsystem.service.impl.ProjectService;
import lyn.projectreportingsystem.service.impl.TeamService;
import lyn.projectreportingsystem.service.impl.UserService;
import lyn.projectreportingsystem.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService = null;

    @Autowired
    private TeamService teamService = null;

    @Autowired
    private ProjectService projectService = null;

    @RequestMapping("/")
    public String home() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model,
                        RedirectAttributes redirectAttributes,
                        HttpServletRequest request) {
        password = MD5.getMD5(password);
        //获取用户
        User user = userService.SelectUserByEmailPassword(email, password);
        if (user == null) {
            model.addAttribute("msg", "账号或密码不正确！");
            return "login";
        }

        //创建session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

//        //获取该用户所在的团队信息
//        List<Team> teamlist = new ArrayList<>();
//        teamlist = teamService.getTeamsByUserEmail(email);
//
//        Team team = null;
//        int memberscount = 0;
//        int projectcount = 0;
//        //获取第一个团队的信息
//        if(teamlist.size() != 0){
//            team = teamService.getTeamByTeamid(teamlist.get(0).getTeamid());
//            memberscount = teamService.getCountOfmembersByTeamid(teamlist.get(0).getTeamid());
//            projectcount = projectService.getProjectCountByTeamid(teamlist.get(0).getTeamid());
//        }
//
//        redirectAttributes.addFlashAttribute("projectcount", projectcount);//第一个团队的项目数
//        redirectAttributes.addFlashAttribute("memberscount", memberscount); //第一个团队的人数
//        redirectAttributes.addFlashAttribute("team", team); //第一个团队基本信息
//        redirectAttributes.addFlashAttribute("teamlist", teamlist); //所有团队
//        redirectAttributes.addFlashAttribute("user", user); //用户信息

        return "redirect:/index";
    }

    @RequestMapping("/register")
    public String register(@RequestParam("email") String email,
                           @RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("phone") String phone,
                           @RequestParam("type") String sex,
                           @RequestParam("school") String school,
                           @RequestParam("college") String college,
                           RedirectAttributes redirectAttributes,
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

        //获取该用户所在的团队信息
        List<Team> teamlist = new ArrayList<>();
        teamlist = teamService.getTeamsByUserEmail(email);

        redirectAttributes.addFlashAttribute("teamlist", teamlist);
        redirectAttributes.addFlashAttribute("user", user);

        return "redirect:/index.html";
    }

}
