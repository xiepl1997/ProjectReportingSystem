package lyn.projectreportingsystem.controller;

import lyn.projectreportingsystem.pojo.Team;
import lyn.projectreportingsystem.pojo.User;
import lyn.projectreportingsystem.service.impl.ProjectService;
import lyn.projectreportingsystem.service.impl.TeamService;
import lyn.projectreportingsystem.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService userService = null;

    @Autowired
    private TeamService teamService = null;

    @Autowired
    private ProjectService projectService = null;

    @RequestMapping("/index")
    public ModelAndView mainPage(HttpServletRequest request){

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        //获取该用户所在的团队信息
        List<Team> teamlist = new ArrayList<>();
        teamlist = teamService.getTeamsByUserEmail(((User)user).getEmail());

        Team team = null;
        int memberscount = 0;
        int projectcount = 0;
        //获取第一个团队的信息
        if(teamlist.size() != 0){
            team = teamService.getTeamByTeamid(teamlist.get(0).getTeamid());
            memberscount = teamService.getCountOfmembersByTeamid(teamlist.get(0).getTeamid());
            projectcount = projectService.getProjectCountByTeamid(teamlist.get(0).getTeamid());
        }

        ModelAndView mv = new ModelAndView();

        mv.addObject("projectcount", projectcount);//第一个团队的项目数
        mv.addObject("memberscount", memberscount); //第一个团队的人数
        mv.addObject("team", team); //第一个团队基本信息
        mv.addObject("teamlist", teamlist); //所有团队
        mv.addObject("user", user); //用户信息
        mv.setViewName("index");

        return mv;

    }

    @RequestMapping("index/members")
    public String tablesdata(){
        return "redirect:/members.html";
    }

    @RequestMapping("/index/projectform")
    public String projectform(){
        return "redirect:/projectform.html";
    }

}
