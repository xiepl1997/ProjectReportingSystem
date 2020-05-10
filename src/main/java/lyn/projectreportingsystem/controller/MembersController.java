package lyn.projectreportingsystem.controller;

import lyn.projectreportingsystem.pojo.User;
import lyn.projectreportingsystem.service.impl.ProjectService;
import lyn.projectreportingsystem.service.impl.TeamService;
import lyn.projectreportingsystem.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MembersController {

    @Autowired
    private UserService userService = null;

    @Autowired
    private TeamService teamService = null;

    @Autowired
    private ProjectService projectService = null;

    /**
     * 根据teamid得到团队的成员
     * @param request
     * @return
     */
    @RequestMapping("/getTeamMembers")
    @ResponseBody
    public Map<String, Object> getTeamMember(HttpServletRequest request){

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        //根据teamid获取团队成员
        String teamid = request.getParameter("teamname").split("-")[0];
        List<User> userList = userService.getMembersInSameTeamByTeamID(Integer.parseInt(teamid));

        String isleader = "false";

        //判断当前用户是否是该团队的负责人
        String leader_email = userService.selectLeader(Integer.parseInt(teamid));
        if(((User)user).getEmail().equals(leader_email))
            isleader = "true";

        Map<String, Object> map = new HashMap<>();
        map.put("userList", userList);
        map.put("isleader", isleader);
        return map;
    }

    /**
     * 在团队成员表中删除成员
     * @param request
     * @return
     */
    @RequestMapping("/delmember")
    @ResponseBody
    public List<User> delmember(HttpServletRequest request){
        String email = request.getParameter("email");
        String teamid = request.getParameter("teamname").split("-")[0];
        try{
            userService.deleteMember(email, Integer.parseInt(teamid));
        }
        catch (Exception e){
            return null;
        }

        List<User> userList = userService.getMembersInSameTeamByTeamID(Integer.parseInt(teamid));
        return userList;

    }
}
