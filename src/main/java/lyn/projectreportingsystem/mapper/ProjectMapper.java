package lyn.projectreportingsystem.mapper;

import lyn.projectreportingsystem.pojo.Project;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProjectMapper {

    /**
     * 由teamid获取该团队的项目数量
     * @param teamid
     * @return
     */
    @Select("select count(distinct projectid) from team_project where teamid=#{teamid}")
    int getProjectCountByTeamid(@Param("teamid") int teamid);

    /**
     * 通过用户id、团队id获取项目
     * @param teamid
     * @param email
     * @return
     */
    @Select("select p.* from team_project tp, project p where tp.email = #{email} and tp.teamid = #{teamid} and tp.projectid = p.projectid")
    List<Project> getProjectByTeamidandEmail(@Param("teamid") int teamid, @Param("email")String email);

    /**
     * 获取用户参与的项目
     * @param email
     * @return
     */
    @Select("select p.* from team_project tp, project p where tp.email = #{email} and tp.projectid = p.projectid")
    List<Project> getProjectByEmail(@Param("email")String email);

    /**
     * 通过类型和用户email获取项目
     * @param type
     * @return
     */
    @Select("select p.* from team_project tp, project p where tp.email = #{email} and tp.projectid = p.projectid and p.type = #{type}")
    List<Project> getProjectBytype(@Param("type")String type, @Param("email")String email);

    /**
     * 团队管理员添加项目
     * @return
     */
    @Insert("insert into project(projectname, type, projectremark, settime, money) values(#{projectname}, #{type},#{projectremark},now(),#{money})")
    @Options(useGeneratedKeys = true, keyProperty = "projectid", keyColumn = "projectid")
    boolean insertproject(Project project);

    /**
     * 通过项目id获取项目
     * @param projectid
     * @return
     */
    @Select("select * from project where projectid = #{projectid}")
    Project getprojectbyprojectid(@Param("projectid") int projectid);

    @Update("update project set projectname=#{projectname},starttime=#{starttime},endtime=#{endtime},money=#{money},type=#{type},tertiarydiscipline=#{tertiarydiscipline},projectremark=#{projectremark} where projectid=#{projectid}")
    boolean updatesubmitproject(@Param("projectid") int projectid,
                                @Param("projectname") String projectname,
                                @Param("starttime") String starttime,
                                @Param("endtime") String endtime,
                                @Param("money") float money,
                                @Param("type") String type,
                                @Param("tertiarydiscipline") String tertiarydiscipline,
                                @Param("projectremark") String projectremark);

    @Update("update project set filepath = #{filepath} where projectid = #{projectid}")
    boolean updatefile(@Param("projectid") int projectid, @Param("filepath") String filepath);

    /**
     * 获取团队的横向或纵向项目数量
     * @param teamid
     * @return
     */
    @Select("select count(projectid) from project where type=#{type} and projectid in (select distinct(projectid) from team_project where teamid=#{teamid})")
    int getHorizontalproject(@Param("teamid")int teamid, @Param("type")String type);
}
