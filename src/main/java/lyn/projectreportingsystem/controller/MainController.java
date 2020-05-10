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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @RequestMapping("/index/members")
    public String tablesdata(HttpServletRequest request,
                             RedirectAttributes redirectAttributes){

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        //获取所加入的团队
        List<Team> team = teamService.getTeamsByUserEmail(((User)user).getEmail());
        redirectAttributes.addFlashAttribute("teamlist", team);

        return "redirect:/members.html";
    }

    @RequestMapping("/index/projectform")
    public String projectform(){
        return "redirect:/projectform.html";
    }

    /**
     * 控制台更换团队时前端进行更新时数据传输
     * @param request
     * @return
     */
    @RequestMapping("/getTeamInfo")
    @ResponseBody
    public Object getTeamInfo(HttpServletRequest request){

        //获取teamid
        String teamid = request.getParameter("teamname").toString().split("-")[0];

        Team team = teamService.getTeamByTeamid(Integer.parseInt(teamid));
        int memberscount = teamService.getCountOfmembersByTeamid(Integer.parseInt(teamid));
        int projectcount = projectService.getProjectCountByTeamid(Integer.parseInt(teamid));

        Map<String, Object> map = new HashMap<>();
        map.put("projectcount", projectcount);
        map.put("memberscount",memberscount);
        map.put("team", team);

        return map;
    }

    /**
     * 用户加入团队
     * @param request
     * @return
     */
    @RequestMapping("/jointeam")
    @ResponseBody
    public Object JoinTeam(HttpServletRequest request){

        String teamid = request.getParameter("teamid");
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        //检查是否有该团队
        Team team = teamService.getTeamByTeamid(Integer.parseInt(teamid));
        if(team == null){
            return "teamnotexist";
        }

        //检查是否已经加入该团队
        List<Team> teamlist = new ArrayList<>();
        teamlist = teamService.getTeamsByUserEmail(((User)user).getEmail());
        for(int i = 0; i < teamlist.size(); i++){
            if(Integer.parseInt(teamid) == teamlist.get(i).getTeamid()){
                return "existed";
            }
        }

        //更新user_team表
        try{
            teamService.insert_user_team(((User)user).getEmail(), teamid, 0);
        }
        catch (Exception e){
            return null;
        }

        int memberscount = teamService.getCountOfmembersByTeamid(Integer.parseInt(teamid));
        int projectcount = projectService.getProjectCountByTeamid(Integer.parseInt(teamid));

        //获取该用户所在的团队信息，用于更新已加入团队下拉框
        teamlist = new ArrayList<>();
        teamlist = teamService.getTeamsByUserEmail(((User)user).getEmail());

        Map<String, Object> map = new HashMap<>();
        map.put("projectcount", projectcount);
        map.put("memberscount",memberscount);
        map.put("team", team);
        map.put("teamlist", teamlist);

        return map;
    }

    /**
     * 新增团队跳转
     * @return
     */
    @RequestMapping("/newteam")
    public String newTeam(){
        return "redirect:/newteam.html";
    }

    /**
     * 新建团队，把团队的id返回给ajax
     * @param request
     * @return
     */
    @RequestMapping("/addnewteam")
    @ResponseBody
    public String addnewteam(HttpServletRequest request){
        String teamname = request.getParameter("teamname");
        String teamremark = request.getParameter("teamremark");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        String leader = ((User)user).getName();

        Team team = new Team(teamname, teamremark, time, leader);

        try{
            teamService.insertteam(team);
            teamService.insert_user_team(((User)user).getEmail(), String.valueOf(team.getTeamid()), 1);
        }catch(Exception e){
            return "false";
        }
        return String.valueOf(team.getTeamid());

    }

}
