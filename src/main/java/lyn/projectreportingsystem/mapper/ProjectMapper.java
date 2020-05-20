package lyn.projectreportingsystem.mapper;

import lyn.projectreportingsystem.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
