package lyn.projectreportingsystem.controller;

import lyn.projectreportingsystem.pojo.Block;
import lyn.projectreportingsystem.pojo.Project;
import lyn.projectreportingsystem.pojo.Team;
import lyn.projectreportingsystem.pojo.User;
import lyn.projectreportingsystem.service.impl.BlockService;
import lyn.projectreportingsystem.service.impl.ProjectService;
import lyn.projectreportingsystem.service.impl.TeamService;
import lyn.projectreportingsystem.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
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

    @Autowired
    private BlockService blockService = null;

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
        int hc = 0;
        int vc = 0;
        //获取第一个团队的信息
        if(teamlist.size() != 0){
            team = teamService.getTeamByTeamid(teamlist.get(0).getTeamid());
            memberscount = teamService.getCountOfmembersByTeamid(teamlist.get(0).getTeamid());
            projectcount = projectService.getProjectCountByTeamid(teamlist.get(0).getTeamid());
            hc = projectService.getHorizontalproject(team.getTeamid(), "横向项目");
            vc = projectService.getHorizontalproject(team.getTeamid(), "纵向项目");
        }

        //获取横向项目数

        ModelAndView mv = new ModelAndView();

        mv.addObject("projectcount", projectcount);//第一个团队的项目数
        mv.addObject("memberscount", memberscount); //第一个团队的人数
        mv.addObject("team", team); //第一个团队基本信息
        mv.addObject("teamlist", teamlist); //所有团队
        mv.addObject("user", user); //用户信息
        mv.addObject("hc", hc);
        mv.addObject("vc", vc);
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
        int hc = projectService.getHorizontalproject(team.getTeamid(), "横向项目");
        int vc = projectService.getHorizontalproject(team.getTeamid(), "纵向项目");

        Map<String, Object> map = new HashMap<>();
        map.put("projectcount", projectcount);
        map.put("memberscount", memberscount);
        map.put("hc", hc);
        map.put("vc", vc);
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

    /**
     * myteam跳转
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/myteam")
    public String myteam(HttpServletRequest request, RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        List<Team> teamlist = teamService.selectteamofleader(((User) user).getEmail());
        redirectAttributes.addFlashAttribute("teamlist", teamlist);
        return "redirect:/myteam.html";
    }

    /**
     * 解散团队
     * @param request
     * @return
     */
    @RequestMapping("/deleteteam")
    @ResponseBody
    public Map<String, Object> deleteteam(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        String teamid = request.getParameter("teamid");

        Map<String, Object> map = new HashMap<>();
        map.put("status", true);

        try{
            teamService.deleteTeam(Integer.parseInt(teamid));
            teamService.deleteUser_Team(Integer.parseInt(teamid));
        }catch (Exception e){
            map.put("status", false);
        }

        List<Team> teamList = teamService.selectteamofleader(((User)user).getEmail());
        map.put("teamlist", teamList);

        return map;
    }

    /**
     * 重定向到项目列表
     * @return
     */
    @RequestMapping("/index/projectlist")
    public String projectlist(HttpServletRequest request,
                              RedirectAttributes redirectAttributes){

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        //获取所加入的团队
        List<Team> team = teamService.getTeamsByUserEmail(((User)user).getEmail());

        //获取用户参与的项目
        List<Project> projectlist = projectService.getProjectByEmail(((User)user).getEmail());

        redirectAttributes.addFlashAttribute("projectlist", projectlist);
        redirectAttributes.addFlashAttribute("teamlist", team);

        return "redirect:/projectlist.html";
    }

    @RequestMapping("/getteamproject")
    @ResponseBody
    public List<Project> getprojectlist(HttpServletRequest request){
        String teamid = request.getParameter("teamname").split("-")[0];

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        List<Project> projectslist = projectService.getProjectByTeamidandEmail(Integer.parseInt(teamid), ((User)user).getEmail());

        return projectslist;
    }

    /**
     * 重定向到创建项目
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/creatproject")
    public String creatproject(HttpServletRequest request,
                               RedirectAttributes redirectAttributes){

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        //获取所管理的团队
        List<Team> teamlist = teamService.selectteamofleader(((User) user).getEmail());
        List<User> userlist = new ArrayList<>();
        if(teamlist.size() != 0){
             userlist = userService.getMembersInSameTeamByTeamID(teamlist.get(0).getTeamid());
        }

        redirectAttributes.addFlashAttribute("teamlist", teamlist);
        redirectAttributes.addFlashAttribute("userlist", userlist);

        return "redirect:/creatproject.html";
    }

    /**
     * 在创建项目页面切换团队时，团队成员列表更新
     * @param request
     * @return
     */
    @RequestMapping("/creatprojectgetuser")
    @ResponseBody
    public List<User> creatprojectgetuser(HttpServletRequest request){
        String teamid = request.getParameter("teamid");

        List<User> userlist = userService.getMembersInSameTeamByTeamID(Integer.parseInt(teamid));
        return userlist;
    }

    /**
     * 创建新项目
     * @param request
     */
    @RequestMapping("/newprojectcreat")
    @ResponseBody
    public String newprojectcreat(HttpServletRequest request){
        String projectname = request.getParameter("projectname");
        String projectremark = request.getParameter("remark");
        String teamid = request.getParameter("teamid");
        String type = request.getParameter("type");
        String[] userids = request.getParameter("userids").split("-");

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        Project project = new Project(projectname, type,projectremark);

        try{
            projectService.insertproject(project);
            teamService.insert_team_project(Integer.parseInt(teamid), project.getProjectid(), ((User)user).getEmail());
            for(int i = 0; i < userids.length; i++){
                teamService.insert_team_project(Integer.parseInt(teamid), project.getProjectid(),userids[i]);
            }
            //得到block
            Block block = new Block(((User)user).getEmail(),String.valueOf(project.getProjectid()),projectname,null,null,0,type,
                    "",projectremark,"","0");
            block.mineBlock(2);
            blockService.insertblock(block);

        }catch (Exception e){
            return "error";
        }
        return "success";
    }

    /**
     * 跳转到填报模块
     * @param projectid
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/writeproject")
    public String gotoprojectform(@RequestParam("projectid") Integer projectid,
                                  RedirectAttributes redirectAttributes){

        Project project = projectService.getprojectbyprojectid(projectid);
        redirectAttributes.addFlashAttribute(project);

        return "redirect:/projectform.html";

    }

    /**
     * 通过type来获取项目
     * @param request
     * @return
     */
    @RequestMapping("/getprojectbytepe")
    @ResponseBody
    public List<Project> getprojectbytype(HttpServletRequest request){
        String type = request.getParameter("projecttype");

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        List<Project> projectList = projectService.getProjectBytype(type, ((User)user).getEmail());
        return projectList;
    }

    /**
     * 填报项目提交
     * @param request
     */
    @RequestMapping("/submitproject")
    @ResponseBody
    public String submitproject(HttpServletRequest request){
        String projectid = request.getParameter("projectid");
        String projectname = request.getParameter("projectname");
        String starttime = request.getParameter("starttime");
        String endtime = request.getParameter("endtime");
        String type = request.getParameter("type");
        String money = request.getParameter("money");
        String tertiarydiscipline = request.getParameter("tertiarydiscipline");
        String projectremark = request.getParameter("projectremark");

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        try{
            projectService.updatesubmitproject(Integer.parseInt(projectid),
                    projectname,starttime,endtime,Float.parseFloat(money),type,tertiarydiscipline,projectremark);
            List<Block> blockList = blockService.getblockbyprojectid(projectid);
            String previous_hash = blockList.get(0).getHash();
            Block block = new Block(((User)user).getEmail(),projectid,projectname,starttime,endtime,Float.parseFloat(money),type,tertiarydiscipline,projectremark,"",previous_hash);
            block.mineBlock(2);
            blockService.insertblock(block);
        }catch (Exception e){
            return "error";
        }
        return "success";

    }

    /**
     * 跳转到项目选择时间线页面
     * @param request
     * @return
     */
    @RequestMapping("/projectcache")
    public String getprojectcache(HttpServletRequest request,
                                  RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        //获取所加入的团队
        List<Team> team = teamService.getTeamsByUserEmail(((User)user).getEmail());

        //获取用户参与的项目
        List<Project> projectlist = projectService.getProjectByEmail(((User)user).getEmail());

        redirectAttributes.addFlashAttribute("projectlist", projectlist);
        redirectAttributes.addFlashAttribute("teamlist", team);

        return "redirect:/projectcache.html";
    }

    /**
     * 跳转到项目提交记录页面
     * @param projectid
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/checkprojectcache")
    public String checkprojectcache(@RequestParam("projectid") String projectid,
                                    RedirectAttributes redirectAttributes){
        String message = "";
        List<Block> blockList = blockService.getblockbyprojectid(projectid);

        String pre = blockList.get(0).getHash();

        for(int i = 1; i < blockList.size(); i++){
            if(!blockList.get(i).getPrevious_hash().equals(pre)){
                message = "/rollback";
                redirectAttributes.addFlashAttribute("msg", message);
                break;
            }
            pre = blockList.get(i).getHash();
        }
        redirectAttributes.addFlashAttribute("projectid",projectid);
        redirectAttributes.addFlashAttribute("blocklist", blockList);
        return "redirect:/reportrecord.html";
    }

    /**
     * 跳转到用户修改信息表
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/updateinfo")
    public String updateinfo(HttpServletRequest request,
                             RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        User nowuser = userService.SelectUserByEmail(((User)user).getEmail());

        redirectAttributes.addFlashAttribute("user", nowuser);

        return "redirect:/userinfo.html";
    }

    /**
     * 修改个人信息
     * @param request
     * @return
     */
    @RequestMapping("/ajaxupdateuserinfo")
    @ResponseBody
    public String ajaxupdateuserinfo(HttpServletRequest request){

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String school = request.getParameter("school");
        String college = request.getParameter("college");

        userService.updateuser(email, name,phone,sex,school,college);

        return "success";
    }

    /**
     * 提交记录rollback，去掉不合法提交
     * @param request
     * @return
     */
    @RequestMapping("/rollback")
    public String rollback(HttpServletRequest request,
                           @RequestParam("projectid")String projectid,
                           RedirectAttributes redirectAttributes){

        List<Block> blockList = blockService.getblockbyprojectid(projectid);

        String pre = blockList.get(0).getHash();
        int i = 1;
        for(; i < blockList.size(); i++){
            if(!blockList.get(i).getPrevious_hash().equals(pre)){
                break;
            }
            pre = blockList.get(i).getHash();
        }
        while(blockList.size() > i)
            blockList.remove(i);
        String timestamp = String.valueOf(blockList.get(i-1).getTimestamp());

        //恢复到脏数据之前的数据
        blockService.deleteblockbytimestamp(projectid, timestamp);
        projectService.updatesubmitproject(Integer.parseInt(projectid),
                blockList.get(i-1).getProjectname(),
                blockList.get(i-1).getStarttime(),
                blockList.get(i-1).getEndtime(),
                blockList.get(i-1).getMoney(),
                blockList.get(i-1).getType(),
                blockList.get(i-1).getTertiarydiscipline(),
                blockList.get(i-1).getProjectremark());
        projectService.updatefile(Integer.parseInt(projectid), blockList.get(i-1).getFile());

        redirectAttributes.addFlashAttribute("projectid",projectid);
        redirectAttributes.addFlashAttribute("blocklist", blockList);
        return "redirect:/reportrecord.html";
    }

}
